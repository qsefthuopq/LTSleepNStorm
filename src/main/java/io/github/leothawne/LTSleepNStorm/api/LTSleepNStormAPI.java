package io.github.leothawne.LTSleepNStorm.api;

import java.util.HashMap;
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
	private static HashMap<UUID, Integer> tiredLevel;
	private static HashMap<UUID, Integer> afkLevel;
	/**
	 * 
	 * @deprecated There is no need to manually create
	 * an object with this constructor when
	 * you can easily use {@link LTSleepNStorm#getAPI()}.
	 * 
	 */
	public LTSleepNStormAPI(LTSleepNStorm plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language, MetricsAPI metrics, HashMap<UUID, Integer> tiredLevel, HashMap<UUID, Integer> afkLevel) {
		LTSleepNStormAPI.plugin = plugin;
		LTSleepNStormAPI.myLogger = myLogger;
		LTSleepNStormAPI.configuration = configuration;
		LTSleepNStormAPI.language = language;
		LTSleepNStormAPI.metrics = metrics;
		LTSleepNStormAPI.tiredLevel = tiredLevel;
		LTSleepNStormAPI.afkLevel = afkLevel;
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
	 * @return A ConsoleLoader type value.
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
	 * @return A FileConfiguration type value.
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
	 * @return A FileConfiguration type value.
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
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param player The Player type variable.
	 * 
	 */
	public final void makeSleep(Player player) {
		if(NearbyMonstersAPI.isSafe(player) == true) {
			SleepAPI.sleep(plugin, configuration, language, null, player, null, false);
		}
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 */
	public final void makeSleep(UUID playerUUID) {
		makeSleep(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @deprecated Replaced by {@link #makeSleep(Player)} and {@link #makeSleep(UUID)}.
	 * 
	 */
	public final void makeSleep(String playerName) {
		makeSleep(plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param player The Player type variable.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 */
	public final void makeSleep(Player player, boolean ignoreNearbyMonsters) {
		if(ignoreNearbyMonsters == false) {
			makeSleep(player);
		} else {
			SleepAPI.sleep(plugin, configuration, language, null, player, null, false);
		}
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerUUID The player's unique id.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 */
	public final void makeSleep(UUID playerUUID, boolean ignoreNearbyMonsters) {
		makeSleep(plugin.getServer().getPlayer(playerUUID), ignoreNearbyMonsters);
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerName The player's name.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 * @deprecated Replaced by {@link #makeSleep(Player, boolean)} and {@link #makeSleep(UUID, boolean)}.
	 * 
	 */
	public final void makeSleep(String playerName, boolean ignoreNearbyMonsters) {
		makeSleep(plugin.getServer().getPlayer(playerName), ignoreNearbyMonsters);
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is already tired.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerTired(Player player) {
		if(tiredLevel.get(player.getUniqueId()).intValue() >= 840) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is already tired.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerTired(UUID playerUUID) {
		return isPlayerTired(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is already tired.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #isPlayerTired(Player)} or {@link #isPlayerTired(UUID)}.
	 * 
	 */
	public final boolean isPlayerTired(String playerName) {
		return isPlayerTired(plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Returns the tired level of a player.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final int getTiredLevel(Player player) {
		return tiredLevel.get(player.getUniqueId()).intValue();
	}
	/**
	 * 
	 * Returns the tired level of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final int getTiredLevel(UUID playerUUID) {
		return getTiredLevel(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns the tired level of a player.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #getTiredLevel(Player)} or {@link #getTiredLevel(UUID)}.
	 * 
	 */
	public final int getTiredLevel(String playerName) {
		return getTiredLevel(plugin.getServer().getPlayer(playerName));
	}
	
	
	
	
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is in Rest Mode.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerAFK(Player player) {
		if(afkLevel.get(player.getUniqueId()).intValue() >= 300) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is in Rest Mode.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerAFK(UUID playerUUID) {
		return isPlayerAFK(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if the player is in Rest Mode.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #isPlayerTired(Player)} or {@link #isPlayerTired(UUID)}.
	 * 
	 */
	public final boolean isPlayerAFK(String playerName) {
		return isPlayerAFK(plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Returns the Rest Mode level of a player.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final int getAFKLevel(Player player) {
		return afkLevel.get(player.getUniqueId()).intValue();
	}
	/**
	 * 
	 * Returns the Rest Mode level of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final int getAFKLevel(UUID playerUUID) {
		return getAFKLevel(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns the Rest Mode level of a player.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A boolean type value.
	 * 
	 * @deprecated Replaced by {@link #getAFKLevel(Player)} or {@link #getAFKLevel(UUID)}.
	 * 
	 */
	public final int getAFKLevel(String playerName) {
		return getAFKLevel(plugin.getServer().getPlayer(playerName));
	}
}