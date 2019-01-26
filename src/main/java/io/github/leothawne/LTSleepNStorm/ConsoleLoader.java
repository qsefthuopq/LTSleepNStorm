package io.github.leothawne.LTSleepNStorm;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private LTSleepNStormLoader plugin;
	public ConsoleLoader(LTSleepNStormLoader plugin) {
		this.plugin = plugin;
	}
	private final ConsoleCommandSender newLogger() {
		return plugin.getServer().getConsoleSender();
	}
	private final String LTSNSVersion = Version.getVersionNumber();
	private final String LTSNSVersion_Date = Version.getVersionDate();
	private final String Minecraft_Version = Version.getMinecraftVersion();
	private final String Java_Version = Version.getJavaVersion();
	public final void Hello() {
		newLogger().sendMessage(ChatColor.AQUA + " _  _____    ____ _    ______________    _        ____ _____ ____ ____ _    ");
		newLogger().sendMessage(ChatColor.AQUA + "/ \\/__ __\\  / ___/ \\  /  __/  __/  __\\  / \\  /|  / ___/__ __/  _ /  __/ \\__/|" + ChatColor.LIGHT_PURPLE + "  V " + LTSNSVersion + " (Minecraft " + Minecraft_Version + ")");
		newLogger().sendMessage(ChatColor.AQUA + "| |  / \\    |    | |  |  \\ |  \\ |  \\/|  | |\\ ||  |    \\ / \\ | / \\|  \\/| |\\/||" + ChatColor.LIGHT_PURPLE + "  Works with Java " + Java_Version);
		newLogger().sendMessage(ChatColor.AQUA + "| |_/| |    \\___ | |_/|  /_|  /_|  __/  | | \\||  \\___ | | | | \\_/|    | |  ||" + ChatColor.LIGHT_PURPLE + "  Released on " + LTSNSVersion_Date);
		newLogger().sendMessage(ChatColor.AQUA + "\\____\\_/    \\____\\____\\____\\____\\_/     \\_/  \\|  \\____/ \\_/ \\____\\_/\\_\\_/  \\|" + ChatColor.LIGHT_PURPLE + "  Twitter @leonappi_");
		newLogger().sendMessage("");
	}
	public final void Goodbye() {
		newLogger().sendMessage(ChatColor.AQUA + " _________ ____ ____ _______  ______");
		newLogger().sendMessage(ChatColor.AQUA + "/  __/  _ /  _ /  _ /  _ \\  \\//  __/");
		newLogger().sendMessage(ChatColor.AQUA + "| |  | / \\| / \\| | \\| | //\\  /|  \\  ");
		newLogger().sendMessage(ChatColor.AQUA + "| |_/| \\_/| \\_/| |_/| |_\\\\/ / |  /_ ");
		newLogger().sendMessage(ChatColor.AQUA + "\\____\\____\\____\\____\\____/_/  \\____\\");
		newLogger().sendMessage("");
	}
	public final void info(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.WHITE + "INFO" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void warning(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.YELLOW + "WARNING" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void severe(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[LT Sleep N Storm " + ChatColor.RED + "ERROR" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
}