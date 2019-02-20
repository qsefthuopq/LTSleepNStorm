package io.github.leothawne.LTSleepNStorm;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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
	public final void registerEvents(Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	private FileConfiguration configuration;
	private FileConfiguration language;
	@Override
	public final void onEnable() {
		myLogger.Hello();
		myLogger.info("Loading...");
		new ConfigurationLoader(this, myLogger);
		ConfigurationLoader.check();
		new ConfigurationLoader(this, myLogger);
		configuration = ConfigurationLoader.load();
		if(configuration.getBoolean("enable-plugin") == true) {
			new MetricsLoader(this, myLogger);
			MetricsLoader.init();
			new LanguageLoader(this, myLogger, configuration);
			LanguageLoader.check();
			new LanguageLoader(this, myLogger, configuration);
			language = LanguageLoader.load();
			getCommand("sleepnstorm").setExecutor(new SleepNStormCommand(this, myLogger, language));
			getCommand("sleepnstorm").setTabCompleter(new SleepNStormCommandTabCompleter());
			getCommand("sleepnstormadmin").setExecutor(new SleepNStormAdminCommand(this, myLogger, configuration, language));
			getCommand("sleepnstormadmin").setTabCompleter(new SleepNStormAdminCommandTabCompleter(this, configuration));
			registerEvents(new AdminEvent(configuration), new BedEvent(this, configuration, language));
			new Version(this, myLogger);
			Version.check();
		} else {
			myLogger.severe("You manually choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public final void onDisable() {
		myLogger.info("Unloading...");
	}
}