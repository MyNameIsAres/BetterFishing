package org.geminicraft.betterfishing.loot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Set;

public class CustomItem implements Lootable {

    private String material;
    private double spawnChance;
    private String itemID;
    private String name;
    private String[] lore;
    private Set<Enchantment> enchantmentSet; // TODO Test if this should be a Set or Map
    private double dropRate;
    // Optional: Hide attributes/enchantments information, glowing


    public CustomItem(String name, double spawnChance, String material) {
        this.name = name;
        this.spawnChance = spawnChance;
        this.material = material;
    }

    public CustomItem(String material, String itemID, String name, String[] lore, Set<Enchantment> enchantmentSet, double dropRate) {
        this.material = material;
        this.itemID = itemID;
        this.name = name;
        this.lore = lore;
        this.enchantmentSet = enchantmentSet;
        this.dropRate = dropRate;

    }

    private void createCustomItem(Entity caughtEntity) {
        Item caughtItem = (Item) caughtEntity;
        ItemStack itemStack = new ItemStack(Material.valueOf(material), 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        caughtItem.setCustomName(name);
        caughtItem.setCustomNameVisible(true);

//        itemMeta.setLore(Arrays.asList(lore));

        caughtItem.setItemStack(itemStack);
    }



    public String getName() {
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