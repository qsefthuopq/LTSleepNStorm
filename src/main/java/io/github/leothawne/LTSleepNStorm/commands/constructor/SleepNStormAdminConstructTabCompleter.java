package io.github.leothawne.LTSleepNStorm.commands.constructor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class SleepNStormAdminConstructTabCompleter implements TabCompleter {
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
		}
		return ReturnNothing;
	}
}