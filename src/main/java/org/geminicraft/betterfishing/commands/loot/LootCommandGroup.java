package org.geminicraft.betterfishing.commands.loot;

import org.mineacademy.fo.command.SimpleCommandGroup;

public class LootCommandGroup extends SimpleCommandGroup {

	protected void registerSubcommands() {
		registerSubcommand(new LootInfoCommand(this));
		registerSubcommand(new LootMenuCommand(this));
	}
}