package io.github.leothawne.LTSleepNStorm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.LTSleepNStorm.command.SleepNStormAdminCommand;
import io.github.leothawne.LTSleepNStorm.command.SleepNStormCommand;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormAdminCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.event.AdminEvent;
import io.github.leothawne.LTSleepNStorm.event.BedEvent;

public class LTSleepNStormLoader extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	public static final void registerEvents(LTSleepNStormLoader plugin, Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	private FileConfiguration configuration;
	private FileConfiguration language;
	@Override
	public final void onEnable() {
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[LTSleepNStorm] " + ChatColor.LIGHT_PURPLE + "Loading...");
		}
		myLogger.Hello();
		new MetricsLoader(this, myLogger);
		MetricsLoader.init();
		myLogger.info("Loading...");
		new ConfigurationLoader(this, myLogger);
		ConfigurationLoader.check();
		new ConfigurationLoader(this, myLogger);
		configuration = ConfigurationLoader.load();
		new LanguageLoader(this, myLogger, configuration);
		LanguageLoader.check();
		new LanguageLoader(this, myLogger, configuration);
		language = LanguageLoader.load();
		if(configuration.getBoolean("enable-plugin") == true) {
			getCommand("sleepnstorm").setExecutor(new SleepNStormCommand(this, myLogger, language));
			getCommand("sleepnstorm").setTabCompleter(new SleepNStormCommandTabCompleter());
			getCommand("sleepnstormadmin").setExecutor(new SleepNStormAdminCommand(this, myLogger, configuration, language));
			getCommand("sleepnstormadmin").setTabCompleter(new SleepNStormAdminCommandTabCompleter(this, configuration));
			registerEvents(this, new AdminEvent(configuration), new BedEvent(this, configuration, language));
			new Version(this, myLogger);
			Version.check();
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
	public final void onDisable() {
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