package org.geminicraft.betterfishing;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;
import org.geminicraft.betterfishing.events.PlayerListener;
import org.geminicraft.betterfishing.loot.Lootable;
import org.geminicraft.betterfishing.storage.CustomCreatureStorage;
import org.geminicraft.betterfishing.storage.CustomItemStorage;
import org.geminicraft.betterfishing.storage.MythicMobStorage;
import org.geminicraft.betterfishing.storage.StoreAble;
import org.mineacademy.fo.plugin.SimplePlugin;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainPlugin extends SimplePlugin {

    public CustomCreatureStorage customCreatureStorage = new CustomCreatureStorage(this);
    public CustomItemStorage customItemStorage = new CustomItemStorage(this);
    public MythicMobStorage mythicMobStorage = new MythicMobStorage(this);

    @Getter
    @Setter
    Multimap<Double, Lootable> map = ArrayListMultimap.create();

    @Override
    protected void onPluginStart() {



        this.getStorageInstances();

        registerEvents(new PlayerListener(this));
    }

    @Override
    protected void onPluginStop() {
        customCreatureStorage.saveConfig();
        customItemStorage.saveConfig();
        mythicMobStorage.saveConfig();
    }

    private void insertLootToMap(List<Lootable> customCreaturesList) {
        customCreaturesList.forEach((item) -> {
            map.put(item.getSpawnChance(), item);
        });
    }

    private void getStorageInstances() {
        Set<StoreAble> storeAbleItems = new HashSet<>();

        storeAbleItems.add(customCreatureStorage);
        storeAbleItems.add(customItemStorage);
        storeAbleItems.add(mythicMobStorage);

        storeAbleItems.forEach((item) -> {
            insertLootToMap(item.loadConfig());
        });
    }
}
