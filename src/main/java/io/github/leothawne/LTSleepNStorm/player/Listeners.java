package io.github.leothawne.LTSleepNStorm.player;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

public class Listeners implements Listener {
	private LTSleepNStormLoader plugin;
	private FileConfiguration configuration;
	public Listeners(LTSleepNStormLoader plugin, FileConfiguration configuration) {
		this.plugin = plugin;
		this.configuration = configuration;
	}
	private String getSleepMessage(int playersSleeping, int playersNeeded, int playersOnline) {
		return "Dormindo: [" + playersSleeping + "/" + playersNeeded + "/" + playersOnline + "]";
	}
	@EventHandler
	public void onPlayerSleep(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		World world = player.getLocation().getWorld();
		ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
		if(worldList.contains(world.getName())) {
			if(world.getEnvironment() != Environment.NETHER && world.getEnvironment() != Environment.THE_END) {
				int playersOnline = 0;
				int playersSleeping = 0;
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getLocation().getWorld().getName().equalsIgnoreCase(world.getName())) {
						playersOnline++;
						if(players.isSleeping()) {
							playersSleeping++;
						}
					}
				}
				Double playersNeeded1 = playersOnline * 0.5;
				int playersNeeded2 = playersNeeded1.intValue();
				int playersNeeded3 = 1;
				if(playersNeeded2 > 0) {
					playersNeeded3 = playersNeeded2;
				}
				if(world.getTime() < 12300 || world.getTime() > 23850) {
					player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
				}
				if(world.hasStorm()) {
					player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
				}
				if(world.isThundering()) {
					player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
				}
				if(playersSleeping >= playersNeeded3) {
					if(world.getTime() < 12300 || world.getTime() > 23850) {
						player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
						for(World worlds : Bukkit.getWorlds()) {
							if(worlds.getName().equalsIgnoreCase(world.getName())) {
								worlds.setTime(0);
							}
						}
					}
					if(world.hasStorm()) {
						player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
						for(World worlds : Bukkit.getWorlds()) {
							if(worlds.getName().equalsIgnoreCase(world.getName())) {
								worlds.setStorm(false);
							}
						}
					}
					if(world.isThundering()) {
						player.sendMessage(getSleepMessage(playersSleeping, playersNeeded3, playersOnline));
						for(World worlds : Bukkit.getWorlds()) {
							if(worlds.getName().equalsIgnoreCase(world.getName())) {
								worlds.setThundering(false);
							}
						}
					}
				}
			}
		}
	}
}