package eu.adventurelands.EnchantLimiter;

import eu.adventurelands.EnchantLimiter.commands.ReloadCommand;
import eu.adventurelands.EnchantLimiter.events.MoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getCommand("limiter").setExecutor(new ReloadCommand(this));

        Bukkit.getServer().getPluginManager().registerEvents(new MoveEvent(this), this);
    }

    public static Main getMainInstance() { return instance; }
}
