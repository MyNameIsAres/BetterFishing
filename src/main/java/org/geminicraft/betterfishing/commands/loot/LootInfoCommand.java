package org.geminicraft.betterfishing.commands.loot;

import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class LootInfoCommand extends SimpleSubCommand {

    public LootInfoCommand(final SimpleCommandGroup parent) {
        super(parent, "info");
        setDescription("Get information about loot!");
        setUsage("/fish info <fish>");
    }

    @Override
    protected void onCommand() {
    }
}

