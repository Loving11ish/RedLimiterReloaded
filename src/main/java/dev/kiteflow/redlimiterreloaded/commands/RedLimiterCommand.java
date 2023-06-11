package dev.kiteflow.redlimiterreloaded.commands;

import com.tcoded.folialib.FoliaLib;
import dev.kiteflow.redlimiterreloaded.RedLimiterReloaded;
import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;
import dev.kiteflow.redlimiterreloaded.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class RedLimiterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            if(args.length >= 1) {
                if ("toggle".equalsIgnoreCase(args[0])) {
                    if (!sender.hasPermission("redlimiterreloaded.toggle")||!sender.isOp()) {
                        sender.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission!"));
                        return true;
                    }
                    OnRedStoneEvent.redStoneEnabled = !OnRedStoneEvent.redStoneEnabled;
                    sender.sendMessage(OnRedStoneEvent.redStoneEnabled ?
                            ColorUtils.translateColorCodes("&aRedstone enabled!") :
                            ColorUtils.translateColorCodes("&cRedstone disabled!"));
                }else  if ("reload".equalsIgnoreCase(args[0])){
                    if (!sender.hasPermission("redlimiterreloaded.reload")||!sender.isOp()) {
                        sender.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission!"));
                        return true;
                    }
                    sender.sendMessage(ColorUtils.translateColorCodes("&3Beginning plugin reload..."));
                    RedLimiterReloaded plugin = RedLimiterReloaded.getPlugin();
                    FoliaLib foliaLib = new FoliaLib(plugin);
                    plugin.onDisable();
                    foliaLib.getImpl().runLater(new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getPluginManager().getPlugin("RedLimiterReloaded").onEnable();
                        }
                    }, 5L, TimeUnit.SECONDS);
                    foliaLib.getImpl().runLater(new Runnable() {
                        @Override
                        public void run() {
                            RedLimiterReloaded.getPlugin().reloadConfig();
                            sender.sendMessage(ColorUtils.translateColorCodes("&3Plugin reload complete!"));
                        }
                    }, 6L, TimeUnit.SECONDS);
                }
            }else {
                sender.sendMessage(ColorUtils.translateColorCodes("&c&nRedLimiter commands:&r "));
                sender.sendMessage(ColorUtils.translateColorCodes("&c/redlimiter toggle: &3Toggle redstone for the entire server."));
                sender.sendMessage(ColorUtils.translateColorCodes("&c/redlimiter reload: &3Reload the RedLimiter plugin."));
            }
        }else if (sender instanceof ConsoleCommandSender){
            if(args.length >= 1) {
                if ("toggle".equalsIgnoreCase(args[0])) {
                    OnRedStoneEvent.redStoneEnabled = !OnRedStoneEvent.redStoneEnabled;
                    sender.sendMessage(OnRedStoneEvent.redStoneEnabled ?
                            ColorUtils.translateColorCodes("&aRedstone enabled!") :
                            ColorUtils.translateColorCodes("&cRedstone disabled!"));
                }else  if ("reload".equalsIgnoreCase(args[0])){
                    sender.sendMessage(ColorUtils.translateColorCodes("&3Beginning plugin reload..."));
                    RedLimiterReloaded plugin = RedLimiterReloaded.getPlugin();
                    FoliaLib foliaLib = new FoliaLib(plugin);
                    plugin.onDisable();
                    foliaLib.getImpl().runLater(new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getPluginManager().getPlugin("RedLimiterReloaded").onEnable();
                        }
                    }, 5L, TimeUnit.SECONDS);
                    foliaLib.getImpl().runLater(new Runnable() {
                        @Override
                        public void run() {
                            RedLimiterReloaded.getPlugin().reloadConfig();
                            sender.sendMessage(ColorUtils.translateColorCodes("&3Plugin reload complete!"));
                        }
                    }, 6L, TimeUnit.SECONDS);
                }
            }else {
                sender.sendMessage(ColorUtils.translateColorCodes("&c&nRedLimiter commands:&r "));
                sender.sendMessage(ColorUtils.translateColorCodes("&c/redlimiter toggle: &3Toggle redstone for the entire server."));
                sender.sendMessage(ColorUtils.translateColorCodes("&c/redlimiter reload: &3Reload the RedLimiter plugin."));
            }
        }
        return true;
    }
}
