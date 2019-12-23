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
            getConfig().addDefault("Lore", true);
            getConfig().addDefault("Language", "enUS");
            getConfig().addDefault("lang.enUS.AmountSingle", "§rThere is §6%number% bee §rin this hive.");
            getConfig().addDefault("lang.enUS.AmountPlural", "§rThere are §6%number% bees §rin this hive.");
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else { // Try to check if config file is valid
            checkConfig("Lore", true);
            checkConfig("Language", "enUS");
            checkConfig("lang.enUS.AmountSingle", "§rThere is §6%number% bee §rin this hive.");
            checkConfig("lang.enUS.AmountPlural", "§rThere are §6%number% bees §rin this hive.");
            saveConfig();
            reloadConfig();
        }

        getLogger().info("Config Initialized!");
        
        getLogger().info("Initializing Event Listeners");
        getServer().getPluginManager().registerEvents(new beeCheckListener(), this);
        if (getConfig().getBoolean("Lore")) {
            getServer().getPluginManager().registerEvents(new inventoryPickup(), this);
            getServer().getPluginManager().registerEvents(new hopperPickup(), this);
        }
        getLogger().info("Event Listeners Initialized!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Have a nice day :)");
    }

    public void loreListeners() {
        if (getConfig().getBoolean("Lore")) {
            getServer().getPluginManager().registerEvents(new inventoryPickup(), this);
            getServer().getPluginManager().registerEvents(new hopperPickup(), this);
        }
    }

    public void checkConfig(String Name, String Value) {
        if (getConfig().get(Name) == null) {
            getConfig().set(Name, Value);
            saveConfig();
            reloadConfig();
        }
    }

    public void checkConfig(String Name, Boolean Value) {
        if (getConfig().get(Name) == null) {
            getConfig().set(Name, Value);
            saveConfig();
            reloadConfig();
        }
    }
}
