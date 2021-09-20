package org.geminicraft.betterfishing.loot.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.geminicraft.betterfishing.loot.Lootable;
import java.util.List;
import java.util.Set;

public class CustomItem implements Lootable {

    private String material;
    private double spawnChance;
    private String itemID;
    private String name;
    private List<String> lore;
    private Set<Enchantment> enchantmentSet; // TODO Test if this should be a Set or Map
    // Optional: Hide attributes/enchantments information, glowing


    public CustomItem(String name, double spawnChance, String material, List<String> lore) {
        this.name = name;
        this.spawnChance = spawnChance;
        this.material = material;
        this.lore = lore;
    }

    private void createCustomItem(Entity caughtEntity) {
        Item caughtItem = (Item) caughtEntity;
        ItemStack itemStack = new ItemStack(Material.valueOf(material), 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        caughtItem.setCustomName(name);
        caughtItem.setCustomNameVisible(true);
        itemMeta.setLore(lore);
        caughtItem.setItemStack(itemStack);
    }



    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    @Override
    public String getLootID() {
        return itemID;
    }

    @Override
    public String getLootName() {
        return name;
    }

    @Override
    public double getSpawnChance() {
        return spawnChance;
    }

    @Override
    public void createItem(Entity caughtEntity) {
        createCustomItem(caughtEntity);
    }
}