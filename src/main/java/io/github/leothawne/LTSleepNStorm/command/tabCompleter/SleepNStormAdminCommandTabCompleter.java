package io.github.leothawne.LTSleepNStorm.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

public class SleepNStormAdminCommandTabCompleter implements TabCompleter {
	private LTSleepNStormLoader plugin;
	private FileConfiguration configuration;
	public SleepNStormAdminCommandTabCompleter(LTSleepNStormLoader plugin, FileConfiguration configuration) {
		this.plugin = plugin;
		this.configuration = configuration;
	}
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("LTSleepNStorm.use") && sender.hasPermission("LTSleepNStorm.admin") && cmd.getName().equalsIgnoreCase("sleepnstormadmin")) {
			if(args.length == 1) {
				List<String> StormAdmin = new ArrayList<>();
				StormAdmin.add("version");
				StormAdmin.add("timereset");
				return StormAdmin;
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("timereset")) {
				List<String> StormAdmin = new ArrayList<>();
				for(World world : plugin.getServer().getWorlds()) {
					ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
					if(worldList.contains(world.getName())) {
						StormAdmin.add(world.getName());
					}
				}
				return StormAdmin;
			}
		}
		return ReturnNothing;
	}
}