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
package io.github.leothawne.LTSleepNStorm.api.utility;

import java.util.List;

import org.bukkit.plugin.Plugin;

import io.github.leothawne.LTSleepNStorm.LTSleepNStormLoader;

public class WarnIntegrationsAPI {
	public WarnIntegrationsAPI(LTSleepNStormLoader mainPlugin, List<String> plugins) {
		for(String plugin : plugins) {
			Plugin getPlugin = mainPlugin.getServer().getPluginManager().getPlugin(plugin);
			if(getPlugin != null) {
				getPlugin.getLogger().warning(getPlugin.getName() + " was successfully integrated with " + mainPlugin.getName() + "!");
			}
		}
	}
}