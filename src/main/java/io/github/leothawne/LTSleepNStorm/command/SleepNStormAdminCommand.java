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

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.Version;
import io.github.leothawne.LTSleepNStorm.api.utility.HTTP;

public class SleepNStormAdminCommand implements CommandExecutor {
	private LTSleepNStorm plugin;
	private ConsoleLoader myLogger;
	private FileConfiguration configuration;
	private FileConfiguration language;
	public SleepNStormAdminCommand(LTSleepNStorm plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language) {
		this.plugin = plugin;
		this.myLogger = myLogger;
		this.configuration = configuration;
		this.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(sender.hasPermission("LTSleepNStorm.admin")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.AQUA + "=+=+=+= [LT Sleep N Storm :: Admin] =+=+=+=");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.AQUA + "- Shows all administration commands for LT Sleep N Storm.");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin version " + ChatColor.AQUA + "- Checks for new updates.");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin timereset " + ChatColor.AQUA + "- Sets the world you are standing in to day 0.");
					sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/sleepnstormadmin "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/snsa"+ ChatColor.YELLOW + ".");
				} else if(args[0].equalsIgnoreCase("version")) {
					if(args.length < 2) {
						new BukkitRunnable() {
							@Override
							public final void run() {
								String[] LocalVersion = Version.getVersionNumber().split("\\.");
								int Local_VersionNumber1 = Integer.parseInt(LocalVersion[0]);
								int Local_VersionNumber2 = Integer.parseInt(LocalVersion[1]);
								int Local_VersionNumber3 = Integer.parseInt(LocalVersion[2]);
								String upToDate = ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "The plugin is up to date!";
								String[] Server1 = HTTP.getData(Version.getUpdateURL()).split("-");
								if(Server1[2].equals(Version.getMinecraftVersion())) {
									String[] Server2 = Server1[0].split("\\.");
									int Server2_VersionNumber1 = Integer.parseInt(Server2[0]);
									int Server2_VersionNumber2 = Integer.parseInt(Server2[1]);
									int Server2_VersionNumber3 = Integer.parseInt(Server2[2]);
									String updateMessage = ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "A newer version is available: " + ChatColor.GREEN + "" + Server1[0] + "" + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + "" + Server1[1] + "" + ChatColor.YELLOW + ").";
									if(Server2_VersionNumber1 > Local_VersionNumber1) {
										sender.sendMessage(updateMessage);
									} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 > Local_VersionNumber2) {
										sender.sendMessage(updateMessage);
									} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 == Local_VersionNumber2 && Server2_VersionNumber3 > Local_VersionNumber3) {
										sender.sendMessage(updateMessage);
									} else {
										sender.sendMessage(upToDate);
									}
								} else {
									sender.sendMessage(upToDate);
								}
							}
						}.runTask(plugin);
					} else {
						sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else if(args[0].equalsIgnoreCase("timereset")) {
					if(args.length < 2) {
						if(sender instanceof Player) {
							Player player = (Player) sender;
							ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
							if(worldList.contains(player.getLocation().getWorld().getName())) {
								player.getLocation().getWorld().setFullTime(0);
								String dayTag = language.getString("world-day-tag");
								for(Player players : plugin.getServer().getOnlinePlayers()) {
									if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
										players.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
										players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
									}
								}
							} else {
								player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("world-blocked"));
							}
						} else {
							for(World world : plugin.getServer().getWorlds()) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								}
							}
							
						}
					} else if(args.length < 3) {
						if(args[1].equals("*")) {
							for(World world : plugin.getServer().getWorlds()) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								}
							}
						} else {
							World world = plugin.getServer().getWorld(args[1]);
							if(world != null) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								} else {
									sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("world-blocked"));
								}
							} else {
								String[] notFoundTag = language.getString("world-not-found").split("%");
								sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + notFoundTag[0] + "" + ChatColor.GREEN + "" + args[1] + "" + ChatColor.YELLOW + "" + notFoundTag[1]);
							}
						}
					} else {
						sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else {
					sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.YELLOW + "to see all available commands.");
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
				myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.admin].");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.use].");
		}
		return true;
	}
}