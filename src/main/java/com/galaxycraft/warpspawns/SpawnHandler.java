package com.galaxycraft.warpspawns;

import io.github.nucleuspowered.nucleus.api.EventContexts;
import io.github.nucleuspowered.nucleus.api.events.NucleusRedirectableSpawnEvent;
import io.github.nucleuspowered.nucleus.api.nucleusdata.Warp;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public class SpawnHandler {

    @Listener
    public void onSpawnEvent(NucleusRedirectableSpawnEvent event) {
        World world = event.getTransformTo().getExtent();

        //get the type of spawn event from nucleus, so we can differentiate permissions between death, spawn command, etc.
        String permSuffix = event.getContext().get(EventContexts.SPAWN_EVENT_TYPE).map(NucleusRedirectableSpawnEvent.Type::name).orElse("other").toLowerCase();

        //check each warp in the "spawns" category, in the order they were given, skipping any that are missing data
        for(Warp warp : WarpSpawns.getWarpService().getWarpsForCategory("spawns")) {
            if(world.getUniqueId().equals(warp.getLocation().map(Location::getExtent).map(World::getUniqueId).orElse(new UUID(0, 0)))
                    && event.getTargetUser().hasPermission("warpspawns." + warp.getName().toLowerCase() + "." + permSuffix)) {
                WarpSpawns.debug("Redirecting " + event.getTargetUser().getName() + "'s spawn to " + warp.getName() + ".");
                if(warp.getTransform().isPresent()) {
                    event.setTransformTo(warp.getTransform().get());
                    return;
                }
            }
        }
    }

}
