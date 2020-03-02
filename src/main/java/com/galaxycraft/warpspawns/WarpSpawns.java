package com.galaxycraft.warpspawns;

import com.google.inject.Inject;
import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.service.NucleusHomeService;
import io.github.nucleuspowered.nucleus.api.service.NucleusModuleService;
import io.github.nucleuspowered.nucleus.api.service.NucleusWarpService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.Optional;
import java.util.Set;

@Plugin(
        id = "warpspawns",
        name = "WarpSpawns",
        description = "Uses Nucleus warps to enable multiple spawn points.",
        url = "http://www.galaxy-craft.com",
        authors = {
                "Brycey92"
        },
        dependencies = {
                @Dependency(id = "nucleus", version = "[1.13.0-S7.1,)")
        }
)
public class WarpSpawns {
    private boolean disable = false;

    private static WarpSpawns instance;

    @Inject
    private Logger logger;

    @Inject
    private PluginContainer pluginContainer;

    private NucleusWarpService warpService;

    public WarpSpawns() { instance = this; }

    public static WarpSpawns getInstance() { return instance; }

    @Listener
    //By this state, inter-plugin communication should be ready to occur. Plugins providing an API should be ready to accept basic requests.
    public void onPostInit(GamePostInitializationEvent event) {

        if(!NucleusAPI.getModuleService().getModulesToLoad().contains("spawn")) {
            disable = true;
            logger.error("Could not find Nucleus' spawn module.");
        }

        Optional warpServiceOptional = NucleusAPI.getWarpService();
        if(warpServiceOptional.isPresent()) {
            warpService = (NucleusWarpService) warpServiceOptional.get();
        }
        else {
            disable = true;
            logger.error("Could not find Nucleus' warp module.");
        }

        if(disable) {
            logger.error("One or more required Nucleus modules were not found. The plugin will now be disabled.");
        }
        else {
            Sponge.getEventManager().registerListeners(this, new SpawnHandler());
            logger.info("Ready to spawn to some warps!");
        }
    }

    public static NucleusWarpService getWarpService() { return instance.warpService; }

    protected static void debug(String msg) {
        instance.logger.debug(msg);
    }

    protected static void info(String msg) {
        instance.logger.info(msg);
    }

    protected static void warn(String msg) {
        instance.logger.warn(msg);
    }

    protected static void error(String msg) {
        instance.logger.error(msg);
    }
}
