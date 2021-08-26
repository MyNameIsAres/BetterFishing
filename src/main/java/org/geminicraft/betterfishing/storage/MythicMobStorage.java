package org.geminicraft.betterfishing.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.Lootable;
import org.geminicraft.betterfishing.loot.impl.MythicMobsLoot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MythicMobStorage extends Storage {

    private final static String FILE = "mythicmobs.yml";

    public MythicMobStorage(MainPlugin mainPlugin) {
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
        List<Lootable> mythicMobList = new ArrayList<>();

        this.getConfig().getKeys(false).forEach((item) -> {
            try {
                mythicMobList.add(new MythicMobsLoot(getConfig().getString(item + ".name"), getConfig().getDouble(item + ".dropWeight")));
                System.out.println(mythicMobList.size());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        return mythicMobList;
    }
}
