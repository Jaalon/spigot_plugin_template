package org.jaalon.spigotmc.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DiamondHelper implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack diamond = new ItemStack(Material.DIAMOND);
            diamond.setAmount(64);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(diamond);

        }

        return true;
    }
}
