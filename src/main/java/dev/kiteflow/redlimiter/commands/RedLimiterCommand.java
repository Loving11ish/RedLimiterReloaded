package dev.kiteflow.redlimiter.commands;

import dev.kiteflow.redlimiter.events.OnRedstoneEvent;
import dev.kiteflow.redlimiter.gui.FrequentGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedLimiterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length >= 1) {
            switch(args[0].toLowerCase()) {
                case "frequent":
                    if(!sender.hasPermission("redlimiter.frequentlist")) {
                        sender.sendMessage(ChatColor.RED + "You do not have permission!");
                        return true;
                    }

                    if(sender instanceof Player) new FrequentGUI((Player) sender);
                    else sender.sendMessage("You must be a player to do this!");

                    break;
                case "toggle":
                    if(!sender.hasPermission("redlimiter.toggle")) {
                        sender.sendMessage(ChatColor.RED + "You do not have permission!");
                        return true;
                    }

                    OnRedstoneEvent.redstoneEnabled = !OnRedstoneEvent.redstoneEnabled;
                    sender.sendMessage(OnRedstoneEvent.redstoneEnabled ?
                            ChatColor.GREEN + "Redstone enabled!" :
                            ChatColor.RED + "Redstone disabled!");

                    break;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.UNDERLINE + "RedLimiter commands: \n" +
                    ChatColor.RED + "/redlimiter frequent: " + ChatColor.WHITE + "See the most frequently activated redstone. \n" +
                    ChatColor.RED + "/redlimiter toggle: " + ChatColor.WHITE + "Toggle redstone for the entire server.");
        }
        return true;
    }
}
