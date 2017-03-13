package cf.brforgers.mods.industrialexpansion;

import cf.brforgers.mods.industrialexpansion.helper.ConfigHandler;
import cf.brforgers.mods.industrialexpansion.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = IndustrialExpansion.MOD_ID,
        name = IndustrialExpansion.MOD_NAME,
        useMetadata = true,
        version = IndustrialExpansion.VERSION,
        dependencies = "required-after:thermalexpansion@[5.0.3,);"
)
public class IndustrialExpansion {
    public static final String MOD_ID = "IndustrialExpansion";
    public static final String MOD_NAME = "IndustrialExpansion";
    public static final String VERSION = "1.0";
    //Proxy Identity
    public static final String COMMONPROXY = "cf.brforgers.mods.industrialexpansion.proxy.CommonProxy";
    public static final String CLIENT = "cf.brforgers.mods.industrialexpansion.proxy.ClientProxy";
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
        public Item getTabIconItem()
        {
            return Items.DIAMOND;
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
}
