package bonn2.bbeechecker.listeners;

import bonn2.bbeechecker.Main;
import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.meta.BlockStateMeta;

public class InteractListener implements Listener  {
    
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {

        FileConfiguration config = Main.plugin.getConfig();

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        int numBees;
        try {
            if (config.getBoolean("ClickOnBlock")) {
                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND) && item.getType() == Material.AIR) {
                    assert block != null;
                    if (block.getState() instanceof Beehive) { // Checks if block is a hive
                        Beehive hive = (Beehive) block.getState();
                        numBees = hive.getEntityCount();
                        String message;
                        if (numBees == 1) {
                            message = config.getString("ChatMessage.AmountSingle");
                        } else {
                            message = config.getString("ChatMessage.AmountPlural");
                        }

                        message = message.replaceAll("%number%", "" + numBees);

                        Main.beesCount = Main.beesCount + numBees;
                        if (config.getString("MessageLocation").equals("chat")) {
                            player.sendMessage(message);
                        } else if (config.getString("MessageLocation").equals("hotbar")) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                        } else {
                            Main.plugin.getLogger().warning("Invalid Chat Location in config.yml!");
                        }
                    }
                }
            } else if (config.getBoolean("ClickInAir") && event.getAction().equals(Action.RIGHT_CLICK_AIR) && event.getHand().equals(EquipmentSlot.HAND)) {
                if (item.getType() == Material.BEEHIVE || item.getType() == Material.BEE_NEST) {
                    numBees = 0;
                    if (item.getItemMeta() instanceof BlockStateMeta) {
                        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
                        Beehive hive = (Beehive) meta.getBlockState();
                        numBees = hive.getEntityCount();
                    }

                    String message;
                    if (numBees == 1) {
                        message = config.getString("ChatMessage.AmountSingle" + "");

                    } else {
                        message = config.getString("ChatMessage.AmountPlural" + "");

                    }
                    message = message.replaceAll("%number%", "" + numBees);

                    Main.beesCount = Main.beesCount + numBees;
                    if (config.getString("MessageLocation").toLowerCase().equals("chat")) {
                        player.sendMessage(message);
                    } else if (config.getString("MessageLocation").toLowerCase().equals("hotbar")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                    } else {
                        Main.plugin.getLogger().warning("Invalid Chat Location in config.yml!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}