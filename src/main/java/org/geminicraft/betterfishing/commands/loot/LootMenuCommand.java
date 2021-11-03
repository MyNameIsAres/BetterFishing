package org.geminicraft.betterfishing.commands.loot;

import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;


public class LootMenuCommand extends SimpleSubCommand {

    public LootMenuCommand(SimpleCommandGroup parent) {
        super(parent, "menu");
        setDescription("Show an overview of all the loot available");
        setUsage("/fish menu");
    }

    @Override
    protected void onCommand() {
    }

}

