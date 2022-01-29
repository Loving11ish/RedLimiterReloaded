package dev.kiteflow.redlimiter;

import dev.kiteflow.redlimiter.commands.RedLimiterCommand;
import dev.kiteflow.redlimiter.commands.completers.RedLimiterCommandCompleter;
import dev.kiteflow.redlimiter.events.OnBlockPlace;
import dev.kiteflow.redlimiter.events.OnRedstoneEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

@SuppressWarnings("deprecation")
public final class RedLimiter extends JavaPlugin {
    public static Plugin plugin;
    private static FileConfiguration config;
    public static ConfigManager configManager;

    private void registerCommands(){
        this.getCommand("redlimiter").setExecutor(new RedLimiterCommand());
        this.getCommand("redlimiter").setTabCompleter(new RedLimiterCommandCompleter());
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnRedstoneEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
    }

    @Override
    public void onEnable() {
        plugin = this;

        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();

        configManager = new ConfigManager(config);

        registerCommands();
        registerEvents();

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, OnRedstoneEvent::clearBlocks, 200, 300);
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, OnRedstoneEvent::clearSignalCount, 200, 6000);
        getLogger().info("RedLimiter enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("RedLimiter disabled");
    }
}
