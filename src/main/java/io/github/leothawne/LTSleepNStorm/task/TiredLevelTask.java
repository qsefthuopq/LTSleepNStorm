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
package io.github.leothawne.LTSleepNStorm.task;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public class TiredLevelTask implements Runnable {
	private static LTSleepNStorm plugin;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> tiredLevel;
	public TiredLevelTask(LTSleepNStorm plugin, FileConfiguration language, HashMap<UUID, Integer> tiredLevel) {
		TiredLevelTask.plugin = plugin;
		TiredLevelTask.language = language;
		TiredLevelTask.tiredLevel = tiredLevel;
	}
	@Override
	public final void run() {
		for(Player player : plugin.getServer().getOnlinePlayers()) {
			tiredLevel.put(player.getUniqueId(), (tiredLevel.get(player.getUniqueId()) + 1));
			if(!player.hasPermission("LTSleepNStorm.sleep.bypass")) {
				if(tiredLevel.get(player.getUniqueId()).intValue() == 960) {
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1));
				}
				if(tiredLevel.get(player.getUniqueId()).intValue() == 1080) {
					player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 20, 2));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 2));
				}
				if(tiredLevel.get(player.getUniqueId()).intValue() > 1200) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 3));
				}
			}
		}
	}
}