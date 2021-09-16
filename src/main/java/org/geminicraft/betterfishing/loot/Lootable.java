package org.geminicraft.betterfishing.loot;

import org.bukkit.entity.Entity;

public interface Lootable {

    String getLootID();

    String getLootName();

    double getSpawnChance();

    void createItem(Entity entity);


}
