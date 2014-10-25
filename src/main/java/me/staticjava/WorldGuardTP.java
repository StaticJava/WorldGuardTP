package me.staticjava;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jains on 4/21/2014.
 */
public class WorldGuardTP extends JavaPlugin {
    WorldGuardPlugin worldGuardPlugin;

    @Override
    public void onEnable() {
        getLogger().info("WorldGuardTP has been enabled!");

        saveDefaultConfig();

        getCommand("tpdisable").setExecutor(new TPDisable(this));
        getCommand("tpenable").setExecutor(new TPEnable(this));
        getCommand("tpreload").setExecutor(new TPReload(this));

        getServer().getPluginManager().registerEvents(new TPListener(this), this);

        worldGuardPlugin = getWorldGuard();
    }

    @Override
    public void onDisable() {
        getLogger().info("WorldGuardTP has been disabled!");

        saveConfig();
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }
}
