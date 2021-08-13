package org.geminicraft.betterfishing.storage;

import org.geminicraft.betterfishing.loot.Lootable;

import java.util.List;

public interface StoreAble {

    List<Lootable> loadConfig();

}
