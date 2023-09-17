package be.achent.blocktotemlore.Commands;

import be.achent.blocktotemlore.BlockTotemLore;
import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class blocktotemlore implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        BlockTotemLore plugin = BlockTotemLore.getInstance();
        if (args.length == 0) {
            if (sender.hasPermission("blocktotemlore.reload") || sender.hasPermission("blocktotemlore.help")) {
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GOLD + "/blocktotemlore" + ChatColor.WHITE + ": Commandes de BlockTotemLore");
                sender.sendMessage(ChatColor.GOLD + "/blocktotemlore reload" + ChatColor.WHITE + ": Recharge le fichier de config.");
                sender.sendMessage("");
            } else {
                sender.sendMessage(plugin.getMessage("NoPermission"));
            }
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("blocktotemlore.reload")) {
                if (args.length == 1) {
                    File config = new File(plugin.getDataFolder(), "config.yml");
                    File language = new File(plugin.getDataFolder(), "language.yml");
                    if (!config.exists()) {
                        plugin.saveDefaultConfig();
                    } else {
                        plugin.reloadConfig();
                    }
                    if (!language.exists()) {
                        plugin.saveDefaultsMessages();
                    } else {
                        plugin.reloadMessages();
                    }
                    sender.sendMessage(plugin.getMessage("Reloaded"));
                } else {
                    sender.sendMessage("reload");
                }
            } else {
                sender.sendMessage(plugin.getMessage("NoPermission"));
            }
        }
        return false;
    }
}
