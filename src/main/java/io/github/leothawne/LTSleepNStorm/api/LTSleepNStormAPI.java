package io.github.leothawne.LTSleepNStorm.api;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.api.bStats.MetricsAPI;
import io.github.leothawne.LTSleepNStorm.api.utility.NearbyMonstersAPI;
import io.github.leothawne.LTSleepNStorm.api.utility.SleepAPI;

/**
 * 
 * The API class.
 * 
 * @author leothawne
 * 
 */
public class LTSleepNStormAPI {
	private static LTSleepNStorm plugin;
	private static ConsoleLoader myLogger;
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static MetricsAPI metrics;
	/**
	 * 
	 * @deprecated There is no need to manually create
	 * an object with this constructor when
	 * you can easily use {@link LTSleepNStorm#getAPI()}.
	 * 
	 */
	public LTSleepNStormAPI(LTSleepNStorm plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language, MetricsAPI metrics) {
		LTSleepNStormAPI.plugin = plugin;
		LTSleepNStormAPI.myLogger = myLogger;
		LTSleepNStormAPI.configuration = configuration;
		LTSleepNStormAPI.language = language;
		LTSleepNStormAPI.metrics = metrics;
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerSafe(Player player) {
		return NearbyMonstersAPI.isSafe(player);
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerSafe(UUID playerUUID) {
		return isPlayerSafe(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #isPlayerSafe(Player)} and {@link #isPlayerSafe(UUID)}.
	 * 
	 */
	public final boolean isPlayerSafe(String playerName) {
		return isPlayerSafe(plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Returns a ConsoleLoader type value that can
	 * be used to log messages on the server console.
	 * 
	 * @return Returns a ConsoleLoader type value.
	 * 
	 */
	public final ConsoleLoader getLogger() {
		return myLogger;
	}
	/**
	 * 
	 * Returns a FileConfiguration type value that can be used
	 * to determine the current language used by the plugin.
	 * 
	 * @return Returns a FileConfiguration type value.
	 * 
	 */
	public final FileConfiguration getLanguageParameters(){
		return language;
	}
	/**
	 * 
	 * Returns a FileConfiguration type value that can be used
	 * to determine the current language used by the plugin.
	 * 
	 * @return Returns a FileConfiguration type value.
	 * 
	 * @deprecated Replaced by {@link #getLanguageParameters()}
	 * 
	 */
	public final FileConfiguration getLanguageFile(){
		return getLanguageParameters();
	}
	/**
	 * 
	 * Returns a boolean type value that can be used to determine
	 * if the plugin is currently using bStats.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isMetricsEnabled() {
		return metrics.isEnabled();
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final void makeSleep(Player player) {
		SleepAPI.sleep(plugin, configuration, language, null, player);
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final void makeSleep(UUID playerUUID) {
		makeSleep(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #makeSleep(Player)} and {@link #makeSleep(UUID)}.
	 * 
	 */
	public final void makeSleep(String playerName) {
		makeSleep(plugin.getServer().getPlayer(playerName));
	}
}