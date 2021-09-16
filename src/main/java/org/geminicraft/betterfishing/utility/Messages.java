package org.geminicraft.betterfishing.utility;

import org.bukkit.entity.Entity;
import org.geminicraft.betterfishing.loot.Lootable;
import org.mineacademy.fo.Common;

public class Messages {

    public static String caughtLootMessage(Lootable lootable) {
        return Common.colorize("&2You caught a &a" + lootable.getLootName());
    }

    public static String caughtFishMessage(final Entity entity) {
        return Common.colorize("&2You caught a &a" + entity.getName());
    }

}
