package org.geminicraft.betterfishing.loot.impl;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.geminicraft.betterfishing.loot.Lootable;


import java.util.List;

public class CustomCreature implements Lootable {

    private String name, creatureID;
    private double maxHealth, spawnChance;
    private EntityType entity;
    private ItemStack droppedItem;
    private List<CustomItem> customItems;
    private ItemStack[] armor;


    public CustomCreature(String name, double spawnChance, EntityType entityType) {
        this.name = name;
        this.spawnChance = spawnChance;
        this.entity = entityType;


    }

    public CustomCreature(String name, String creatureID, double maxHealth, double spawnChance, EntityType entity, ItemStack droppedItem, List<CustomItem> customItems, ItemStack[] armor) {
        this.name = name;
        this.creatureID = creatureID;
        this.maxHealth = maxHealth;
        this.spawnChance = spawnChance;
        this.entity = entity;
        this.droppedItem = droppedItem;
        this.customItems = customItems;
        this.armor = armor;
    }

    private void spawn(final Location location) {
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entity);
        this.buildLivingEntity(livingEntity);
    }

    private void buildLivingEntity(LivingEntity livingEntity) {
        livingEntity.setCustomName(name);
        livingEntity.setCustomNameVisible(true);
        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        livingEntity.setHealth(maxHealth);
        livingEntity.getEquipment().setBoots(armor[0]);
        livingEntity.getEquipment().setLeggings(armor[1]);
        livingEntity.getEquipment().setChestplate(armor[2]);
        livingEntity.getEquipment().setHelmet(armor[3]);
    }

    public String getName() {
        return name;
    }

    public double getSpawnChance() {
        return spawnChance;
    }

    @Override
    public void createItem(Entity caughtEntity) {
        this.spawn(caughtEntity.getLocation());
    }
}