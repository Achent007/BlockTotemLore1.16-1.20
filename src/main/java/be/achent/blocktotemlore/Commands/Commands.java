package be.achent.blocktotemlore.Commands;

import be.achent.blocktotemlore.BlockTotemLore;

public class Commands {
    public void init() {
        BlockTotemLore plugin = BlockTotemLore.getInstance();
        plugin.getCommand("blocktotemlore").setExecutor(new blocktotemlore());
    }
}
