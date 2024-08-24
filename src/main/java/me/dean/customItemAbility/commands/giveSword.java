package me.dean.customItemAbility.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class giveSword implements CommandExecutor {


    public ItemStack createCustomSword(String c_sword) {
        if (c_sword.equalsIgnoreCase("blade_of_light")) {
            ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);
            ItemMeta meta = sword.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "Blade of Lightning");
            meta.setLore(Arrays.asList(
                    ChatColor.GREEN + "A powerful sword forged by the gods.",
                    ChatColor.AQUA + "Right-click to summon lightning!"
            ));
            meta.addEnchant(Enchantment.SHARPNESS, 5, true);
            meta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
            meta.setUnbreakable(true);
            sword.setItemMeta(meta);
            return sword;
        }
        if (c_sword.equalsIgnoreCase("ban_stick")){
            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta meta = stick.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "The stick of banishment");
            meta.setLore(Arrays.asList(
                    ChatColor.RED + "A powerfull and mighty stick that bans anyone in contact",
                    ChatColor.AQUA + "Hit a player to ban"
            ));
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            stick.setItemMeta(meta);
            return stick;
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0){
                switch (args[0]){
                    case "lightning_blade":
                        player.getInventory().addItem(new ItemStack(createCustomSword("blade_of_light")));
                        break;
                    case "ban_stick":
                        player.getInventory().addItem(new ItemStack(createCustomSword("ban_stick")));
                        break;
                }
            } else {
                sender.sendMessage("Valid arguments.");
                sender.sendMessage("ligthningblade, banstick");
            }
        }
        return false;
    }
}
