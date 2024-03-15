package net.melonenjoe.discofisch.manager;

import net.melonenjoe.discofisch.Main;
import org.bukkit.NamespacedKey;

public class DiscoFishManager {

    NamespacedKey key = new NamespacedKey(Main.getInstance(), "Disco-Fish");

    public NamespacedKey getKey() {

        return key;

    }

}
