package io.github.leothawne.LTSleepNStorm.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

public class BedEvents implements Listener {
	private static LTSleepNStormLoader plugin;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	public BedEvents(LTSleepNStormLoader plugin, FileConfiguration configuration, FileConfiguration language) {
		BedEvents.plugin = plugin;
		BedEvents.configuration = configuration;
		BedEvents.language = language;
	}
	public static final void bedEffect(Block bed) {
		plugin.getServer().getWorld(bed.getLocation().getWorld().getName()).strikeLightningEffect(bed.getLocation());
	}
	@EventHandler
	public static final void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
			if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking() == false){
				Block block = event.getClickedBlock();
				if(block.getType().toString().contains("_BED")) {
					ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
					if(worldList.contains(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getName())) {
						if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getEnvironment() != Environment.NETHER && Bukkit.getWorld(player.getLocation().getWorld().getUID()).getEnvironment() != Environment.THE_END) {
							boolean stormPassed = false;
							boolean nightPassed = false;
							if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getTime() > 12300 && Bukkit.getWorld(player.getLocation().getWorld().getUID()).getTime() < 23850) {
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setTime(0);
								nightPassed = true;
								if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).hasStorm() == true){
									Bukkit.getWorld(player.getLocation().getWorld().getUID()).setStorm(false);
									stormPassed = true;
								}
								if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).isThundering() == true){
									Bukkit.getWorld(player.getLocation().getWorld().getUID()).setThundering(false);
									stormPassed = true;
								}
							}
							if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).hasStorm() == true && Bukkit.getWorld(player.getLocation().getWorld().getUID()).isThundering() == true) {
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setStorm(false);
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setThundering(false);
								stormPassed = true;
							}
							int dayCount = (int) Bukkit.getWorld(player.getLocation().getWorld().getUID()).getFullTime() / 24000;
							String dayTag = language.getString("world-day-tag");
							String[] nightTag = language.getString("world-night-passed").split("%");
							String[] stormTag = language.getString("world-storm-passed").split("%");
							String[] nightStormTag = language.getString("world-night-storm-passed").split("%");
							for(Player players : plugin.getServer().getOnlinePlayers()) {
								if(players.getLocation().getWorld().equals(Bukkit.getWorld(player.getLocation().getWorld().getUID()))) {
									if(nightPassed == true && stormPassed == false) {
										players.sendMessage(ChatColor.AQUA + "" + nightTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightTag[1]);
										players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
										bedEffect(block);
									} else if(nightPassed == false && stormPassed == true) {
										players.sendMessage(ChatColor.AQUA + "" + stormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + stormTag[1]);
									} else if(nightPassed == true && stormPassed == true) {
										players.sendMessage(ChatColor.AQUA + "" + nightStormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightStormTag[1]);
										players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
										bedEffect(block);
									}
								}
							}
							
						} else {
							String environment = Bukkit.getWorld(player.getLocation().getWorld().getUID()).getEnvironment().toString();
							String[] envTag = language.getString("world-environment-error").split("%");
							player.sendMessage(ChatColor.RED +  "" + envTag[0] + "" + ChatColor.GOLD + "" + environment + "" + ChatColor.RED + "" + envTag[1]);
						}
					}
				}
			}
		}
	}
}