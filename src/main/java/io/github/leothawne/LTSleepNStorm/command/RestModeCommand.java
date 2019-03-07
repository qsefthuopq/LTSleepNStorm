package io.github.leothawne.LTSleepNStorm.command;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;

public class RestModeCommand implements CommandExecutor {
	private static ConsoleLoader myLogger;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> afkLevel;
	public RestModeCommand(ConsoleLoader myLogger, FileConfiguration language, HashMap<UUID, Integer> afkLevel) {
		RestModeCommand.myLogger = myLogger;
		RestModeCommand.language = language;
		RestModeCommand.afkLevel = afkLevel;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
				if(afkLevel.get(player.getUniqueId()).intValue() < 300) {
					afkLevel.put(player.getUniqueId(), 300);
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-activated"));
				} else if(afkLevel.get(player.getUniqueId()).intValue() >= 150) {
					afkLevel.put(player.getUniqueId(), 0);
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
				}
			} else {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
				myLogger.severe(player.getName() + " does not have permission [LTSleepNStorm.use, LTSleepNStorm.sleep].");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-error"));
		}
		return true;
	}
}