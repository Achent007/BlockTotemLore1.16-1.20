package be.achent.blocktotemlore.chatcolorhandler.parsers.custom;

import org.bukkit.entity.Player;

public interface Parser {
    String parseString(String string);
    String parseString(String string, Player player);
    // TODO: add stripColor method
}
