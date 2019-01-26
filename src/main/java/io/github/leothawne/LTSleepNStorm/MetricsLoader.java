package io.github.leothawne.LTSleepNStorm;

import io.github.leothawne.LTSleepNStorm.api.bStats.Metrics;

public class MetricsLoader {
	private static LTSleepNStormLoader plugin;
	private static ConsoleLoader myLogger;
	public MetricsLoader(LTSleepNStormLoader plugin, ConsoleLoader myLogger) {
		MetricsLoader.plugin = plugin;
		MetricsLoader.myLogger = myLogger;
	}
	public static final void init() {
		Metrics metrics = new Metrics(plugin);
		if(metrics.isEnabled() == true) {
			myLogger.info("LT Sleep N Storm is using bStats to collect data to improve the next versions. In case you want to know what data will be collected: https://bstats.org/getting-started");
		} else {
			myLogger.warning("bStats is disabled and LT Item Mail cannot use it. Please enable bStats! Thank you.");
		}
	}
}