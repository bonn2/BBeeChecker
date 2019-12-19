package bonn2.beeChecker;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    @Override
    public void onEnable() {
        getLogger().info("Originaly made for mc.envirocraft.net!");
        getServer().getPluginManager().registerEvents(new beeCheckListener(), this);
        getServer().getPluginManager().registerEvents(new inventoryPickup(), this);
        getServer().getPluginManager().registerEvents(new hopperPickup(), this);
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Have a nice day :)");
    }
}
