package eu.adventurelands.EnchantLimiter.commands;

import eu.adventurelands.EnchantLimiter.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8» &4Incorrect usage! &8[&c/limiter reload&8]"));
            } else System.out.println("Incorrect usage! [/limiter reload]");
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("limiter.reload")) {
                    plugin.reloadConfig();

                    if (sender instanceof Player) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8» &cReloaded config!"));
                    } else System.out.println("Reloaded config!");
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error."));
                }
            } else {
                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8» &4Incorrect usage! &8[&c/limiter reload&8]"));
                } else System.out.println("Incorrect usage! [/limiter reload]");
            }
        }
        return true;
    }
}
