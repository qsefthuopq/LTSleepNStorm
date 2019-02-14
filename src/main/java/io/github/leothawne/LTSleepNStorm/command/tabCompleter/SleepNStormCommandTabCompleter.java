package io.github.leothawne.LTSleepNStorm.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class SleepNStormCommandTabCompleter implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("LTSleepNStorm.use") && cmd.getName().equalsIgnoreCase("sleepnstorm")) {
			if(args.length == 1) {
				List<String> Storm = new ArrayList<>();
				Storm.add("version");
				return Storm;
			}
		}
		return ReturnNothing;
	}
}