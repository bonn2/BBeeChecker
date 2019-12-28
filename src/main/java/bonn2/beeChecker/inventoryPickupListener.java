package bonn2.beeChecker;

import java.util.Arrays;
// import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class inventoryPickupListener implements Listener {

    FileConfiguration config = Main.plugin.getConfig();
    // Logger logger = Main.plugin.getLogger();
    
    @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent e) {
        if (config.getBoolean("Lore")) {

            ItemStack item = e.getItem().getItemStack();
            if (item.getType() == Material.BEEHIVE || item.getType() == Material.BEE_NEST) {
                net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                int numBees = 0;

                try {
                    String s = nmsItem.getTag().toString();
                    String text = "HasStung:";
                    for (int i = 0; i < s.length(); i++) {
                        if (s.substring(i).startsWith(text)) {
                            numBees++;
                        }
                    }
                } catch (Exception ex) {numBees = 0;}

                ItemMeta meta = item.getItemMeta();

                String message;
                if (numBees == 1) {
                    message = config.getString("Lore.AmountSingle");

                    while (message.contains("%number%")) {
                        message = message.replaceFirst("%number%", "" + numBees);
                    }
                    meta.setLore(Arrays.asList(message));
                } else {
                    message = config.getString("Lore.AmountPlural");

                    while (message.contains("%number%")) {
                        message = message.replaceFirst("%number%", "" + numBees);
                    }
                    meta.setLore(Arrays.asList(message));
                }

                item.setItemMeta(meta);
            }
        }
    }
}