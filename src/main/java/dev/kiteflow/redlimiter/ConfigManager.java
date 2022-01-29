package dev.kiteflow.redlimiter;

import org.bukkit.configuration.file.FileConfiguration;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ConfigManager {
    public boolean redstoneEnabled;

    public Long pistonTimings;
    public Long observerTimings;
    public Long repeaterTimings;
    public Long defaultTimings;

    public Long pistonLimits;
    public Long observerLimits;
    public Long repeaterLimits;

    public String limitwarning;

    public ConfigManager(FileConfiguration config) {
        redstoneEnabled = config.getBoolean("redstone");

        pistonTimings = config.getLong("timings.pistons");
        observerTimings = config.getLong("timings.observers");
        repeaterTimings = config.getLong("timings.repeaters");
        defaultTimings = config.getLong("timings.default");

        pistonLimits = config.getLong("limits.pistons");
        observerLimits = config.getLong("limits.observers");
        repeaterLimits = config.getLong("limits.repeaters");

        limitwarning = config.getString("limitwarning");
        limitwarning = translateAlternateColorCodes('&', limitwarning);
    }
}
