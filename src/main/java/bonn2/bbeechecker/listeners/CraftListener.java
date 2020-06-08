package bonn2.bbeechecker.listeners;

import java.util.Arrays;

import bonn2.bbeechecker.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CraftListener implements Listener {
    
    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {

        FileConfiguration config = Main.plugin.getConfig();

        ItemStack item = e.getInventory().getResult();
        if (item == null) return;
        if (item.getType() == Material.BEE_NEST || item.getType() == Material.BEEHIVE && config.getBoolean("lore")) {
            ItemMeta meta = item.getItemMeta();
            String message = config.getString("Lore.AmountPlural");
            message = message.replaceAll("%number%", "" + 0);
            meta.setLore(Arrays.asList(message));
            item.setItemMeta(meta);
        }
    }
}