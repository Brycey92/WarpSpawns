package com.galaxycraft.warpspawns;

import com.google.inject.Inject;
import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.service.NucleusHomeService;
import io.github.nucleuspowered.nucleus.api.service.NucleusModuleService;
import io.github.nucleuspowered.nucleus.api.service.NucleusWarpService;
import org.slf4j.Logger;
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
        description = "This is a Sponge plugin that uses Nucleus warps to enable multiple spawn points.",
        url = "http://www.galaxy-craft.com",
        authors = {
                "Brycey92"
        },
        dependencies = {
                @Dependency(id = "nucleus")
        }
)
public class WarpSpawns {
    private boolean disable = false;
    private boolean homeModuleLoaded = true;

    private static WarpSpawns instance;

    @Inject
    private Logger logger;

    @Inject
    private PluginContainer pluginContainer;

    //private NucleusSpawnService spawnService;
    private NucleusWarpService warpService;
    private NucleusHomeService homeService;

    public WarpSpawns() { instance = this; }

    public static WarpSpawns getInstance() { return instance; }

    @Listener
    //By this state, inter-plugin communication should be ready to occur. Plugins providing an API should be ready to accept basic requests.
    public void onPostInit(GamePostInitializationEvent event) {

        /*Optional spawnServiceOptional = NucleusAPI.getSpawnService();
        if(spawnServiceOptional.isPresent()) {
            spawnService = (NucleusSpawnService) spawnServiceOptional.get();
        }
        else {
            disable = true;
            logger.error("Could not find Nucleus' spawn module.");
        }*/

        Optional warpServiceOptional = NucleusAPI.getWarpService();
        if(warpServiceOptional.isPresent()) {
            warpService = (NucleusWarpService) warpServiceOptional.get();
        }
        else {
            disable = true;
            logger.error("Could not find Nucleus' warp module.");
        }

        Optional homeServiceOptional = NucleusAPI.getHomeService();
        if(homeServiceOptional.isPresent()) {
            homeService = (NucleusHomeService) homeServiceOptional.get();
        }
        else {
            homeModuleLoaded = false;
        }

        if(disable) {
            throw new RuntimeException("One or more required Nucleus modules were not found. The plugin will now be disabled.");
        }
        else {
            logger.info("Ready to spawn to some warps!");
        }
    }

    /*public NucleusSpawnService getSpawnService() {
        return spawnService;
    }*/

    public NucleusWarpService getWarpService() {
        return warpService;
    }

    public NucleusHomeService getHomeService() {
        return homeService;
    }
}
