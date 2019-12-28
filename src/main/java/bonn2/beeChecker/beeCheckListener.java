package bonn2.beeChecker;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.TileEntityBeehive;

public class beeCheckListener implements Listener  {
    
    FileConfiguration config = Main.plugin.getConfig();

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        ItemStack i = p.getInventory().getItemInMainHand();
        CraftWorld cw = (CraftWorld) e.getPlayer().getLocation().getWorld(); // Get world player is in
        int numBees = 0;
        try {
            if (config.getBoolean("ClickOnBlock") && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getHand().equals(EquipmentSlot.HAND) && i.getType() == Material.AIR) { // If the event is fired by main hand rightclick
                if (b.getType() == Material.BEEHIVE || b.getType() == Material.BEE_NEST) { // Checks if block is a hive
                
                    TileEntityBeehive te = (TileEntityBeehive) cw.getHandle().getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ())); // Get Tile Entity
                    Object[] a = te.m().toArray(); // Gets Bee nbt and adds it into an array
                    numBees = a.length; // Calculates amount by using number of entrys in array

                    String message;
                    if (numBees == 1) {
                        message = config.getString("ChatMessage.AmountSingle");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }

                        p.sendMessage(message);
                    } else {
                        message = config.getString("ChatMessage.AmountPlural");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }

                        p.sendMessage(message);
                    }
                }
            } else if (config.getBoolean("ClickInAir") && e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getHand().equals(EquipmentSlot.HAND)) {
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
                    } catch (Exception ignored) {numBees = 0;}
                
                    String message;
                    if (numBees == 1) {
                        message = config.getString("ChatMessage.AmountSingle");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }

                        p.sendMessage(message);
                    } else {
                        message = config.getString("ChatMessage.AmountPlural");

                        while (message.contains("%number%")) {
                            message = message.replaceFirst("%number%", "" + numBees);
                        }

                        p.sendMessage(message);
                    }
                }
            }
        } catch (Exception ignored) { }
    }
}