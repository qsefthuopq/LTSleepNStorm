/*
 * Copyright (C) 2019 Murilo Amaral Nappi (murilonappi@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.LTSleepNStorm.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.ImmutableList;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;
import io.github.leothawne.LTSleepNStorm.api.utility.TabCompleterAPI;

public class SleepNStormAdminCommandTabCompleter extends TabCompleterAPI implements TabCompleter {
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
				ImmutableList<String> StormAdmin = ImmutableList.of("version", "timereset");
				return partial(args[0], StormAdmin);
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("timereset")) {
				ArrayList<String> StormAdmin = new ArrayList<String>();
				StormAdmin.add("*");
				for(World world : plugin.getServer().getWorlds()) {
					ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
					if(worldList.contains(world.getName())) {
						StormAdmin.add(world.getName());
					}
				}
				ImmutableList<String> StormAdminNew = ImmutableList.copyOf(StormAdmin);
				return partial(args[1], StormAdminNew);
			}
		}
		return ReturnNothing;
	}
}