package net.melonenjoe.discofisch.listener;

import net.melonenjoe.discofisch.manager.DiscoFishManager;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class DiscoFishListener implements Listener {

    DiscoFishManager discoFishManager = new DiscoFishManager();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {

        Player player = event.getPlayer();

        // This is a little bit critical because in the offhand and when you are fast enough to swap items, it doesn't work
        ItemStack bucket = event.getPlayer().getInventory().getItem(event.getHand());

        BlockFace blockFace = event.getBlockFace();

        if (bucket.getItemMeta().getPersistentDataContainer().has(discoFishManager.getKey())) {

            Entity entity = player.getWorld().spawnEntity(event.getBlockClicked().getLocation().add(blockFace.getModX() + 0.50, blockFace.getModY(), blockFace.getModZ() + 0.50), EntityType.TROPICAL_FISH);

            entity.getPersistentDataContainer().set(discoFishManager.getKey(), PersistentDataType.INTEGER, 0);
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        Entity entity = event.getEntity();

        EntityDamageEvent.DamageCause cause = event.getCause();

        if (entity instanceof TropicalFish) {

            if (entity.getPersistentDataContainer().has(discoFishManager.getKey()) && !cause.equals(EntityDamageEvent.DamageCause.CUSTOM)) {

                event.setCancelled(true);

            }

        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();

        if (entity instanceof TropicalFish) {

            if (entity.getPersistentDataContainer().has(discoFishManager.getKey())) {

                event.setCancelled(true);

            }

        }

    }

}
