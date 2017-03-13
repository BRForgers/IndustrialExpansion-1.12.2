package cf.brforgers.mods.industrialexpansion.helper;

import cf.brforgers.mods.industrialexpansion.IndustrialExpansion;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration configuration;

    public static int energyCompactCoalBall = 0;
    public static int energyDiamond = 0;

    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(IndustrialExpansion.MOD_ID))
        {
            loadConfiguration();
        }
    }
    private static void loadConfiguration()
    {
        energyCompactCoalBall = configuration.getInt("energyCompactCoalBall", "Energy Consumption", 10000, 1, 100000, "Sets the energy consumption for the production of Compact Coal Ball.");
        energyDiamond = configuration.getInt("energyDiamond", "Energy Consumption", 10000, 1, 100000, "Sets the energy consumption for the production of Diamond.");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}
