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

    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_QUANTUM = EnumHelper.addArmorMaterial("IE:QUANTUM","quantum", 100, new int[] { 3, 8, 6, 3 }, 20 , SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f);
    public static final String[] TEXTURE_QUANTUM = { "industrialexpansion:textures/armor/" + "quantum_1.png", "industrialexpansion:textures/armor/" + "quantum_2.png" };

    public static void init() {
        CoalBall = register(new ItemBase("coal_ball"));
        CompactCoalBall = register(new ItemBase("compact_coal_ball"));
        CoalChunk = register(new ItemBase("coal_chunk"));

        itemHelmetQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.HEAD).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantum_helmet").setRegistryName("quantum_helmet").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemPlateQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.CHEST).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantum_plate").setRegistryName("quantum_plate").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemLegsQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.LEGS).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantum_legs").setRegistryName("quantum_legs").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        itemBootsQuantum = (QuantumArmor) register(new QuantumArmor(ARMOR_MATERIAL_QUANTUM, EntityEquipmentSlot.FEET).setEnergyParams(20000000, 32000).setArmorTextures(TEXTURE_QUANTUM).setUnlocalizedName("quantum_boots").setRegistryName("quantum_boots").setCreativeTab(IndustrialExpansion.tabIndustrialExpansion));
        IndustrialExpansion.proxy.registerItemRenderer(itemHelmetQuantum,0,"quantum_helmet");
        IndustrialExpansion.proxy.registerItemRenderer(itemPlateQuantum,0,"quantum_plate");
        IndustrialExpansion.proxy.registerItemRenderer(itemLegsQuantum,0,"quantum_legs");
        IndustrialExpansion.proxy.registerItemRenderer(itemBootsQuantum,0,"quantum_boots");
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if(item instanceof ItemModelProvider) {
            ((ItemModelProvider)item).registerItemModel(item);
        }

        return item;
    }
}
