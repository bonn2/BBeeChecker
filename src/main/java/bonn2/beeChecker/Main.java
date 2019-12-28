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
        } else { // Try to check if config file is valid
            checkConfig("ClickOnBlock", true);
            checkConfig("ClickInAir", true);
            checkConfig("Lore", true);
            checkConfig("Language", "enUS");
            checkConfig("ChatMessage.AmountSingle", "§rThere is §6%number% bee §rin this hive.");
            checkConfig("ChatMessage.AmountPlural", "§rThere are §6%number% bees §rin this hive.");
            checkConfig("Lore.AmountSingle", "§rThere is §6%number% bee §rin this hive.");
            checkConfig("Lore.AmountPlural", "§rThere are §6%number% bees §rin this hive.");
            saveConfig();
            reloadConfig();
        }

        getLogger().info("Config Initialized!");
        
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
