package dev.kiteflow.redlimiterreloaded.commands;

import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;
import dev.kiteflow.redlimiterreloaded.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RedLimiterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length >= 1) {
            if ("toggle".equalsIgnoreCase(args[0])) {
                if (!sender.hasPermission("redlimiterreloaded.toggle")) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission!"));
                    return true;
                }
                OnRedStoneEvent.redStoneEnabled = !OnRedStoneEvent.redStoneEnabled;
                sender.sendMessage(OnRedStoneEvent.redStoneEnabled ?
                        ColorUtils.translateColorCodes("&aRedstone enabled!") :
                        ColorUtils.translateColorCodes("&cRedstone disabled!"));
            }
        }else {
            sender.sendMessage(ColorUtils.translateColorCodes("&c&nRedLimiter commands:&r \n" +
                    ColorUtils.translateColorCodes("\n" +
                    ColorUtils.translateColorCodes("&c/redlimiterreloaded toggle: &3Toggle redstone for the entire server."))));
        }
        return true;
    }
}
