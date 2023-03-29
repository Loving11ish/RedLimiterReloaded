package dev.kiteflow.redlimiterreloaded.commands;

import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;
import dev.kiteflow.redlimiterreloaded.gui.FrequentGUI;
import dev.kiteflow.redlimiterreloaded.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedLimiterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "frequent" -> {
                    if (!sender.hasPermission("redlimiterreloaded.frequentlist")) {
                        sender.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission!"));
                        return true;
                    }
                    if (sender instanceof Player) new FrequentGUI((Player) sender);
                    else sender.sendMessage(ColorUtils.translateColorCodes("&4You must be a player to do this!"));
                }
                case "toggle" -> {
                    if (!sender.hasPermission("redlimiterreloaded.toggle")) {
                        sender.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission!"));
                        return true;
                    }
                    OnRedStoneEvent.redStoneEnabled = !OnRedStoneEvent.redStoneEnabled;
                    sender.sendMessage(OnRedStoneEvent.redStoneEnabled ?
                            ColorUtils.translateColorCodes("&aRedstone enabled!") :
                            ColorUtils.translateColorCodes("&cRedstone disabled!"));
                }
            }
        }else {
            sender.sendMessage(ColorUtils.translateColorCodes("&c&nRedLimiter commands:&r \n" +
                    ColorUtils.translateColorCodes("&c/redlimiterreloaded frequent: &3See the most frequently activated redstone. \n" +
                    ColorUtils.translateColorCodes("&c/redlimiterreloaded toggle: &3Toggle redstone for the entire server."))));
        }
        return true;
    }
}
