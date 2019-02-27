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
package io.github.leothawne.LTSleepNStorm;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.LTSleepNStorm.api.LTSleepNStormAPI;
import io.github.leothawne.LTSleepNStorm.api.bStats.MetricsAPI;
import io.github.leothawne.LTSleepNStorm.command.SleepCommand;
import io.github.leothawne.LTSleepNStorm.command.SleepNStormAdminCommand;
import io.github.leothawne.LTSleepNStorm.command.SleepNStormCommand;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormAdminCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.event.AdminEvent;
import io.github.leothawne.LTSleepNStorm.event.BedEvent;

/**
 * Main class.
 * 
 * @author leothawne
 *
 */
public class LTSleepNStorm extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	private final void registerEvents(Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static MetricsAPI metrics;
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onEnable() {
		myLogger.Hello();
		myLogger.info("Loading...");
		ConfigurationLoader.check(this, myLogger);
		configuration = ConfigurationLoader.load(this, myLogger);
		if(configuration.getBoolean("enable-plugin") == true) {
			MetricsLoader.init(this, myLogger, metrics);
			LanguageLoader.check(this, myLogger, configuration);
			language = LanguageLoader.load(this, myLogger, configuration);
			getCommand("sleepnstorm").setExecutor(new SleepNStormCommand(myLogger, language));
			getCommand("sleepnstorm").setTabCompleter(new SleepNStormCommandTabCompleter());
			getCommand("sleepnstormadmin").setExecutor(new SleepNStormAdminCommand(this, myLogger, configuration, language));
			getCommand("sleepnstormadmin").setTabCompleter(new SleepNStormAdminCommandTabCompleter(this, configuration));
			getCommand("sleep").setExecutor(new SleepCommand(this, myLogger, configuration, language));
			registerEvents(new AdminEvent(configuration), new BedEvent(this, configuration, language));
			Version.check(this, myLogger);
		} else {
			myLogger.severe("You choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onDisable() {
		myLogger.info("Unloading...");
	}
	/**
	 * 
	 * Method used to cast the API class.
	 * 
	 * @return The API class.
	 * 
	 */
	@SuppressWarnings("deprecation")
	public final LTSleepNStormAPI getAPI() {
		return new LTSleepNStormAPI(this, myLogger, configuration, language, metrics);
	}
}