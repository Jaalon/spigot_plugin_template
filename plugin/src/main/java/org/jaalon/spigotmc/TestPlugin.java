package org.jaalon.spigotmc;

import org.bukkit.plugin.java.JavaPlugin;
import org.jaalon.spigotmc.commands.DiamondHelper;
import org.jaalon.spigotmc.listeners.PlayerConnectedListener;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        this.getCommand("diamondsPlease").setExecutor(new DiamondHelper());
        getServer().getPluginManager().registerEvents(new PlayerConnectedListener(), this);

        System.out.println("!!! I enabled the plugin !!!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("!!! I disabled the plugin !!!");
    }
}
