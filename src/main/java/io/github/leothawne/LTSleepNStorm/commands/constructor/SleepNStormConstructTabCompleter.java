package io.github.leothawne.LTSleepNStorm.commands.constructor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class SleepNStormConstructTabCompleter implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		return ReturnNothing;
	}
}