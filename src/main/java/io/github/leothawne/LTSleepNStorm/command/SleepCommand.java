/*
 * Copyright (C) 2019 Murilo Amaral Nappi (murilonappi@gmail.com)
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
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;
import io.github.leothawne.LTSleepNStorm.api.utility.NearbyMonstersAPI;
import io.github.leothawne.LTSleepNStorm.api.utility.SleepAPI;

public class SleepCommand implements CommandExecutor {
	private static LTSleepNStormLoader plugin;
	private static ConsoleLoader myLogger;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	public SleepCommand(LTSleepNStormLoader plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language) {
		SleepCommand.plugin = plugin;
		SleepCommand.myLogger = myLogger;
		SleepCommand.configuration = configuration;
		SleepCommand.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
				if(NearbyMonstersAPI.isSafe(player) == true) {
					SleepAPI.sleep(plugin, configuration, language, null, player);
				} else {
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("nearby-monsters"));
				}
			} else {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
				myLogger.severe(player.getName() + " does not have permission [LTSleepNStorm.use].");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-error"));
		}
		return true;
	}
}