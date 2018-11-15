package io.github.leothawne.LTSleepNStorm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Version {
	private LTSleepNStormLoader plugin;
	private ConsoleLoader myLogger;
	public Version(LTSleepNStormLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	public final int configFileVersion = 1;
	public final String LTSNSVersion = "0.1.0";
	public final String LTSNSVersion_Date = "";
	public final String Minecraft_Version = "1.13.X";
	public final String Minecraft_Build = "1.13-R0.1-SNAPSHOT";
	public final String Java_Version = "8+";
	public final void version(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "LT Sleep N Storm " + ChatColor.YELLOW + "plugin " + ChatColor.GREEN + "" + LTSNSVersion + "" + ChatColor.YELLOW + " (" + ChatColor.GREEN + "" + LTSNSVersion_Date + "" + ChatColor.YELLOW + "), Minecraft " + ChatColor.GREEN + "" + Minecraft_Version +  "" + ChatColor.YELLOW + " (Java " + ChatColor.GREEN + "" + Java_Version + "" + ChatColor.YELLOW + ", build " + ChatColor.GREEN + "" + Minecraft_Build + "" + ChatColor.YELLOW + ").");
	}
	public final void check() {
		try {
			URLConnection allowedUrl = new URL("https://leothawne.github.io/LTItemMail/api/" + LTSNSVersion + "/plugin.html").openConnection();
			allowedUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			allowedUrl.connect();
			BufferedReader allowedReader = new BufferedReader(new InputStreamReader(allowedUrl.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = allowedReader.readLine()) != null) {
				sb.append(line);
			}
			if(sb.toString() != null) {
				if(sb.toString().equalsIgnoreCase("disabled")) {
					myLogger.severe("Hey you, stop right there! The version " + LTSNSVersion + " is not allowed anymore!");
					myLogger.severe("Apologies, but this plugin will now be disabled! Download a newer version to play: https://dev.bukkit.org/projects/lt-sleep-n-storm");
					plugin.getServer().getPluginManager().disablePlugin(plugin);
				}
			}
		} catch(Exception e) {
			myLogger.severe("Plugin: Is this version allowed?");
		}
	}
}