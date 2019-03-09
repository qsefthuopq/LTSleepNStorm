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
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public class TiredLevelTask implements Runnable {
	private static LTSleepNStorm plugin;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static HashMap<UUID, Integer> tiredLevel;
	private static HashMap<UUID, Integer> afkLevel;
	public TiredLevelTask(LTSleepNStorm plugin, FileConfiguration configuration, FileConfiguration language, HashMap<UUID, Integer> tiredLevel, HashMap<UUID, Integer> afkLevel) {
		TiredLevelTask.plugin = plugin;
		TiredLevelTask.configuration = configuration;
		TiredLevelTask.language = language;
		TiredLevelTask.tiredLevel = tiredLevel;
		TiredLevelTask.afkLevel = afkLevel;
	}
	@Override
	public final void run() {
		for(Player player : plugin.getServer().getOnlinePlayers()) {
			if(player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) {
				if(player.hasPermission("LTSleepNStorm.sleep.bypass") == false) {
					afkLevel.put(player.getUniqueId(), (afkLevel.get(player.getUniqueId()) + 1));
					if(afkLevel.get(player.getUniqueId()).intValue() < configuration.getInt("auto-restmode")) {
						tiredLevel.put(player.getUniqueId(), (tiredLevel.get(player.getUniqueId()) + 1));
						if(tiredLevel.get(player.getUniqueId()).intValue() < 5) {
							if(player.hasPotionEffect(PotionEffectType.SLOW)) {
								player.removePotionEffect(PotionEffectType.SLOW);
							}
							if(player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
								player.removePotionEffect(PotionEffectType.BLINDNESS);
							}
						}
						if(tiredLevel.get(player.getUniqueId()).intValue() == 2040) {
							player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
							player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 1));
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1));
						}
						if(tiredLevel.get(player.getUniqueId()).intValue() == 2400) {
							player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
							player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 20, 2));
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 2));
						}
						if(tiredLevel.get(player.getUniqueId()).intValue() > 3000) {
							if(player.hasPotionEffect(PotionEffectType.SLOW) == false) {
								player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
								player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
								player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 999999, 3));
							}
						}
						if(tiredLevel.get(player.getUniqueId()).intValue() > 3200) {
							if(player.hasPotionEffect(PotionEffectType.BLINDNESS) == false) {
								player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("player-very-tired"));
								player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
								player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 999999, 3));
							}
						}
					} else {
						if(afkLevel.get(player.getUniqueId()).intValue() == configuration.getInt("auto-restmode")) {
							player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("afk-activated"));
						}
					}
				}
			}
		}
	}
}