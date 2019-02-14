package io.github.leothawne.LTSleepNStorm.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminEvent implements Listener {
	private static FileConfiguration configuration;
	public AdminEvent(FileConfiguration configuration) {
		AdminEvent.configuration = configuration;
	}
	@EventHandler
	public static final void onPlayerJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use") && player.hasPermission("LTSleepNStorm.admin")) {
			if(configuration.getBoolean("update.check") == true) {
				player.performCommand("sleepnstormadmin version");
			}
		}
	}
}