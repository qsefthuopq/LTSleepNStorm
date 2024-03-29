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
package io.github.leothawne.LTSleepNStorm.event;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Bed.Part;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.api.utility.NearbyMonstersAPI;
import io.github.leothawne.LTSleepNStorm.api.utility.SleepAPI;

public class BedEvent implements Listener {
	private static LTSleepNStorm plugin;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> tiredLevel;
	public BedEvent(LTSleepNStorm plugin, FileConfiguration configuration, FileConfiguration language, HashMap<UUID, Integer> tiredLevel) {
		BedEvent.plugin = plugin;
		BedEvent.configuration = configuration;
		BedEvent.language = language;
		BedEvent.tiredLevel = tiredLevel;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
			if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK){
				Block block = event.getClickedBlock();
				if(block.getType().toString().contains("_BED")) {
					Bed bedData = (Bed) block.getBlockData();
					boolean isObstructed = false;
					if(bedData.getFacing().equals(BlockFace.EAST)) {
						if(bedData.getPart().equals(Part.HEAD)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() - 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
						if(bedData.getPart().equals(Part.FOOT)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() + 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
					}
					if(bedData.getFacing().equals(BlockFace.NORTH)) {
						if(bedData.getPart().equals(Part.HEAD)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() + 1)).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
						if(bedData.getPart().equals(Part.FOOT)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() - 1)).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
					}
					if(bedData.getFacing().equals(BlockFace.WEST)) {
						if(bedData.getPart().equals(Part.HEAD)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() + 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
						if(bedData.getPart().equals(Part.FOOT)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() - 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
					}
					if(bedData.getFacing().equals(BlockFace.SOUTH)) {
						if(bedData.getPart().equals(Part.HEAD)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() - 1)).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
						if(bedData.getPart().equals(Part.FOOT)) {
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
							if(new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() + 1)).getBlock().getType().equals(Material.AIR) == false) {
								isObstructed = true;
							}
						}
					}
					if(isObstructed == false) {
						if(player.getLocation().distance(block.getLocation()) <= 3.5) {
							if(tiredLevel.get(player.getUniqueId()).intValue() >= 840) {
								if(NearbyMonstersAPI.isSafe(player) == true) {
									SleepAPI.sleep(plugin, configuration, language, block, player, tiredLevel, false);
								} else {
									event.setCancelled(true);
								}
							} else {
								if(player.hasPermission("LTSleepNStorm.sleep.bypass")) {
									if(NearbyMonstersAPI.isSafe(player) == true) {
										SleepAPI.sleep(plugin, configuration, language, block, player, tiredLevel, false);
									} else {
										event.setCancelled(true);
									}
								} else {
									String[] notTired = language.getString("player-not-tired").split("%");
									int tiredTime = 840 - tiredLevel.get(player.getUniqueId()).intValue();
									player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + notTired[0] + ChatColor.GREEN + "" + tiredTime + "" + ChatColor.YELLOW + notTired[1]);
									event.setCancelled(true);
								}
							}
						}
					} else {
						player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + language.getString("bed-obstructed"));
						event.setCancelled(true);
					}
				}
			}
		}
	}
}