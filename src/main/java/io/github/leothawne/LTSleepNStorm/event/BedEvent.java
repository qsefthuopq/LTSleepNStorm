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

import org.bukkit.block.Block;
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
	public BedEvent(LTSleepNStorm plugin, FileConfiguration configuration, FileConfiguration language) {
		BedEvent.plugin = plugin;
		BedEvent.configuration = configuration;
		BedEvent.language = language;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static final void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
			if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking() == false){
				Block block = event.getClickedBlock();
				if(block.getType().toString().contains("_BED")) {
					if(NearbyMonstersAPI.isSafe(player) == true) {
						SleepAPI.sleep(plugin, configuration, language, block, player);
					}
				}
			}
		}
	}
}