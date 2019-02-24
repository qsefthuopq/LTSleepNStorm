package io.github.leothawne.LTSleepNStorm.api.utility;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

public class SleepAPI {
	public static final void sleep(LTSleepNStormLoader plugin, FileConfiguration configuration, FileConfiguration language, Block block, Player player) {
		ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
		if(worldList.contains(player.getLocation().getWorld().getName())) {
			if(player.getLocation().getWorld().getEnvironment() != Environment.NETHER && player.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
				boolean stormPassed = false;
				boolean nightPassed = false;
				if(player.getLocation().getWorld().getTime() > 12300 && player.getLocation().getWorld().getTime() < 23850) {
					player.getLocation().getWorld().setTime(0);
					nightPassed = true;
					if(player.getLocation().getWorld().hasStorm() == true){
						player.getLocation().getWorld().setStorm(false);
						stormPassed = true;
					}
				} else {
					if(player.getLocation().getWorld().hasStorm() == true) {
						player.getLocation().getWorld().setStorm(false);
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
							runEffect(plugin, block);
						} else if(nightPassed == false && stormPassed == true) {
							players.sendMessage(ChatColor.AQUA + "" + stormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + stormTag[1]);
							runEffect(plugin, block);
						} else if(nightPassed == true && stormPassed == true) {
							players.sendMessage(ChatColor.AQUA + "" + nightStormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightStormTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(plugin, block);
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
	public static final void runEffect(LTSleepNStormLoader plugin, Block bed) {
		if(bed != null) {
			plugin.getServer().getWorld(bed.getLocation().getWorld().getName()).strikeLightningEffect(bed.getLocation());
			for(Player player : plugin.getServer().getOnlinePlayers()) {
				player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1F, 1F);
			}
		}
	}
}