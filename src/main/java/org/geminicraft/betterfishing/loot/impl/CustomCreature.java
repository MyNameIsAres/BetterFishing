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

    /*
        TODO: Fix known bug where it can't find the EntityType. Find it by name from a list of correct names.
        TODO: Optional parameters or improved null checks for all Loot classes.
     */
    public CustomCreature(String name, double maxHealth, double spawnChance) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.spawnChance = spawnChance;
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

    @Override
    public String getLootID() {
        return creatureID;
    }

    @Override
    public String getLootName() {
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