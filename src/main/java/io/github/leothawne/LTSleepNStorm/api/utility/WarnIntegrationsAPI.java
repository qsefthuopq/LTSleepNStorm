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