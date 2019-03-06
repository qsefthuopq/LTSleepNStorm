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

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.api.utility.NearbyMonstersAPI;
import io.github.leothawne.LTSleepNStorm.api.utility.SleepAPI;

public class SleepCommand implements CommandExecutor {
	private static LTSleepNStorm plugin;
	private static ConsoleLoader myLogger;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> tiredLevel;
	public SleepCommand(LTSleepNStorm plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language, HashMap<UUID, Integer> tiredLevel) {
		SleepCommand.plugin = plugin;
		SleepCommand.myLogger = myLogger;
		SleepCommand.configuration = configuration;
		SleepCommand.language = language;
		SleepCommand.tiredLevel = tiredLevel;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep.command")) {
				if(tiredLevel.get(player.getUniqueId()).intValue() >= 840) {
					if(NearbyMonstersAPI.isSafe(player) == true) {
						SleepAPI.sleep(plugin, configuration, language, null, player, tiredLevel, true);
					} else {
						player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("nearby-monsters"));
					}
				} else {
					if(player.hasPermission("LTSleepNStorm.sleep.bypass")) {
						if(NearbyMonstersAPI.isSafe(player) == true) {
							SleepAPI.sleep(plugin, configuration, language, null, player, tiredLevel, true);
						} else {
							player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("nearby-monsters"));
						}
					} else {
						String[] notTired = language.getString("player-not-tired").split("%");
						int tiredTime = 840 - tiredLevel.get(player.getUniqueId()).intValue();
						player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + notTired[0] + ChatColor.GREEN + "" + tiredTime + "" + ChatColor.YELLOW + notTired[1]);
					}
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