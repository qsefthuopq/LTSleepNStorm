package io.github.leothawne.LTSleepNStorm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.LTSleepNStorm.commands.SleepNStormAdminCommands;
import io.github.leothawne.LTSleepNStorm.commands.SleepNStormCommands;
import io.github.leothawne.LTSleepNStorm.commands.constructor.SleepNStormAdminConstructTabCompleter;
import io.github.leothawne.LTSleepNStorm.commands.constructor.SleepNStormConstructTabCompleter;
import io.github.leothawne.LTSleepNStorm.player.Listeners;

public class LTSleepNStormLoader extends JavaPlugin {
	private ConsoleLoader myLogger = new ConsoleLoader(this);
	public static void registerEvents(LTSleepNStormLoader plugin, Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	private FileConfiguration configuration = null;
	@Override
	public void onEnable() {
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[LTSleepNStorm] " + ChatColor.LIGHT_PURPLE + "Loading...");
		}
		myLogger.Hello();
		myLogger.info("Loading...");
		new ConfigurationLoader(this, myLogger).check();
		configuration = new ConfigurationLoader(this, myLogger).load();
		if(configuration.getBoolean("enable-plugin") == true) {
			getCommand("sleepnstorm").setExecutor(new SleepNStormCommands(this, myLogger));
			getCommand("sleepnstorm").setTabCompleter(new SleepNStormConstructTabCompleter());
			getCommand("sleepnstormadmin").setExecutor(new SleepNStormAdminCommands(this, myLogger));
			getCommand("sleepnstormadmin").setTabCompleter(new SleepNStormAdminConstructTabCompleter());
			registerEvents(this, new Listeners(this, configuration));
			new Version(this, myLogger).check();
			myLogger.warning("A permissions plugin is required! Just make sure you are using one. Permissions nodes can be found at: https://leothawne.github.io/LTSleepNStorm/permissions.html");
			for(Player player : this.getServer().getOnlinePlayers()) {
				player.sendMessage(ChatColor.AQUA + "[LTSleepNStorm] " + ChatColor.LIGHT_PURPLE + "Loaded!");
			}
		} else {
			myLogger.severe("You manually choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public void onDisable() {
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[LTSleepNStorm] " + ChatColor.LIGHT_PURPLE + "Unloading...");
		}
		myLogger.info("Unloading...");
		myLogger.Goodbye();
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[LTSleepNStorm] " + ChatColor.LIGHT_PURPLE + "Unloaded!");
		}
	}
}