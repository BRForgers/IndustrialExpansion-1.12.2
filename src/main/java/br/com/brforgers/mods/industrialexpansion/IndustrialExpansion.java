package br.com.brforgers.mods.industrialexpansion;

import br.com.brforgers.mods.industrialexpansion.manager.ItemManager;
import br.com.brforgers.mods.industrialexpansion.proxy.CommonProxy;
import br.com.brforgers.mods.industrialexpansion.helper.ConfigHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = IndustrialExpansion.MOD_ID,
        name = IndustrialExpansion.MOD_NAME,
        useMetadata = true,
        version = IndustrialExpansion.VERSION,
        dependencies = "required-after:thermalexpansion@[5.4.2,);")
public class IndustrialExpansion {
    public static final String MOD_ID = "industrialexpansion";
    public static final String MOD_NAME = "IndustrialExpansion";
    public static final String VERSION = "1.2.6";
    //Proxy Identity
    public static final String COMMONPROXY = "br.com.brforgers.mods.industrialexpansion.proxy.CommonProxy";
    public static final String CLIENT = "br.com.brforgers.mods.industrialexpansion.proxy.ClientProxy";
    public static final String RESOURCE_PATH = MOD_ID + ":";

    @Mod.Instance(MOD_ID)
    public static IndustrialExpansion instance;

    public static Logger logger = LogManager.getLogger("IndustrialExpansion");

    @SidedProxy(clientSide = CLIENT, serverSide = COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabIndustrialExpansion = new CreativeTabs("tabIndustrialExpansion")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.DIAMOND);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        logger.info("Armelin is Topper!");
        //logger = e.getModLog();
        ConfigHandler.init(e.getSuggestedConfigurationFile());
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit();
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ItemManager.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ItemManager.registerModels();
        }
    }
}
