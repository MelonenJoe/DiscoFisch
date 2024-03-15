package net.melonenjoe.discofisch.command;

import org.bukkit.command.CommandExecutor;

import java.util.Collection;
import java.util.HashSet;

public class CommandManager {

    Collection<CommandExecutor> executors = new HashSet<>();

}
