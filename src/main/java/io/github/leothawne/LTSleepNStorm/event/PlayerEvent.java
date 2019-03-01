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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.PlayersFileLoader;

public class PlayerEvent implements Listener {
	private static LTSleepNStorm plugin;
	private static ConsoleLoader myLogger;
	private static HashMap<UUID, Integer> tiredLevel;
	public PlayerEvent(LTSleepNStorm plugin, ConsoleLoader myLogger, HashMap<UUID, Integer> tiredLevel) {
		PlayerEvent.plugin = plugin;
		PlayerEvent.myLogger = myLogger;
		PlayerEvent.tiredLevel = tiredLevel;
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public static final void onPlayerJoin(PlayerJoinEvent event) {
		PlayersFileLoader.load(plugin, myLogger, event.getPlayer(), tiredLevel, false);
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public static final void onPlayerQuit(PlayerQuitEvent event) {
		PlayersFileLoader.save(plugin, myLogger, event.getPlayer(), tiredLevel, false);
	}
}