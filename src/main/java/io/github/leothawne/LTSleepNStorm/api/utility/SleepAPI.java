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
package io.github.leothawne.LTSleepNStorm.api.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public class SleepAPI {
	public static final void sleep(LTSleepNStorm plugin, FileConfiguration configuration, FileConfiguration language, Block block, Player player, HashMap<UUID, Integer> tiredLevel, boolean byCommand) {
		ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
		if(worldList.contains(player.getLocation().getWorld().getName())) {
			if(player.getLocation().getWorld().getEnvironment() != Environment.NETHER && player.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
				boolean stormPassed = false;
				boolean nightPassed = false;
				if(player.getLocation().getWorld().getTime() > 12300 && player.getLocation().getWorld().getTime() < 23850) {
					if(tiredLevel != null) {
						tiredLevel.put(player.getUniqueId(), 0);
					}
					player.getLocation().getWorld().setTime(0);
					nightPassed = true;
					if(player.getLocation().getWorld().hasStorm() == true){
						player.getLocation().getWorld().setStorm(false);
						stormPassed = true;
					}
					if(player.getLocation().getWorld().isThundering() == true){
						player.getLocation().getWorld().setThundering(false);
						stormPassed = true;
					}
				} else {
					if(player.getLocation().getWorld().hasStorm() == true && player.getLocation().getWorld().isThundering() == true) {
						if(tiredLevel != null) {
							tiredLevel.put(player.getUniqueId(), 0);
						}
						player.getLocation().getWorld().setStorm(false);
						player.getLocation().getWorld().setThundering(false);
						stormPassed = true;
					}
				}
				int dayCount = (int) player.getLocation().getWorld().getFullTime() / 24000;
				String dayTag = language.getString("world-day-tag");
				String[] nightTag = language.getString("world-night-passed").split("%");
				String[] stormTag = language.getString("world-storm-passed").split("%");
				String[] nightStormTag = language.getString("world-night-storm-passed").split("%");
				for(Player players : plugin.getServer().getOnlinePlayers()) {
					if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
						if(nightPassed == true && stormPassed == false) {
							players.sendMessage(ChatColor.AQUA + "" + nightTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(plugin, block, player);
						} else if(nightPassed == false && stormPassed == true) {
							players.sendMessage(ChatColor.AQUA + "" + stormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + stormTag[1]);
							runEffect(plugin, block, player);
						} else if(nightPassed == true && stormPassed == true) {
							players.sendMessage(ChatColor.AQUA + "" + nightStormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightStormTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(plugin, block, player);
						} else {
							if(byCommand == true) {
								player.sendMessage("Nope!");
							}
						}
					}
				}
			} else {
				String environment = player.getLocation().getWorld().getEnvironment().toString();
				String[] envTag = language.getString("world-environment-error").split("%");
				player.sendMessage(ChatColor.RED +  "" + envTag[0] + "" + ChatColor.GOLD + "" + environment + "" + ChatColor.RED + "" + envTag[1]);
			}
		}
	}
	private static final void runEffect(LTSleepNStorm plugin, Block bed, Player player) {
		if(bed != null) {
			plugin.getServer().getWorld(bed.getLocation().getWorld().getName()).strikeLightningEffect(bed.getLocation());
		} else {
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 6, 1));
		}
		for(Player players : plugin.getServer().getOnlinePlayers()) {
			players.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1F, 1F);
		}
	}
}