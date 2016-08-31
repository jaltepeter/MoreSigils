package com.moresigils.item.sigil;

import com.moresigils.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemSigilWaterBreath extends ItemSigilBaseToggleable implements IPotionSigil
{
    private PotionEffect potionEffect;
    private Potion mobEffect;

    public ItemSigilWaterBreath()
    {
        super("WaterBreath", ConfigHandler.sigilWaterBreathCost);
        MinecraftForge.EVENT_BUS.register(this);

        ItemStack stack = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER_BREATHING);
        this.potionEffect = PotionUtils.getEffectsFromStack(stack).get(0);

        this.mobEffect = MobEffects.WATER_BREATHING;
    }

    @Override
    public void onSigilUpdate(ItemStack stack, World world, EntityPlayer player, int itemSlot, boolean isSelected)
    {
        this.applyEffect(player);
    }

    @Override
    public PotionEffect getPotionEffect()
    {
        return this.potionEffect;
    }

    @Override
    public Potion getMobEffect()
    {
        return this.mobEffect;
    }

    @Override
    public void applyEffect(EntityPlayer player)
    {
        PotionEffect currentEffect = player.getActivePotionEffect(getPotionEffect().getPotion());
        if (currentEffect == null || currentEffect.getDuration() < 205)
        {
            player.addPotionEffect(new PotionEffect(getMobEffect(), 285, 1, true, false));
        }
    }
}