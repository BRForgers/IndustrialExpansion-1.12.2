package cf.brforgers.mods.industrialexpansion.items;

import cf.brforgers.mods.industrialexpansion.helper.ItemModelProvider;
import net.minecraft.item.Item;
import cf.brforgers.mods.industrialexpansion.IndustrialExpansion;

public class ItemBase extends Item implements ItemModelProvider {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(IndustrialExpansion.tabIndustrialExpansion);
    }

    @Override
    public void registerItemModel(Item item) {
        IndustrialExpansion.proxy.registerItemRenderer(this, 0, name);
    }


}
