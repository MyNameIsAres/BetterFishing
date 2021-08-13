package org.geminicraft.betterfishing.loot;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;


import java.util.List;

public class CustomCreature implements Lootable {

    private String name, creatureID;
    private double maxHealth, spawnChance;
    private EntityType entity;
    private ItemStack droppedItem;
    private List<CustomItem> customItems;
    private ItemStack[] armor;

    public CustomCreature(String name, double spawnChance) {
        this.name = name;
        this.spawnChance = spawnChance;
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

    private LivingEntity spawn(final Location location) {
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entity);
        return this.buildLivingEntity(livingEntity);

    }

    private LivingEntity buildLivingEntity(LivingEntity livingEntity) {
        livingEntity.setCustomName(name);
        livingEntity.setCustomNameVisible(true);
        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        livingEntity.setHealth(maxHealth);
        livingEntity.getEquipment().setBoots(armor[0]);
        livingEntity.getEquipment().setLeggings(armor[1]);
        livingEntity.getEquipment().setChestplate(armor[2]);
        livingEntity.getEquipment().setHelmet(armor[3]);

        return livingEntity;
    }

    public String getName() {
        return name;
    }

    public double getSpawnChance() {
        return spawnChance;
    }

    @Override
    public void createItem(Entity caughtEntity) {
        System.out.println("Is this used");
        this.spawn(caughtEntity.getLocation());
    }
}