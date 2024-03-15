package net.melonenjoe.discofisch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.melonenjoe.discofisch.command.DiscoFishCommand;
import net.melonenjoe.discofisch.listener.DiscoFishListener;
import net.melonenjoe.discofisch.manager.DiscoFishManager;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TropicalFish;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Main extends JavaPlugin {

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

    Server server = Bukkit.getServer();

    Random random = new Random();

    @Override
    public void onEnable() {

        server.getPluginManager().registerEvents(new DiscoFishListener(), this);

        getCommand("discofisch").setExecutor(new DiscoFishCommand());

        List<DyeColor> colors = new ArrayList<>();

        for (DyeColor color : DyeColor.values()) {

            colors.add(color);

        }

        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {

            DiscoFishManager discoFishManager = new DiscoFishManager();

            for (Entity fish : Bukkit.getWorlds().get(0).getEntitiesByClasses(TropicalFish.class)) {

                if (fish.getPersistentDataContainer().has(discoFishManager.getKey())) {

                    TropicalFish realFish = (TropicalFish) fish;

                    realFish.setBodyColor(colors.get(random.nextInt(15)));

                    realFish.setPatternColor(colors.get(random.nextInt(15)));

                    realFish.customName(Component.text("§lDisco Fisch").color(TextColor.color(random.nextInt(150) + 50, random.nextInt(150) + 50, random.nextInt(150) + 50)));

                }

            }

        }, 40L, 10L);

    }

    @Override
    public void onDisable() {

    }
}
