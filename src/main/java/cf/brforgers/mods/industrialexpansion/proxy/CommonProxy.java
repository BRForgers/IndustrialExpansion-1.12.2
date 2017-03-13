package cf.brforgers.mods.industrialexpansion.proxy;

import cf.brforgers.mods.industrialexpansion.manager.*;
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
