package io.github.leothawne.LTSleepNStorm.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;
import io.github.leothawne.LTSleepNStorm.Version;

public class SleepNStormAdminCommand implements CommandExecutor {
	private LTSleepNStormLoader plugin;
	private ConsoleLoader myLogger;
	private FileConfiguration configuration;
	private FileConfiguration language;
	public SleepNStormAdminCommand(LTSleepNStormLoader plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language) {
		this.plugin = plugin;
		this.myLogger = myLogger;
		this.configuration = configuration;
		this.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(sender.hasPermission("LTSleepNStorm.admin")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.AQUA + "=+=+=+= [LT Sleep N Storm :: Admin] =+=+=+=");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.AQUA + "- Administration commands for LT Sleep N Storm.");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin version " + ChatColor.AQUA + "- Check for new updates.");
					sender.sendMessage(ChatColor.GREEN + "/sleepnstormadmin timereset " + ChatColor.AQUA + "- Set the world you are standing in to day 0.");
					sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/sleepnstormadmin "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/snsa"+ ChatColor.YELLOW + ".");
				} else if(args[0].equalsIgnoreCase("version")) {
					if(args.length < 2) {
						try {
							URLConnection newUpdateURL = new URL("https://leothawne.github.io/LTSleepNStorm/api/version.html").openConnection();
							newUpdateURL.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
							newUpdateURL.connect();
							BufferedReader newUpdateReader = new BufferedReader(new InputStreamReader(newUpdateURL.getInputStream(), Charset.forName("UTF-8")));
							StringBuilder sb = new StringBuilder();
							String line;
							while((line = newUpdateReader.readLine()) != null) {
								sb.append(line);
							}
							if(sb.toString() != null) {
								String[] LocalVersion = Version.getVersionNumber().split("\\.");
								int Local_VersionNumber1 = Integer.parseInt(LocalVersion[0]);
								int Local_VersionNumber2 = Integer.parseInt(LocalVersion[1]);
								int Local_VersionNumber3 = Integer.parseInt(LocalVersion[2]);
								String[] Server1 = sb.toString().split("-");
								String[] Server2 = Server1[0].split("\\.");
								int Server2_VersionNumber1 = Integer.parseInt(Server2[0]);
								int Server2_VersionNumber2 = Integer.parseInt(Server2[1]);
								int Server2_VersionNumber3 = Integer.parseInt(Server2[2]);
								sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "Running: " + ChatColor.GREEN + "" + Version.getVersionNumber() + "" + ChatColor.YELLOW + " (Released on " + ChatColor.GREEN + "" + Version.getVersionDate() + "" + ChatColor.YELLOW + ")");
								String updateMessage = ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "A newer version is available: " + ChatColor.GREEN + "" + Server1[0] + "" + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + "" + Server1[1] + "" + ChatColor.YELLOW + ")";
								if(Server2_VersionNumber1 > Local_VersionNumber1) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 > Local_VersionNumber2) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 == Local_VersionNumber2 && Server2_VersionNumber3 > Local_VersionNumber3) {
									sender.sendMessage(updateMessage);
								} else {
									sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "The plugin is up to date!");
								}
							} else {
								sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "Error while checking for new updates: Server did not respond correctly.");
							}
						} catch(Exception e) {
							myLogger.severe("Error while checking for new updates: " + e.getMessage());
							sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "Error while checking for new updates.");
						}
					} else {
						sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else if(args[0].equalsIgnoreCase("timereset")) {
					if(args.length < 2) {
						if(sender instanceof Player) {
							Player player = (Player) sender;
							ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
							if(worldList.contains(player.getLocation().getWorld().getName())) {
								player.getLocation().getWorld().setFullTime(0);
								String dayTag = language.getString("world-day-tag");
								for(Player players : plugin.getServer().getOnlinePlayers()) {
									if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
										players.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
										players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
									}
								}
							} else {
								player.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("world-blocked"));
							}
						} else {
							for(World world : plugin.getServer().getWorlds()) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								}
							}
							
						}
					} else if(args.length < 3) {
						if(args[1].equals("*")) {
							for(World world : plugin.getServer().getWorlds()) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								}
							}
						} else {
							World world = plugin.getServer().getWorld(args[1]);
							if(world != null) {
								ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + language.getString("world-day-reset"));
									String dayTag = language.getString("world-day-tag");
									for(Player player : plugin.getServer().getOnlinePlayers()) {
										if(player.getLocation().getWorld().equals(world)) {
											player.sendMessage(ChatColor.RED + "" + language.getString("world-day-reset"));
											player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
										}
									}
								} else {
									sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("world-blocked"));
								}
							} else {
								String[] notFoundTag = language.getString("world-not-found").split("%");
								sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + notFoundTag[0] + "" + ChatColor.GREEN + "" + args[1] + "" + ChatColor.YELLOW + "" + notFoundTag[1]);
							}
						}
					} else {
						sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else {
					sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/sleepnstormadmin " + ChatColor.YELLOW + "to see all available commands.");
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[LTSNS :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
				myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.admin].");
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "[LTSNS] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.severe(sender.getName() + " does not have permission [LTSleepNStorm.use].");
		}
		return true;
	}
}