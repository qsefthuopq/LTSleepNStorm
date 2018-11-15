package io.github.leothawne.LTSleepNStorm.player;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Listeners implements Listener {
	private FileConfiguration configuration;
	public Listeners(FileConfiguration configuration) {
		this.configuration = configuration;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.sleep")) {
			if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking() == false){
				Block block = event.getClickedBlock();
				if(block.getType().toString().contains("_BED")) {
					ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
					if(worldList.contains(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getName())) {
						if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getEnvironment() != Environment.NETHER && Bukkit.getWorld(player.getLocation().getWorld().getUID()).getEnvironment() != Environment.THE_END) {
							if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).getTime() > 12300 && Bukkit.getWorld(player.getLocation().getWorld().getUID()).getTime() < 23850) {
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setTime(0);
							}
							if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).hasStorm()) {
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setStorm(false);
							}
							if(Bukkit.getWorld(player.getLocation().getWorld().getUID()).isThundering()) {
								Bukkit.getWorld(player.getLocation().getWorld().getUID()).setThundering(false);
							}
						}
					}
				}
			}
		}
	}
}