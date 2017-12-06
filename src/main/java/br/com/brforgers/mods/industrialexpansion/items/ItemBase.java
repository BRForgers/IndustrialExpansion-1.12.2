package br.com.brforgers.mods.industrialexpansion.items;

import net.minecraft.item.Item;
import br.com.brforgers.mods.industrialexpansion.IndustrialExpansion;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(IndustrialExpansion.tabIndustrialExpansion);
    }

    public void registerItemModel() {
        IndustrialExpansion.proxy.registerItemRenderer(this, 0, name);
    }


}
