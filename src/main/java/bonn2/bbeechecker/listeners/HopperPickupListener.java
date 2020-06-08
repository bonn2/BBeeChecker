package bonn2.bbeechecker.listeners;

import java.util.Arrays;

import bonn2.bbeechecker.Main;
import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;


public class HopperPickupListener implements Listener {
    
    @EventHandler
    public void onHopperPickup(InventoryPickupItemEvent e) {

        FileConfiguration config = Main.plugin.getConfig();

        if (config.getBoolean("lore")) {
            
            ItemStack item = e.getItem().getItemStack();
            if (item.getType() == Material.BEEHIVE || item.getType() == Material.BEE_NEST) {
                int numBees = 0;
                if (item.getItemMeta() instanceof BlockStateMeta) {
                    BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
                    Beehive hive = (Beehive) meta.getBlockState();
                    numBees = hive.getEntityCount();
                }
                ItemMeta meta = item.getItemMeta();

                String message;
                if (numBees == 1) {
                    message = config.getString("Lore.AmountSingle");
                } else {
                    message = config.getString("Lore.AmountPlural");
                }
                message = message.replaceAll("%number%", "" + numBees);
                Main.beesCount = Main.beesCount + numBees;
                meta.setLore(Arrays.asList(message));

                item.setItemMeta(meta);
            }
        }
    }
}