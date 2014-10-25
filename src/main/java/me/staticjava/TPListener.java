package me.staticjava;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jains on 4/21/2014.
 */
public class TPListener implements Listener {

    private WorldGuardTP plugin;

    public TPListener(WorldGuardTP plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTPCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("wgtp.bypass")) {

            String command = event.getMessage().toLowerCase().substring(1); //The command without the backslash.
            List<String> commandList = Arrays.asList(command.split(" "));

            int length = commandList.size();

            if (commandList.get(0).equals("tpa")) {
                if (length == 2) {
                    String playerTo = commandList.get(1);
                    Player player1 = Bukkit.getPlayer(playerTo);

                    if (player1 != null) {
                        Location loc = player1.getLocation();

                        ApplicableRegionSet set = WGBukkit.getRegionManager(player1.getWorld()).getApplicableRegions(loc);

                        for (ProtectedRegion region : set) {
                            String id = region.getId();
                            String worldName = player1.getWorld().getName();
                            if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + worldName)) {
                                event.setCancelled(true);
                                player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGTP" + ChatColor.GOLD + "] " + ChatColor.GREEN + "You cannot teleport to this user, as they are in a region where TP is disabled!");
                                break;
                            }
                        }
                    }
                }
            } else if (commandList.get(0).equals("tp")) {
                if (length == 2) {
                    String playerTo = commandList.get(1);
                    Player player1 = Bukkit.getPlayer(playerTo);

                    if (player1 != null) {
                        Location loc = player1.getLocation();

                        ApplicableRegionSet set = WGBukkit.getRegionManager(player1.getWorld()).getApplicableRegions(loc);

                        for (ProtectedRegion region : set) {
                            String id = region.getId();
                            String worldName = player1.getWorld().getName();
                            if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + worldName)) {
                                event.setCancelled(true);
                                player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGTP" + ChatColor.GOLD + "] " + ChatColor.GREEN + "You cannot teleport to this user, as they are in a region where TP is disabled!");
                                break;
                            }
                        }
                    }
                } else if (length == 3) {
                    String playerTeleporting = commandList.get(1);
                    Player player1 = Bukkit.getPlayer(playerTeleporting);

                    String playerTeleported = commandList.get(2);
                    Player player2 = Bukkit.getPlayer(playerTeleported);
                    if (player1 != null && player2 != null) {
                        Location loc = player2.getLocation();

                        ApplicableRegionSet set = WGBukkit.getRegionManager(player2.getWorld()).getApplicableRegions(loc);

                        for (ProtectedRegion region : set) {
                            String id = region.getId();
                            String worldName = player1.getWorld().getName();
                            if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + worldName)) {
                                event.setCancelled(true);
                                player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGTP" + ChatColor.GOLD + "] " + ChatColor.GREEN + "You cannot teleport to this user, as they are in a region where TP is disabled!");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
