package com.dynview;

import com.dynview.config.Configuration;
import com.dynview.event.EventHandler;
import com.dynview.event.ModBusEventHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class DynView
{
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * The config instance.
     */
    private static Configuration config;

    public DynView()
    {
        config = new Configuration();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(EventHandler.class);
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(ModBusEventHandler.class);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
          () -> new IExtensionPoint.DisplayTest(() -> "ANY", (remote, isServer) -> true));
    }

    public static Configuration getConfig()
    {
        return config;
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("Zooming chunks initiated!");
    }
}
