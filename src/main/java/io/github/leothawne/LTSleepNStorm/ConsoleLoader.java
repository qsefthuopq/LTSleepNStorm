package io.github.leothawne.LTSleepNStorm;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private LTSleepNStormLoader plugin;
	public ConsoleLoader(LTSleepNStormLoader plugin) {
		this.plugin = plugin;
	}
	private final ConsoleCommandSender getConsoleSender() {
		return plugin.getServer().getConsoleSender();
	}
	public final void Hello() {
		getConsoleSender().sendMessage(ChatColor.AQUA + " _   _______ _____ _   _  _____ ");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| | |__   __/ ____| \\ | |/ ____|");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | | | (___ |  \\| | (___  " + ChatColor.WHITE + "  V: " + Version.getVersionNumber() + " (Minecraft: " + Version.getMinecraftVersion() + ")");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | |  \\___ \\| . ` |\\___ \\" + ChatColor.WHITE + "  Requires Java: " + Version.getJavaVersion());
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |____| |  ____) | |\\  |____) |" + ChatColor.WHITE + "  Released on: " + Version.getVersionDate());
		getConsoleSender().sendMessage(ChatColor.AQUA + "|______|_| |_____/|_| \\_|_____/ " + ChatColor.WHITE + "  My Twitter: @leonappi_");
	}
	public final void info(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.GREEN + "I" + ChatColor.WHITE + "] " + message);
	}
	public final void warning(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " + message);
	}
	public final void severe(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.RED + "E" + ChatColor.WHITE + "] " + message);
	}
}