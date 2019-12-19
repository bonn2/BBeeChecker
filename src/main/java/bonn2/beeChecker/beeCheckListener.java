package bonn2.beeChecker;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
// import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.TileEntityBeehive;

public class beeCheckListener implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        ItemStack i = p.getInventory().getItemInMainHand();
        CraftWorld cw = (CraftWorld) e.getPlayer().getLocation().getWorld(); // Get world player is in
        int numBees = 0;
        try {
        if (e.getHand().equals(EquipmentSlot.HAND) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && i.getType() == Material.AIR) { // If the event is fired by main hand rightclick
            if (b.getType() == Material.BEEHIVE || b.getType() == Material.BEE_NEST) { // Checks if block is a hive
                
                TileEntityBeehive te = (TileEntityBeehive) cw.getHandle().getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ())); // Get Tile Entity
                Object[] a = te.m().toArray(); // Gets Bee nbt and adds it into an array
                numBees = a.length; // Calculates amount by using number of entrys in array

                if (numBees == 1) {
                    p.sendMessage("There is §6" + numBees + " bee §rin this hive.");
                } else {
                    p.sendMessage("There are §6" + numBees + " bees §rin this hive.");
                }
            }
        } /* else if (e.getHand().equals(EquipmentSlot.HAND) && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (i.getType() == Material.BEEHIVE || i.getType() == Material.BEE_NEST) {
                net.minecraft.server.v1_15_R1.ItemStack item = CraftItemStack.asNMSCopy(i);
                numBees = 0;

                try {
                    String s = item.getTag().toString();
                    String text = "HasStung:";
                    for (int iter = 0; iter < s.length(); iter++) {
                        if (s.substring(iter).startsWith(text)) {
                            numBees++;
                        }
                    }
                } catch (Exception ex) {numBees = 0;}
                
                if (numBees == 1) {
                    p.sendMessage("There is §6" + numBees + " bee §rin this hive.");
                } else {
                    p.sendMessage("There are §6" + numBees + " bees §rin this hive.");
                }
            }
        } */
        } catch (Exception error) {

        }
    }
}