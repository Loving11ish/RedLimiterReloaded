package dev.kiteflow.redlimiterreloaded.files;

import dev.kiteflow.redlimiterreloaded.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ConfigManager {
    public boolean redstoneEnabled;

    public int limitValue;
    public ArrayList<String> materialList;

    public Long pistonTimings;
    public Long observerTimings;
    public Long repeaterTimings;
    public Long defaultTimings;

    public String limitwarning;

    public ConfigManager(FileConfiguration config) {
        redstoneEnabled = config.getBoolean("redstone");

        pistonTimings = config.getLong("timings.pistons");
        observerTimings = config.getLong("timings.observers");
        repeaterTimings = config.getLong("timings.repeaters");
        defaultTimings = config.getLong("timings.default");

        limitValue = config.getInt("limits.limit-value");
        materialList = new ArrayList<>(config.getStringList("limits.materials-list"));

        limitwarning = ColorUtils.translateColorCodes(config.getString("limitwarning"));
    }
}
