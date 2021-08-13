package org.geminicraft.betterfishing.loot;

import org.bukkit.entity.Entity;

public interface Lootable {

    double spawnChance = 0.0;

    double getSpawnChance();

    void createItem(Entity entity);


}
