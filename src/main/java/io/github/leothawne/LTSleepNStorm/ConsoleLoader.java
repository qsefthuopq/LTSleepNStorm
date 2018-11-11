package io.github.leothawne.LTSleepNStorm;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private LTSleepNStormLoader plugin;
	public ConsoleLoader(LTSleepNStormLoader plugin) {
		this.plugin = plugin;
	}
	private ConsoleCommandSender newLogger() {
		return plugin.getServer().getConsoleSender();
	}
	private String LTSNSVersion = new Version(plugin, this).LTSNSVersion;
	private String LTSNSVersion_Date = new Version(plugin, this).LTSNSVersion_Date;
	private String Minecraft_Version = new Version(plugin, this).Minecraft_Version;
	private String Java_Version = new Version(plugin, this).Java_Version;
	public void Hello() {
		newLogger().sendMessage(ChatColor.AQUA + " _  _____    ____ _    ______________    _        ____ _____ ____ ____ _    ");
		newLogger().sendMessage(ChatColor.AQUA + "/ \\/__ __\\  / ___/ \\  /  __/  __/  __\\  / \\  /|  / ___/__ __/  _ /  __/ \\__/|" + ChatColor.LIGHT_PURPLE + "  V " + LTSNSVersion + " (Minecraft " + Minecraft_Version + ")");
		newLogger().sendMessage(ChatColor.AQUA + "| |  / \\    |    | |  |  \\ |  \\ |  \\/|  | |\\ ||  |    \\ / \\ | / \\|  \\/| |\\/||" + ChatColor.LIGHT_PURPLE + "  Works with Java " + Java_Version);
		newLogger().sendMessage(ChatColor.AQUA + "| |_/| |    \\___ | |_/|  /_|  /_|  __/  | | \\||  \\___ | | | | \\_/|    | |  ||" + ChatColor.LIGHT_PURPLE + "  Released on " + LTSNSVersion_Date);
		newLogger().sendMessage(ChatColor.AQUA + "\\____\\_/    \\____\\____\\____\\____\\_/     \\_/  \\|  \\____/ \\_/ \\____\\_/\\_\\_/  \\|" + ChatColor.LIGHT_PURPLE + "  Twitter @leothawne");
		newLogger().sendMessage("");
	}
	public void Goodbye() {
		newLogger().sendMessage(ChatColor.AQUA + " _________ ____ ____ _______  ______");
		newLogger().sendMessage(ChatColor.AQUA + "/  __/  _ /  _ /  _ /  _ \\  \\//  __/");
		newLogger().sendMessage(ChatColor.AQUA + "| |  | / \\| / \\| | \\| | //\\  /|  \\  ");
		newLogger().sendMessage(ChatColor.AQUA + "| |_/| \\_/| \\_/| |_/| |_\\\\/ / |  /_ ");
		newLogger().sendMessage(ChatColor.AQUA + "\\____\\____\\____\\____\\____/_/  \\____\\");
		newLogger().sendMessage("");
	}
	public void info(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.WHITE + "INFO" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public void warning(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.YELLOW + "WARNING" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public void severe(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.RED + "ERROR" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
}