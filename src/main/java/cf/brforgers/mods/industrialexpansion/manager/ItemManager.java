package cf.brforgers.mods.industrialexpansion.manager;

import cf.brforgers.mods.industrialexpansion.IndustrialExpansion;
import cf.brforgers.mods.industrialexpansion.helper.ItemModelProvider;
import cf.brforgers.mods.industrialexpansion.items.ItemBase;
import cf.brforgers.mods.industrialexpansion.items.QuantumArmor;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemManager {
    public static QuantumArmor itemHelmetQuantum;
    public static QuantumArmor itemPlateQuantum;
    public static QuantumArmor itemLegsQuantum;
    public static QuantumArmor itemBootsQuantum;
    public static ItemBase CoalBall;
    public static ItemBase CompactCoalBall;
    public static ItemBase CoalChunk;

    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_QUANTUM = EnumHelper.addArmorMaterial("IE:QUANTUM","Quantum", 100, new int[] { 3, 8, 6, 3 }, 20 , SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f);
    public static final String[] TEXTURE_QUANTUM = { "industrialexpansion:textures/armor/" + "Quantum_1.png", "industrialexpansion:textures/armor/" + "Quantum_2.png" };

    public static void init() {
        CoalBall = register(new ItemBase("CoalBall"));
        CompactCoalBall = register(new ItemBase("CompactCoalBall"));
        CoalChunk = register(new ItemBase("CoalChunk"));

        itemHelmetQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.HEAD).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantumHelmet").setRegistryName("quantumHelmet").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemPlateQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.CHEST).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantumPlate").setRegistryName("quantumPlate").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemLegsQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.LEGS).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantumLegs").setRegistryName("quantumLegs").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemBootsQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.FEET).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantumBoots").setRegistryName("quantumBoots").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        IndustrialExpansion.proxy.registerItemRenderer(itemHelmetQuantum,0,"quantumHelmet");
        IndustrialExpansion.proxy.registerItemRenderer(itemPlateQuantum,0,"quantumPlate");
        IndustrialExpansion.proxy.registerItemRenderer(itemLegsQuantum,0,"quantumLegs");
        IndustrialExpansion.proxy.registerItemRenderer(itemBootsQuantum,0,"quantumBoots");
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if(item instanceof ItemModelProvider) {
            ((ItemModelProvider)item).registerItemModel(item);
        }

        return item;
    }
}
