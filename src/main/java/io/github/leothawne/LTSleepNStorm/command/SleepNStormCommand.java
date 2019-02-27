/*
 * Copyright (C) 2019 Murilo Amaral Nappi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.LTSleepNStorm.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.Version;

public class SleepNStormCommand implements CommandExecutor {
	private ConsoleLoader myLogger;
	private FileConfiguration language;
	public SleepNStormCommand(ConsoleLoader myLogger, FileConfiguration language) {
		this.myLogger = myLogger;
		this.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [LT Sleep N Storm] =+=+=+=");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm " + ChatColor.AQUA + "- Shows all commands for LT Sleep N Storm.");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm version " + ChatColor.AQUA + "- Shows the plugin version.");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.AQUA + "- Shows the administration commands for LT Sleep N Storm.");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/sleepnstorm "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/sns"+ ChatColor.YELLOW + ".");
			} else if(args[0].equalsIgnoreCase("version")) {
				if(args.length < 2) {
					Version.version(sender);
				} else {
					sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/sleepnstorm " + ChatColor.YELLOW + "to see all available commands.");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.use].");
		}
		return true;
	}
}