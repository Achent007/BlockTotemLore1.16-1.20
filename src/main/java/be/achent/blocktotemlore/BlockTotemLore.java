package be.achent.blocktotemlore;

import be.achent.blocktotemlore.Commands.BlockTotemLoreCommands;
import be.achent.blocktotemlore.Commands.BlockTotemLoreTabCompleter;
import be.achent.blocktotemlore.Event.Event;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockTotemLore extends JavaPlugin {
    public BlockTotemLoreTabCompleter commands;

    private Messages messages;

    public static BlockTotemLore plugin;

    public void onEnable() {
        plugin = this;
        this.messages = new Messages();
        this.messages.saveDefaultConfig();
        saveDefaultConfig();
        getCommand("blocktotemlore").setExecutor(new BlockTotemLoreCommands());
        getCommand("blocktotemlore").setTabCompleter(new BlockTotemLoreTabCompleter());
        getServer().getPluginManager().registerEvents(new Event(), this);
    }

    public static BlockTotemLore getInstance() {
        return plugin;
    }

    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.messages.get().getString(path));
    }

    public void reloadMessages() {
        this.messages.reload();
    }

    public void saveDefaultsMessages() {
        this.messages.saveDefaultConfig();
    }

    public static BlockTotemLore getPlugin() {
        return plugin;
    }

    public ArrayList<String> getBlockedLoreList() {
        return (ArrayList<String>)getConfig().getStringList("block-lore");
    }
}
