package org.geminicraft.betterfishing.storage;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.impl.CustomItem;
import org.geminicraft.betterfishing.loot.Lootable;
import org.geminicraft.betterfishing.utility.Utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CustomItemStorage extends Storage {

    @Getter
    @Setter
    public Map<String, Integer> customCreatureStorage = new HashMap<>();


    private final static String FILE = "items.yml";

    public CustomItemStorage(MainPlugin mainPlugin) {
        super(mainPlugin, FILE);
        saveDefaultConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
    }

    @Override
    public FileConfiguration getConfig() {
        return super.getConfig();
    }

    @Override
    public void saveConfig() {
        super.saveConfig();
    }

    @Override
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
    }

    @Override
    public List<Lootable> loadConfig() {
        List<Lootable> customItemList = new ArrayList<>();

        this.getConfig().getKeys(false).forEach((item) -> {
            try {
                customItemList.add(new CustomItem(getConfig().getString(item + ".name"), getConfig().getDouble(item + ".dropWeight"), getConfig().getString(item + ".material")));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        return customItemList;
    }
}
