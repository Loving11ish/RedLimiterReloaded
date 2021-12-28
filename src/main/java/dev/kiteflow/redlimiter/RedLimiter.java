package dev.kiteflow.redlimiter;

import dev.kiteflow.redlimiter.events.OnBlockPlace;
import dev.kiteflow.redlimiter.events.OnRedstoneEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RedLimiter extends JavaPlugin {
    public static ConfigManager config;

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnRedstoneEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = new ConfigManager(this.getConfig());

        registerEvents();

        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, OnRedstoneEvent::clearBlocks, 200, 300);

        System.out.println("[RedLimiting] RedLimiting enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[RedLimiting] RedLimiting disabled");
    }
}