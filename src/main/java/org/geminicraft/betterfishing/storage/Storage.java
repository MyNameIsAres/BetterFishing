package org.geminicraft.betterfishing.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.Lootable;
import org.geminicraft.betterfishing.utility.Utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Storage implements StoreAble{

    private final MainPlugin mainPlugin;
    private FileConfiguration fileConfiguration = null;
    private File configFile = null;
    private  String file;

    public Storage(MainPlugin mainPlugin, String file) {
        this.mainPlugin = mainPlugin;
        this.file = file;
    }

    public void reloadConfig() {
        Optional<File> optionalConfigFile = Optional.of(new File(this.mainPlugin.getDataFolder(), file));

        optionalConfigFile.ifPresent((file -> this.fileConfiguration = YamlConfiguration.loadConfiguration(configFile)));

        Optional<InputStream> optionalDefaultStream = Optional.ofNullable(this.mainPlugin.getResource(file));

        optionalDefaultStream.ifPresent((stream -> {
            YamlConfiguration defaultConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(optionalDefaultStream.get()));
            this.fileConfiguration.setDefaults(defaultConfiguration);
        }));
    }

    public FileConfiguration getConfig() {
        if (Utility.checkNotNull(this.fileConfiguration)) {
            reloadConfig();
        }
        return this.fileConfiguration;
    }

    public void saveConfig() {
        if (Utility.checkNotNull(fileConfiguration) || Utility.checkNotNull(configFile)) {
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (Utility.checkNotNull(configFile)) {
            this.configFile = new File(this.mainPlugin.getDataFolder(), file);
        }

        if (!this.configFile.exists()) {
            this.mainPlugin.saveResource(file, false);
        }
    }


    public List<Lootable> loadConfig() {
        return new ArrayList<>();
    }


}
