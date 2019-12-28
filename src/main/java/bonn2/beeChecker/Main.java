package bonn2.beeChecker;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Originaly made for mc.envirocraft.net!");
        
        getLogger().info("Initializing Config");
        File configyml = new File(getDataFolder() + File.separator + "config.yml");
        if (!configyml.exists()) { // Checks if config file exists
            getLogger().warning("No Config.yml found, making a new one!");
            saveResource("config.yml", false);
        }
        getLogger().info("Config Initialized!");
        
        getLogger().info("Initializing Commands");
        this.getCommand("bb").setExecutor(new commandListener());
        getLogger().info("Commands Initialized");

        getLogger().info("Initializing Event Listeners");
        getServer().getPluginManager().registerEvents(new beeCheckListener(), this);
        getServer().getPluginManager().registerEvents(new inventoryPickupListener(), this);
        getServer().getPluginManager().registerEvents(new hopperPickupListener(), this);
        getLogger().info("Event Listeners Initialized!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Have a nice day :)");
    }

}
