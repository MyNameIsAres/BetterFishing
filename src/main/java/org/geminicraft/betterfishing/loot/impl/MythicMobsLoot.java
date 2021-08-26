package org.geminicraft.betterfishing.loot.impl;

import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.entity.Entity;
import org.geminicraft.betterfishing.hooks.MythicMobsHook;
import org.geminicraft.betterfishing.loot.Lootable;
import org.mineacademy.fo.Common;

public class MythicMobsLoot implements Lootable {

    private final String name;
    private final double spawnChance;

    public MythicMobsLoot(String name, double spawnChance) {
        this.name = name;
        this.spawnChance = spawnChance;
    }

    @Override
    public double getSpawnChance() {
        return spawnChance;
    }

    @Override
    public void createItem(Entity caughtEntity) {
        try {
            new MythicMobsHook().spawnMythicMob(getName(), caughtEntity.getLocation());
        } catch (InvalidMobTypeException exception) {
            Common.error(exception, "Could not create a Mythic Mob!");
        }

    }

    public String getName() {
        return name;
    }
}
