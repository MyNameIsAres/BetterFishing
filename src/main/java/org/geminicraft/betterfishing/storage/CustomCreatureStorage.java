package org.geminicraft.betterfishing.storage;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.CustomCreature;
import org.geminicraft.betterfishing.loot.Lootable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCreatureStorage implements StoreAble {

    @Getter
    @Setter
    public Map<String, Integer> customCreatureStorage = new HashMap<>();

    private final MainPlugin mainPlugin;
    private FileConfiguration fileConfiguration = null;
    private File configFile = null;
    private final static String FILE = "creatures.yml";

    public CustomCreatureStorage(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;

        saveDefaultConfig();

    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.mainPlugin.getDataFolder(), FILE);
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = this.mainPlugin.getResource(FILE);
        if (defaultStream != null) {
            YamlConfiguration defaultConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.fileConfiguration.setDefaults(defaultConfiguration);
        }
    }

    public FileConfiguration getConfig() {
        if (this.fileConfiguration == null) {
            reloadConfig();
        }
        return this.fileConfiguration;
    }

    public void saveConfig() {
        if (this.fileConfiguration == null || this.configFile == null) {
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            System.out.println("This is null.. so create it?");
            this.configFile = new File(this.mainPlugin.getDataFolder(), FILE);
        }

        System.out.println(configFile + " WTF does it thinkt his is");
        System.out.println(mainPlugin.getDataFolder() + " is the data folder evn real");

        if (!this.configFile.exists()) {
            this.mainPlugin.saveResource(FILE, false);
        }
    }

    @Override
    public List<Lootable> loadConfig() {
        List<Lootable> customCreatureList = new ArrayList<>();

        this.getConfig().getKeys(false).forEach((item) -> {
            try {
                customCreatureList.add(new CustomCreature(getConfig().getString(item + ".name"), getConfig().getDouble(item + ".dropWeight")));
                System.out.println(customCreatureList.size());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        return customCreatureList;
    }
}
