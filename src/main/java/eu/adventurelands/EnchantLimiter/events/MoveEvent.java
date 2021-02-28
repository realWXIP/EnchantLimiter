package eu.adventurelands.EnchantLimiter.events;

import eu.adventurelands.EnchantLimiter.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MoveEvent implements Listener {
    private final Main plugin;

    public MoveEvent(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ() || event.getFrom().getBlockY() != event.getTo().getBlockY()) {
            for (String limit : plugin.getConfig().getConfigurationSection("limit").getKeys(false)) {
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                int value = plugin.getConfig().getInt("limit." + limit);

                if (!plugin.getConfig().getStringList("blacklist.items").contains(item.getType().toString())) {
                    if (!event.getPlayer().hasPermission("limiter.bypass")) {
                        if (item.getEnchantments().containsKey(Enchantment.getByName(limit))) {
                            if (item.getEnchantmentLevel(Enchantment.getByName(limit)) > value) {
                                item.addUnsafeEnchantment(Enchantment.getByName(limit), value);
                            }
                        }
                    }
                }
            }
        }
    }
}
