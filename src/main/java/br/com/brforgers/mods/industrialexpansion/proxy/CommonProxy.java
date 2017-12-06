package br.com.brforgers.mods.industrialexpansion.proxy;

import br.com.brforgers.mods.industrialexpansion.manager.ItemManager;
import br.com.brforgers.mods.industrialexpansion.manager.RecipeManager;
import br.com.brforgers.mods.industrialexpansion.manager.*;
import net.minecraft.item.Item;

public class CommonProxy
{

    public void preInit() {
        ItemManager.init();
    }
    public void postInit() {
        RecipeManager.init();
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }
}
