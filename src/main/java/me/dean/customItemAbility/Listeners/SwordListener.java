package me.dean.customItemAbility.Listeners;

import me.dean.customItemAbility.CustomItemAbility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class SwordListener implements Listener {

    private final CustomItemAbility plugin;
    private final int cooldownTime;

    public SwordListener(CustomItemAbility plugin) {
        this.plugin = plugin;
        this.cooldownTime = plugin.getConfig().getInt("global-cooldown");
    }


    private final HashMap<UUID, Long> cooldowns = new HashMap<>();


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (player.hasPermission("CustomSword.UseSword")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Blade of Lightning")) {
                    if (event.getAction().toString().contains("RIGHT_CLICK")) {
                        if (cooldowns.containsKey(player.getUniqueId())) {
                            long timeLeft = ((cooldowns.get(player.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                            if (timeLeft > 0) {
                                player.sendMessage(ChatColor.RED + "You must wait " + timeLeft + " seconds before using this ability again!");
                                return;
                            }
                        }
                        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());

                        player.getWorld().strikeLightning(player.getTargetBlock(null, 50).getLocation());
                        player.getWorld().createExplosion(player.getTargetBlock(null, 50).getLocation(), 6.0F, false, false);
                    }
                } else if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "The stick of banishment")) {
                    if (event.getAction().toString().contains("RIGHT_CLICK")) {
                        if (cooldowns.containsKey(player.getUniqueId())) {
                            long timeLeft = ((cooldowns.get(player.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                            if (timeLeft > 0) {
                                player.sendMessage(ChatColor.RED + "You must wait " + timeLeft + " seconds before using this ability again!");
                                return;
                            }
                        }
                        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
                        //ban code here
                    }
                }
            }
        }
    }
}