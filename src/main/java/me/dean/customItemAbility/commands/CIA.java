package me.dean.customItemAbility.commands;

import me.dean.customItemAbility.CustomItemAbility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;

public class CIA implements CommandExecutor {
    private final CustomItemAbility plugin;
    public CIA(CustomItemAbility plugin) {
        this.plugin = plugin;
    }

    String[] messages = {
            "Developed by dean",
            "Current version 1.0 Dev build",
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length > 0) {
                switch (args[0]){
                    case "info":
                        for (String message : messages) {
                            player.sendMessage(ChatColor.GREEN + message);
                        }
                        break;
                    case "reload":
                        if (player.hasPermission("CustomSword.reload")){
                            plugin.reloadConfig();
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Config has been reloaded");
                        } else {
                            player.sendMessage(ChatColor.RED + "Insufficient permissions");
                        }
                        break;
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Please provide an argument!");
            }
        }
        return false;
    }
}
