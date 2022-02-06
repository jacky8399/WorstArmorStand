package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.gui.GUI;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class SelectorListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (!(event.getRightClicked() instanceof ArmorStand as)) return;
        if (Util.isUsing(event.getPlayer()) || Util.isInUse(as)) return;
        if (event.getPlayer().isSneaking()) {
            GUI.createGUI(event.getPlayer(), as);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Util.close(event.getPlayer());
    }

}
