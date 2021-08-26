package org.geminicraft.betterfishing.events;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.loot.Lootable;
import org.mineacademy.fo.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.geminicraft.betterfishing.utility.Utility;

public class PlayerListener implements Listener {

    private final MainPlugin mainPlugin;

    public PlayerListener(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }


    @EventHandler
    public void onPlayerFish(final PlayerFishEvent event) {
        final Player player = event.getPlayer();

        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            // TODO: Eventually hook this up to a Logger API to notify the administrator an exception has occurred.
            if (Utility.checkNotNull(event.getCaught())) {
                return;
            }
            List<Lootable> weightedList = new ArrayList<>();
            Entity entity = event.getCaught();

            System.out.println("Entity: " + entity.getType().name());

            Location entityLocation = entity.getLocation();
            Vector velocity = player.getLocation().toVector().subtract(entityLocation.toVector());

            this.setVelocityToEntity(entity, velocity);


            mainPlugin.getMap().forEach((key, value) -> {
                // TODO Create own private (lightweight) chance class
                if (RandomUtil.chanceD(key)) {
                    weightedList.add(value);
                }
            });

            this.spawnLoot(weightedList, entity, entityLocation);
        }
    }


    private void setVelocityToEntity(Entity entity, Vector velocity) {
        velocity.multiply(0.1);
        velocity.setY(1);
        entity.setVelocity(velocity);
    }

    private void spawnLoot(List<Lootable> lootAbleList, Entity entity, Location entityLocation) {

        System.out.println("Entity in SpawnLoot: " + entity.getName());
        System.out.println("Location in SpawnLoot: " + entityLocation.toString());

        if (lootAbleList.isEmpty()) {
            entityLocation.getWorld().spawnEntity(entityLocation, entity.getType());
        } else if (lootAbleList.size() > 1) {
            int randomNumber = ThreadLocalRandom.current().nextInt(lootAbleList.size());

            Lootable spawnedLoot = lootAbleList.get(randomNumber);

            System.out.println("SpawnedLoot variable: " + spawnedLoot.toString());

            spawnedLoot.createItem(entity);
        } else {
            lootAbleList.get(0).createItem(entity);
        }
    }

}