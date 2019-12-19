package bonn2.beeChecker;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class craftListener implements Listener {
/*    @EventHandler
    public void onPlayerPickup(CraftItemEvent e) {
        ItemStack item = e.getInventory().getResult();
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

            if (numBees == 1) {
                meta.setLore(Arrays.asList("§rThere is §6" + numBees + " bee §rin this hive."));
            } else {
                meta.setLore(Arrays.asList("§rThere are §6" + numBees + " bees §rin this hive."));
            }
            
            item.setItemMeta(meta);
        }
    } */
}