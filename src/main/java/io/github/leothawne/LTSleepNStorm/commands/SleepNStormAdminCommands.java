package io.github.leothawne.LTSleepNStorm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.leothawne.LTSleepNStorm.ConsoleLoader;
import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

@SuppressWarnings("unused")
public class SleepNStormAdminCommands implements CommandExecutor {
	private LTSleepNStormLoader plugin;
	private ConsoleLoader myLogger;
	public SleepNStormAdminCommands(LTSleepNStormLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		return true;
	}
}