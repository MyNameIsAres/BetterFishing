package org.geminicraft.betterfishing.hooks;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.Location;

public class MythicMobsHook  {

    MythicMobs mythicMobs = MythicMobs.inst();

    public MythicMobs getMythicMobs() {
        return mythicMobs;
    }

    private BukkitAPIHelper getMythicAPiHelper() {
        return mythicMobs.getAPIHelper();
    }

    public void spawnMythicMob(String name, Location location) throws InvalidMobTypeException {
        getMythicAPiHelper().spawnMythicMob(name, location);
    }
}