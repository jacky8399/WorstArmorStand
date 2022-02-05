package dev.sunbread.worstarmorstand;

import dev.sunbread.worstarmorstand.hooks.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorstArmorStand extends JavaPlugin {
    static WorstArmorStand plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Metrics(this, 14194);
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new EditorListener(), this);
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new InputListener(), this);
        Bukkit.getPluginManager().registerEvents(new MoverListener(), this);
        Bukkit.getPluginManager().registerEvents(new SelectorListener(), this);
        new MoverTask().runTaskTimer(this, 3L, 3L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Util.closeAll();
        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
        plugin = null;
    }

}
