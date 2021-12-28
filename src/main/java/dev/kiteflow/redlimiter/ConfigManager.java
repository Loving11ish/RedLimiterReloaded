package dev.kiteflow.redlimiter;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    public Long pistonTimings;
    public Long observerTimings;
    public Long repeaterTimings;
    public Long defaultTimings;

    public Long pistonLimits;
    public Long observerLimits;
    public Long repeaterLimits;

    public ConfigManager(FileConfiguration config) {
        pistonTimings = config.getLong("timings.pistons");
        observerTimings = config.getLong("timings.observers");
        repeaterTimings = config.getLong("timings.repeaters");
        defaultTimings = config.getLong("timings.default");

        pistonLimits = config.getLong("limits.pistons");
        observerLimits = config.getLong("limits.observers");
        repeaterLimits = config.getLong("limits.repeaters");
    }
}
