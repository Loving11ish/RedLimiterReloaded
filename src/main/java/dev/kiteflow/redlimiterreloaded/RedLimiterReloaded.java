package dev.kiteflow.redlimiterreloaded;

import com.tcoded.folialib.FoliaLib;
import dev.kiteflow.redlimiterreloaded.commands.RedLimiterCommand;
import dev.kiteflow.redlimiterreloaded.commands.completers.RedLimiterCommandCompleter;
import dev.kiteflow.redlimiterreloaded.events.OnBlockPlace;
import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;
import dev.kiteflow.redlimiterreloaded.files.ConfigManager;
import dev.kiteflow.redlimiterreloaded.utils.ColorUtils;
import dev.kiteflow.redlimiterreloaded.utils.TaskTimers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class RedLimiterReloaded extends JavaPlugin {

    private final PluginDescriptionFile pluginInfo = getDescription();
    private final String pluginVersion = pluginInfo.getVersion();

    private static RedLimiterReloaded plugin;
    private static FileConfiguration config;
    public static ConfigManager configManager;
    public static TaskTimers taskTimers;
    Logger logger = this.getLogger();

    private List<String> configMaterialList = new ArrayList<>();
    private static ArrayList<Material> materialsList = new ArrayList<>();

    @Override
    public void onEnable() {
        //Plugin startup logic
        plugin = this;

        //Server version compatibility check
        if (!(Bukkit.getServer().getVersion().contains("1.19"))){
            logger.warning(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            logger.warning(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &4This plugin is only supported on the Minecraft versions listed below:"));
            logger.warning(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &41.19.x"));
            logger.warning(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &4Is now disabling!"));
            logger.warning(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }else {
            logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
            logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &aA supported Minecraft version has been detected"));
            logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &6Continuing plugin startup"));
            logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
        }

        //Load config
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();

        configManager = new ConfigManager(config);

        //Load materials list
        configMaterialList = configManager.materialList;
        updateMaterialsList();

        //Register commands
        this.getCommand("redlimiter").setExecutor(new RedLimiterCommand());
        this.getCommand("redlimiter").setTabCompleter(new RedLimiterCommandCompleter());

        //Register events
        getServer().getPluginManager().registerEvents(new OnRedStoneEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);

        //Run auto task timers
        taskTimers = new TaskTimers();
        taskTimers.runClearBlocks();
        taskTimers.runClearSignalCount();

        //Plugin startup message
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Plugin by: &b&lLoving11ish & Kiteflow"));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3has been loaded successfully"));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Plugin Version: &d&l" + pluginVersion));
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
    }

    @Override
    public void onDisable() {
        //Safely stop the background tasks if running
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Plugin by: &b&lLoving11ish & Kiteflow"));
        try {
            FoliaLib foliaLib = new FoliaLib(this);
            taskTimers.task1.cancel();
            taskTimers.task2.cancel();
            if (foliaLib.isUnsupported()){
                Bukkit.getScheduler().cancelTasks(this);
            }
            logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Background tasks have disabled successfully!"));
        } catch (Exception e) {
            logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Background tasks have disabled successfully!"));
        }

        //Final plugin shutdown message
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Plugin Version: &d&l" + pluginVersion));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Has been shutdown successfully"));
        logger.info(ColorUtils.translateColorCodes("&6RedLimiterReloaded: &3Goodbye!"));
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
    }

    public static RedLimiterReloaded getPlugin() {
        return plugin;
    }

    public void updateMaterialsList(){
        for (String string : configMaterialList){
            materialsList.add(Material.getMaterial(string));
        }
    }

    public ArrayList<Material> getMaterialsList() {
        return materialsList;
    }
}
