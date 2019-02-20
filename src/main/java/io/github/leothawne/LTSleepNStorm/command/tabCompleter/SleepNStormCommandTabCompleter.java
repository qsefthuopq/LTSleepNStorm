package io.github.leothawne.LTSleepNStorm.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.ImmutableList;

import io.github.leothawne.LTSleepNStorm.api.utility.TabCompleterAPI;

public class SleepNStormCommandTabCompleter extends TabCompleterAPI implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("LTSleepNStorm.use") && cmd.getName().equalsIgnoreCase("sleepnstorm")) {
			if(args.length == 1) {
				ImmutableList<String> Storm = ImmutableList.of("version");
				return partial(args[0], Storm);
			}
		}
		return ReturnNothing;
	}
}