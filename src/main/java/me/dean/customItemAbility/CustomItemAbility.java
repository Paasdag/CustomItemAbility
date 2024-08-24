package me.dean.customItemAbility;

import me.dean.customItemAbility.Listeners.SwordListener;
import me.dean.customItemAbility.commands.CIA;
import me.dean.customItemAbility.commands.giveSword;
import me.dean.customItemAbility.tabcompleter.CIATabComplete;
import me.dean.customItemAbility.tabcompleter.giveSwordTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItemAbility extends JavaPlugin {

    @Override
    public void onEnable() {
        // config stuff
        saveDefaultConfig();

        //events
        getServer().getPluginManager().registerEvents(new SwordListener(this), this);

        // commands
        getCommand("givesword").setExecutor(new giveSword());
        getCommand("givesword").setTabCompleter(new giveSwordTabComplete());

        getCommand("CIA").setExecutor(new CIA(this));
        getCommand("CIA").setTabCompleter(new CIATabComplete());
    }

    @Override
    public void onDisable() {

    }
}
