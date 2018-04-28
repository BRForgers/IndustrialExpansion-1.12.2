package br.com.brforgers.mods.industrialexpansion.manager;

import br.com.brforgers.mods.industrialexpansion.IndustrialExpansion;
import br.com.brforgers.mods.industrialexpansion.helper.ConfigHandler;
import cofh.thermalexpansion.init.TEItems;
import cofh.thermalexpansion.item.ItemFrame;
import cofh.thermalexpansion.util.managers.machine.CompactorManager;
import cofh.thermalfoundation.block.BlockGlass;
import cofh.thermalfoundation.item.ItemMaterial;
import cofh.thermalexpansion.item.ItemCapacitor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeManager {
    public static void init() {
        ResourceLocation group = new ResourceLocation(IndustrialExpansion.MOD_ID, "group");

        ItemStack glowflorb = new ItemStack(TEItems.itemFlorb);
        if(glowflorb.getTagCompound() == null){
            glowflorb.setTagCompound(new NBTTagCompound());
        }
        glowflorb.getTagCompound().setString("Fluid","glowstone");
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"coalball"),group, new ItemStack(ItemManager.CoalBall,1),
                "AAA","ABA","AAA",
                'A', "dustCoal",
                'B', Items.FLINT);
        CompactorManager.addRecipe(ConfigHandler.energyCompactCoalBall, new ItemStack(ItemManager.CoalBall),new ItemStack(ItemManager.CompactCoalBall), CompactorManager.Mode.PLATE);
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"coalchuck"),group,new ItemStack(ItemManager.CoalChunk,1),
                "AAA","ABA","AAA",
                'A', ItemManager.CompactCoalBall,
                'B', Blocks.OBSIDIAN);
        CompactorManager.addRecipe(ConfigHandler.energyDiamond, new ItemStack(ItemManager.CoalChunk),new ItemStack(Items.DIAMOND), CompactorManager.Mode.PLATE);
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"quantumhelmet"),group, new ItemStack(ItemManager.itemHelmetQuantum,1),
                " A ","BCB","DED",
                'A', Items.DIAMOND_HELMET,
                'B', ItemMaterial.plateIridium,
                'C', ItemCapacitor.capacitorResonant,
                'D', ItemMaterial.gearLumium,
                'E', BlockGlass.glassIridium);
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"quantumplate"),group, new ItemStack(ItemManager.itemPlateQuantum,1),
                "ABA","CDC","CAC",
                'A', ItemMaterial.ingotEnderium,
                'B', Items.DIAMOND_CHESTPLATE,
                'C', ItemMaterial.plateIridium,
                'D', ItemCapacitor.capacitorResonant);
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"quantumlegs"),group, new ItemStack(ItemManager.itemLegsQuantum,1),
                "ABA","CDC","E E",
                'A', ItemFrame.frameMachine,
                'B', ItemCapacitor.capacitorResonant,
                'C', ItemMaterial.plateIridium,
                'D', Items.DIAMOND_LEGGINGS,
                'E', glowflorb);
        GameRegistry.addShapedRecipe(new ResourceLocation(IndustrialExpansion.MOD_ID,"quantumboots"),group, new ItemStack(ItemManager.itemBootsQuantum,1),
                "ABA","CDC","   ",
                'A', ItemMaterial.plateIridium,
                'B', Items.DIAMOND_BOOTS,
                'C', ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalfoundation","armor.boots_tin")),
                'D', ItemCapacitor.capacitorResonant);
        //More Recipes Update
        int energyPress = 4000,
            energyStorage = 400;
        CompactorManager.addRecipe(energyPress, new ItemStack(Items.BLAZE_POWDER,5), new ItemStack(Items.BLAZE_ROD), CompactorManager.Mode.PLATE);
        CompactorManager.addRecipe(energyPress, new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE), CompactorManager.Mode.PLATE);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Items.SNOWBALL, 4), new ItemStack(Blocks.SNOW), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Blocks.SAND,4,0), new ItemStack(Blocks.SANDSTONE,1,0), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Blocks.SAND,4,1), new ItemStack(Blocks.RED_SANDSTONE,1,0), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Items.CLAY_BALL,4), new ItemStack(Blocks.CLAY), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Items.NETHERBRICK,4), new ItemStack(Blocks.NETHER_BRICK), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Items.GLOWSTONE_DUST,4), new ItemStack(Blocks.GLOWSTONE), CompactorManager.Mode.ALL);
        CompactorManager.addRecipe(energyStorage, new ItemStack(Items.BRICK,4), new ItemStack(Blocks.BRICK_BLOCK), CompactorManager.Mode.ALL);
    }
}
