package me.staticjava;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Jains on 4/21/2014.
 */
public class TPReload implements CommandExecutor {

    private WorldGuardTP plugin;

    public TPReload(WorldGuardTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("wgtp.reload")) {
            plugin.reloadConfig();

            sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGTP" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Successfully reloaded the WGTP Config!");
            return true;
        } else {
            sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGTP" + ChatColor.GOLD + "] " + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }
    }
}