package bonn2.beeChecker;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class craftListener implements Listener {
    
    @EventHandler
    public void onCraft(InventoryClickEvent e) {

        FileConfiguration config = Main.plugin.getConfig();

        ItemStack item = e.getCurrentItem();
        if (item.getType() == Material.BEE_NEST || item.getType() == Material.BEEHIVE && config.getBoolean("lore")) {

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
                } catch (Exception ignored) {numBees = 0;}

                ItemMeta meta = item.getItemMeta();

                String message;
                    if (numBees == 1) {
                        message = config.getString("Lore.AmountSingle");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }
                        Main.beesCount = Main.beesCount + numBees;
                        meta.setLore(Arrays.asList(message));
                    } else {
                        message = config.getString("Lore.AmountPlural");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }
                        Main.beesCount = Main.beesCount + numBees;
                        meta.setLore(Arrays.asList(message));
                    }
            
                item.setItemMeta(meta);
            }
        }
    }
}