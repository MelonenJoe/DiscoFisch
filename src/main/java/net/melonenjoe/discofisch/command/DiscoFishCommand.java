package net.melonenjoe.discofisch.command;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.melonenjoe.discofisch.manager.DiscoFishManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.StringUtil;

import java.util.*;

public class DiscoFishCommand implements CommandExecutor, TabCompleter {

    DiscoFishManager discoFishManager = new DiscoFishManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Dieser Befehl kann nur von Spielern verwendet werden.").color(TextColor.color(252, 3, 94)));
            return true;
        }

        if (!(args.length > 0)) {

            if (!(player.getInventory().firstEmpty() == -1)) {

                ItemStack fishBucket = new ItemStack(Material.TROPICAL_FISH_BUCKET);
                ItemMeta meta = fishBucket.getItemMeta();

                List<Component> lore = new ArrayList<>();
                lore.add(Component.text(""));
                lore.add(Component.text(" §8» §7Dieser Eimer ist unendlich"));
                lore.add(Component.text("    §7oft verwendbar!"));
                lore.add(Component.text(""));

                meta.getPersistentDataContainer().set(discoFishManager.getKey(), PersistentDataType.INTEGER, 0);
                meta.setDisplayName(IridiumColorAPI.process("§8§l[<SOLID:4ceb34>§lDisco§8§l] <SOLID:eb3489>§lFisch"));
                meta.lore(lore);

                fishBucket.setItemMeta(meta);

                player.getInventory().addItem(fishBucket);

            } else {

                player.sendMessage(Component.text("Das Item kann dir nicht gegeben werden da dein Inventar voll ist!").color(TextColor.color(252, 3, 94)));

            }

        } else {

            player.sendMessage(Component.text("Verwendung: /discofisch").color(TextColor.color(252, 3, 94)));

        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        return StringUtil.copyPartialMatches(args[args.length - 1], Collections.emptyList(), new ArrayList<>());

    }
}
