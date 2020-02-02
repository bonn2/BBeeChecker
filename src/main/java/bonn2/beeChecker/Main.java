package bonn2.beeChecker;

import java.io.File;

import org.bstats.bukkit.Metrics;
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
        if (getConfig().getInt("ConfigVersion") == 1) {updateConfig();}
        if (getConfig().getInt("ConfigVersion") == 2) {updateConfig2();}
        getLogger().info("Config Initialized!");
        
        getLogger().info("Initializing Commands");
        this.getCommand("bb").setExecutor(new commandListener());
        getLogger().info("Commands Initialized");

        getLogger().info("Initializing Event Listeners");
        getServer().getPluginManager().registerEvents(new beeCheckListener(), this);
        getServer().getPluginManager().registerEvents(new inventoryPickupListener(), this);
        getServer().getPluginManager().registerEvents(new hopperPickupListener(), this);
        //getServer().getPluginManager().registerEvents(new craftListener(), this);
        getLogger().info("Event Listeners Initialized!");

        if (getConfig().getBoolean("disableMetrics") == false) {
            getLogger().info("Enabling Metrics, thanks for helping me make the plugin better.");
            int pluginId = 6414; 
            Metrics metrics = new Metrics(this, pluginId);
            
        }
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Have a nice day :)");
    }

    public void updateConfig() {
        getLogger().warning("Detected config version 1 updating to version 2!");
        boolean clickonblock = getConfig().getBoolean("ClickOnBlock");
        boolean clickinair = getConfig().getBoolean("ClickInAir");
        String messagelocation = getConfig().getString("MessageLocation");
        String loresingle = getConfig().getString("Lore.AmountSingle");
        String loreplural = getConfig().getString("Lore.AmountPlural");
        String messagesingle = getConfig().getString("ChatMessage.AmountSingle");
        String messageplural = getConfig().getString("ChatMessage.AmountPlural");
        String invcommand = getConfig().getString("InvalidCommand");
        String reloadconfig = getConfig().getString("ReloadingConfig");
        String reloadconfigsuccess = getConfig().getString("ReloadedConfigSuccess");
        String reloadconfigfail = getConfig().getString("ReloadedConfigFail");
        saveResource("config.yml", true);
        reloadConfig();
        getConfig().set("ClickOnBlock", clickonblock);
        getConfig().set("ClickInAir", clickinair);
        getConfig().set("MessageLocation", messagelocation);
        getConfig().set("Lore.AmountSingle", loresingle);
        getConfig().set("Lore.AmountPlural", loreplural);
        getConfig().set("ChatMessage.AmountSingle", messagesingle);
        getConfig().set("ChatMessage.AmountPlural", messageplural);
        getConfig().set("InvalidCommand", invcommand);
        getConfig().set("ReloadingConfig", reloadconfig);
        getConfig().set("ReloadedCongigSuccess", reloadconfigsuccess);
        getConfig().set("ReloadedCongigFail", reloadconfigfail);
        saveConfig();
        reloadConfig();
        updateConfig2();
    }

    public void updateConfig2() {
        getLogger().warning("Detected config version 2 updating to version 3!");
        boolean clickonblock = getConfig().getBoolean("ClickOnBlock");
        boolean clickinair = getConfig().getBoolean("ClickInAir");
        String messagelocation = getConfig().getString("MessageLocation");
        boolean lore = getConfig().getBoolean("lore");
        String loresingle = getConfig().getString("Lore.AmountSingle");
        String loreplural = getConfig().getString("Lore.AmountPlural");
        String messagesingle = getConfig().getString("ChatMessage.AmountSingle");
        String messageplural = getConfig().getString("ChatMessage.AmountPlural");
        String invcommand = getConfig().getString("InvalidCommand");
        String reloadconfig = getConfig().getString("ReloadingConfig");
        String reloadconfigsuccess = getConfig().getString("ReloadedConfigSuccess");
        String reloadconfigfail = getConfig().getString("ReloadedConfigFail");
        saveResource("config.yml", true);
        reloadConfig();
        getConfig().set("ClickOnBlock", clickonblock);
        getConfig().set("ClickInAir", clickinair);
        getConfig().set("MessageLocation", messagelocation);
        getConfig().set("lore", lore);
        getConfig().set("Lore.AmountSingle", loresingle);
        getConfig().set("Lore.AmountPlural", loreplural);
        getConfig().set("ChatMessage.AmountSingle", messagesingle);
        getConfig().set("ChatMessage.AmountPlural", messageplural);
        getConfig().set("InvalidCommand", invcommand);
        getConfig().set("ReloadingConfig", reloadconfig);
        getConfig().set("ReloadedCongigSuccess", reloadconfigsuccess);
        getConfig().set("ReloadedCongigFail", reloadconfigfail);
        saveConfig();
        reloadConfig();
    }

}
