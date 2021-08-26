package org.geminicraft.betterfishing.storage;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.impl.CustomCreature;
import org.geminicraft.betterfishing.loot.Lootable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCreatureStorage extends Storage {

    @Getter
    @Setter
    public Map<String, Integer> customCreatureStorage = new HashMap<>();

    private final static String FILE = "creatures.yml";

    public CustomCreatureStorage(MainPlugin mainPlugin) {
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
        List<Lootable> customCreatureList = new ArrayList<>();

        this.getConfig().getKeys(false).forEach((item) -> {
            try {
                customCreatureList.add(new CustomCreature(getConfig().getString(item + ".name"), getConfig().getDouble(item + ".dropWeight"), EntityType.valueOf(getConfig().getString(item + ".entitytype"))));
                System.out.println(customCreatureList.size());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        return customCreatureList;
    }
}
