package io.github.leothawne.LTSleepNStorm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;
import io.github.leothawne.LTSleepNStorm.Version;

public class SleepNStormCommands implements CommandExecutor {
	private LTSleepNStormLoader plugin;
	private ConsoleLoader myLogger;
	public SleepNStormCommands(LTSleepNStormLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [LT Sleep N Storm] =+=+=+=");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm " + ChatColor.AQUA + "- Show all commands for Sleep N Storm.");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm version " + ChatColor.AQUA + "- Show plugin version.");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.AQUA + "- Administration commands for Sleep N Storm.");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/sleepnstorm "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/sns"+ ChatColor.YELLOW + ".");
			} else if(args[0].equalsIgnoreCase("version")) {
				if(args.length < 2) {
					new Version(plugin, myLogger).version(sender);
				} else {
					sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "Too many arguments!");
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/sleepnstorm " + ChatColor.YELLOW + "to see all available commands.");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "You can't do that! You don't have permission.");
			myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.use].");
		}
		return true;
	}
}