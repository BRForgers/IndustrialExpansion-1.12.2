package cf.brforgers.mods.industrialexpansion.items;

import cofh.core.item.ItemArmorCore;
import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ISpecialArmor;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import cf.brforgers.mods.industrialexpansion.helper.DurabillityHelper;
import cf.brforgers.mods.industrialexpansion.manager.ItemManager;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class QuantumArmor extends ItemArmorCore implements ISpecialArmor, IEnergyContainerItem {

    public static final ISpecialArmor.ArmorProperties QUANTUM = new ArmorProperties(0, 0.20D, Integer.MAX_VALUE);

    public int maxEnergy = 25000000;
    public int maxTransfer = 50000;

    public double absorbRatio = 1.0E7D;
    public int energyPerDamage = 20000;

    public String[] textures = new String[2];

    public QuantumArmor(ItemArmor.ArmorMaterial material, EntityEquipmentSlot type) {
        super(material, type);
    }

    public QuantumArmor setEnergyParams(int maxEnergy, int maxTransfer) {
        this.maxEnergy = maxEnergy;
        this.maxTransfer = maxTransfer;
        return this;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.UNCOMMON;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean check) {

        if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.shiftForDetails());
        }
        if (!StringHelper.isShiftKeyDown()) {
            return;
        }
        if (stack.getTagCompound() == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.getTagCompound().getInteger("Energy") + " / " + maxEnergy + " RF");
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {

        super.setDamage(stack, 0);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return !ItemHelper.areItemStacksEqualIgnoreTags(oldStack, newStack, "Energy");
    }


    public double getDurabilityForDisplay(ItemStack stack)
    {
        if (stack.getTagCompound() == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        return 1.0D - (double) stack.getTagCompound().getInteger("Energy") / (double) this.maxEnergy;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {

        return 0;//maxEnergy;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return DurabillityHelper.showArmorCharge;
        //return !DurabillityHelper.showArmorCharge ? false : stack.getTagCompound() == null || !stack.getTagCompound().getBoolean("CreativeTab");
    }

    @Override
    public boolean isDamaged(ItemStack stack) {

        return true;
    }

    @Override
    public void getSubItems(@Nonnull Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    protected int getBaseAbsorption() {

        return 20;
    }

    /**
     * Returns a % that each piece absorbs, set sums to 100.
     */
    protected int getAbsorptionRatio() {

        switch (armorType) {
            case HEAD:
                return 15;
            case CHEST:
                return 40;
            case LEGS:
                return 30;
            case FEET:
                return 15;
            default:
                return 0;
        }
    }

    protected int getEnergyPerDamage(ItemStack stack) {

        int unbreakingLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack), 0, 4);
        return energyPerDamage * (5 - unbreakingLevel) / 5;
    }

    /* ISpecialArmor */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player,@Nonnull ItemStack armor, DamageSource source, double damage, int slot) {

        if ("quantum".equals(source.damageType)) {
            return QUANTUM;
        } else if (source.isUnblockable()) {
            int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
            return new ArmorProperties(0, absorbRatio * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
        }
        int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
        return new ArmorProperties(0, absorbRatio * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
        // 0.05 = 1 / 20 (max armor)
    }

    @Override
    public int getArmorDisplay(EntityPlayer player,@Nonnull ItemStack armor, int slot) {

        if (getEnergyStored(armor) >= getEnergyPerDamage(armor)) {
            return Math.min(getBaseAbsorption(), 20) * getAbsorptionRatio() / 100;
        }
        return 0;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        if(world.isRemote) return;
        if (getEnergyStored(armor) >= getEnergyPerDamage(armor)) {
            if (armor.getItem() == ItemManager.itemLegsQuantum) {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 260, 4, true, false));
            }
            if (armor.getItem() == ItemManager.itemPlateQuantum) {
                player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 260, 4, true, false));
            }
            if (armor.getItem() == ItemManager.itemHelmetQuantum) {
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 260, 0, true, false));
            }
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity,@Nonnull ItemStack armor, DamageSource source, int damage, int slot) {

        if (source.damageType.equals("quantum")) {
            boolean p = source.getTrueSource() == null;
            receiveEnergy(armor, damage * (p ? energyPerDamage / 2 : getEnergyPerDamage(armor)), false);
        } else {
            extractEnergy(armor, damage * getEnergyPerDamage(armor), false);
        }
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.getTagCompound() == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.getTagCompound().getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate) {
            stored += receive;
            container.getTagCompound().setInteger("Energy", stored);
        }
        return receive;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.getTagCompound() == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.getTagCompound().getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if (!simulate) {
            stored -= extract;
            container.getTagCompound().setInteger("Energy", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack container) {

        if (container.getTagCompound() == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        return container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return maxEnergy;
    }
}