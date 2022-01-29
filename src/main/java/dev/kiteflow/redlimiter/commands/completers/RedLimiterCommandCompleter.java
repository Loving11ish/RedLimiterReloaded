package dev.kiteflow.redlimiter.commands.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RedLimiterCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completers = new ArrayList<>();

        if(args.length == 1) {
            completers.add("frequent");
            completers.add("toggle");
        }

        return completers;
    }
}
