package org.geminicraft.betterfishing.events;

import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.geminicraft.betterfishing.MainPlugin;
import org.geminicraft.betterfishing.hooks.MythicMobsHook;
import org.geminicraft.betterfishing.loot.CustomCreature;
import org.geminicraft.betterfishing.loot.CustomItem;
import org.geminicraft.betterfishing.loot.Lootable;
import org.geminicraft.betterfishing.loot.MythicMobsLoot;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerListener implements Listener {

    private MainPlugin mainPlugin;

    public PlayerListener(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }


    @EventHandler
    public void onPlayerFish(final PlayerFishEvent event) throws InvalidMobTypeException {
        final Player player = event.getPlayer();



        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH) ||  event.getState().equals(PlayerFishEvent.State.REEL_IN)) {

            Location entityLocation = event.getCaught().getLocation();


            Vector velocity = player.getLocation().toVector().subtract(entityLocation.toVector());
            velocity.multiply(0.1);
            velocity.setY(1);

            Entity entity = event.getCaught();
            entity.setVelocity(velocity);

            Lootable customItem = new CustomItem("Emerald", "EmeraldID", "Rare Loot", new String[] {"A custom lore line"}, null, 0.0);

//            Lootable mythicEntity = new MythicMobsLoot("SkeletalKnight");

//
//            List<Lootable> lootableList = Arrays.asList(customCreature2, customItem);
//
            List<Lootable> weightedList = new ArrayList<>();

//
//            if (RandomUtil.chanceD(4)) {
//                customItem.createItem(event.getCaught());
//            }
            mainPlugin.getMap().forEach((key, value) -> {
                if (RandomUtil.chanceD(key)) {
                    System.out.println("Do we hit this");


                    System.out.println("What is the value: " + value.getClass().toGenericString());

                    weightedList.add(value);
                }
            });

            if (weightedList.size() > 1) {
                int randomNumber = ThreadLocalRandom.current().nextInt(weightedList.size());

                Lootable spawnedLoot = weightedList.get(randomNumber);
                spawnedLoot.createItem(entity);
            } else if (weightedList.isEmpty()){
                entityLocation.getWorld().spawnEntity(entityLocation, event.getCaught().getType());
            } else {
                weightedList.get(0).createItem(entity);

                System.out.println(weightedList.get(0).getClass().toGenericString() + " creature index 0");

                Common.tell(player, "We hit the else block");
            }





//            for (Lootable customCreatureItem : lootableList) {
//                customCreatureItem.createItem(event.getCaught());
////                if (RandomUtil.chanceD(customCreatureItem.createItem())) {
////                    weightedList.add(customCreatureItem);
////                }
//            }
//
//            if (weightedList.size() > 1) {
//                int random = ThreadLocalRandom.current().nextInt(weightedList.size());
//
//                Lootable returnedCreature = weightedList.get(random);
//

                /*
                    if (item in list getMaterial == null) {
                        item.spawn
                    }
                    createCustomItem
                 */


            }



//            if (RandomUtil.chance(1)) {
//                mythicMobsHook.spawnMythicMob("SkeletalKnight", entityLocation).setVelocity(velocity);
//            } else if (RandomUtil.chance(3)) {
//                new CustomCreature("Fish Monster", "FishMonster", 30.0, 80.0, EntityType.ZOMBIE,
//                        new ItemStack(Material.LANTERN), null,
//                        new ItemStack[]{
//                                new ItemStack(Material.CHAINMAIL_BOOTS),
//                                new ItemStack(Material.CHAINMAIL_LEGGINGS),
//                                new ItemStack(Material.CHAINMAIL_CHESTPLATE),
//                                new ItemStack(Material.CHAINMAIL_HELMET)})
//                        .spawn(entityLocation).setVelocity(velocity);
//
//            } else {
//
//                CustomItem customItem = new CustomItem(Material.EMERALD, "EmeraldID", "Rare Loot", new String[] {"A custom lore line"},
//                        null, 0.0);
//                customItem.createCustomItem(event.getCaught()).setVelocity(velocity);
//                Common.tell(player, customItem.getName());
//
////                buildItem(event.getCaught()).setVelocity(velocity);
////                buildDirt(event.getCaught()).setVelocity(velocity);
//            }


//            MythicMobs mythicMobs = MythicMobs.inst();
//            MobManager mobManager = mythicMobs.getMobManager();




            /*


             */




        }

//    }

    private Entity buildMonsterEntity(final Location location, final String name) throws InvalidMobTypeException {
        return new MythicMobsHook().getMythicMobs().getAPIHelper().spawnMythicMob(name, location);
    }

    private Item buildItem(Entity caughtEntity) {
        Item caughtThing = (Item) caughtEntity;
        ItemStack newDrop = new ItemStack(Material.DIAMOND);

        caughtThing.setCustomName(Common.colorize("&6 A Fancy Diamond"));
        caughtThing.setCustomNameVisible(true);
        caughtThing.setItemStack(newDrop);



        return caughtThing;
    }

    private Item buildDirt(Entity caughtEntity) {
        Item caughtThing = (Item) caughtEntity;
        ItemStack newDrop = new ItemStack(Material.DIRT);
        caughtThing.setCustomName(Common.colorize("&1Dirt Block"));
        caughtThing.setCustomNameVisible(true);
        caughtThing.setItemStack(newDrop);

        return caughtThing;
    }

    /*
     Lootable customCreature = new CustomCreature("Fish Monster", "FishMonster", 30.0, 80.0, EntityType.ZOMBIE,
                    new ItemStack(Material.LANTERN), null,
                    new ItemStack[]{
                            new ItemStack(Material.CHAINMAIL_BOOTS),
                            new ItemStack(Material.CHAINMAIL_LEGGINGS),
                            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
                            new ItemStack(Material.CHAINMAIL_HELMET)});

            Lootable customCreature1 = new CustomCreature("Gentle Fish Monster", "FishMonster", 30.0, 80.0, EntityType.ZOMBIE,
                    new ItemStack(Material.LANTERN), null,
                    new ItemStack[]{
                            new ItemStack(Material.CHAINMAIL_BOOTS),
                            new ItemStack(Material.CHAINMAIL_LEGGINGS),
                            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
                            new ItemStack(Material.CHAINMAIL_HELMET)});

            Lootable customCreature2 = new CustomCreature("Less Gentle Fish Monster", "FishMonster", 30.0, 60.0, EntityType.ZOMBIE,
                    new ItemStack(Material.LANTERN), null,
                    new ItemStack[]{
                            new ItemStack(Material.CHAINMAIL_BOOTS),
                            new ItemStack(Material.CHAINMAIL_LEGGINGS),
                            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
                            new ItemStack(Material.CHAINMAIL_HELMET)});

     */


}