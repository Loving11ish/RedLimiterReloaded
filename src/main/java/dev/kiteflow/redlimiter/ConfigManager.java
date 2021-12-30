package dev.kiteflow.redlimiter;

import org.bukkit.configuration.file.FileConfiguration;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ConfigManager {
    public Long pistonTimings;
    public Long observerTimings;
    public Long repeaterTimings;
    public Long defaultTimings;

    public Long pistonLimits;
    public Long observerLimits;
    public Long repeaterLimits;

    public String limitwarning = "&cYou have reached the chunk limit!";

    public ConfigManager(FileConfiguration config) {
        pistonTimings = config.getLong("timings.pistons");
        observerTimings = config.getLong("timings.observers");
        repeaterTimings = config.getLong("timings.repeaters");
        defaultTimings = config.getLong("timings.default");

        pistonLimits = config.getLong("limits.pistons");
        observerLimits = config.getLong("limits.observers");
        repeaterLimits = config.getLong("limits.repeaters");

        if(config.contains("limitwarning", true)) limitwarning = config.getString("limitwarning");
        else RedLimiter.plugin.getLogger().warning("New config file required! Please edit it so it contains all values in https://github.com/Kiteflow/RedLimiter/blob/master/src/main/resources/config.yml");

        limitwarning = translateAlternateColorCodes('&', limitwarning);
    }
}
