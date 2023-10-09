package be.achent.blocktotemlore.Event;

import be.achent.blocktotemlore.BlockTotemLore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static be.achent.blocktotemlore.BlockTotemLore.getPlugin;
import static be.achent.blocktotemlore.BlockTotemLore.plugin;

public class BlockTotemLoreCancelEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTotemUse(EntityResurrectEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if ((p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) && (
                    checkTotem(p.getInventory().getItemInOffHand()) || checkTotem(p.getInventory().getItemInMainHand()))) {
                e.setCancelled(true);
                if (getPlugin().getConfig().getBoolean("blocked-message") == true)
                p.sendMessage(plugin.getMessage("event-canceled"));
            }
        }
    }

    private boolean checkTotem(ItemStack item) {
        if (item.hasItemMeta()) {
            ItemMeta im = item.getItemMeta();
            if (im.hasLore())
                for (String line : im.getLore()) {
                    for (String blackLine : BlockTotemLore.getPlugin().getBlockedLoreList()) {
                        if (ChatColor.stripColor(line).contentEquals(blackLine))
                            return true;
                    }
                }
        }
        return false;
    }
}
