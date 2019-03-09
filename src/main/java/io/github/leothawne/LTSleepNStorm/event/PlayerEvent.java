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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.PlayersFileLoader;
import io.github.leothawne.LTSleepNStorm.item.BottleOfCoffeeItem;

public class PlayerEvent implements Listener {
	private static LTSleepNStorm plugin;
	private static ConsoleLoader myLogger;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> tiredLevel;
	private static HashMap<UUID, Integer> afkLevel;
	public PlayerEvent(LTSleepNStorm plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language, HashMap<UUID, Integer> tiredLevel, HashMap<UUID, Integer> afkLevel) {
		PlayerEvent.plugin = plugin;
		PlayerEvent.myLogger = myLogger;
		PlayerEvent.configuration = configuration;
		PlayerEvent.language = language;
		PlayerEvent.tiredLevel = tiredLevel;
		PlayerEvent.afkLevel = afkLevel;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerJoin(PlayerJoinEvent event) {
		PlayersFileLoader.load(plugin, myLogger, event.getPlayer(), tiredLevel, false);
		afkLevel.put(event.getPlayer().getUniqueId(), 0);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerQuit(PlayerQuitEvent event) {
		PlayersFileLoader.save(plugin, myLogger, event.getPlayer(), tiredLevel, false);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerConsume(PlayerItemConsumeEvent event) {
		if(event.getItem().equals(BottleOfCoffeeItem.getItemStack())) {
			tiredLevel.put(event.getPlayer().getUniqueId(), (tiredLevel.get(event.getPlayer().getUniqueId()) - 350));
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
			if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
			}
			afkLevel.put(player.getUniqueId(), 0);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
			if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
			}
			afkLevel.put(player.getUniqueId(), 0);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
			if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
			}
			afkLevel.put(player.getUniqueId(), 0);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
			if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
				player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
			}
			afkLevel.put(player.getUniqueId(), 0);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onPickupItem(EntityPickupItemEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
				if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
				}
				afkLevel.put(player.getUniqueId(), 0);
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if(player.hasPlayedBefore()) {
			if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
				if(afkLevel.get(player.getUniqueId()) >= configuration.getInt("auto-restmode")) {
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-deactivated"));
				}
				afkLevel.put(player.getUniqueId(), 0);
			}
		}
	}
}