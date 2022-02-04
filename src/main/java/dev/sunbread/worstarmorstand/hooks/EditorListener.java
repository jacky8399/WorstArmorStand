package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.edit.EditorManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public final class EditorListener implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (!EditorManager.INSTANCE.isEditing(event.getPlayer())) return;
        EditorManager.INSTANCE.apply(event.getPlayer(), Util.calculateDelta(event.getPreviousSlot(), event.getNewSlot()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (!EditorManager.INSTANCE.isEditing(event.getPlayer())) return;
        switch (event.getAction()) {
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> EditorManager.INSTANCE.setEditor(event.getPlayer(), null);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (!EditorManager.INSTANCE.isEditing(event.getPlayer())) return;
        EditorManager.INSTANCE.toggleMode(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        if (!EditorManager.INSTANCE.isEditing(player)) return;
        EditorManager.INSTANCE.setEditor(player, null);
        event.setCancelled(true);
    }

}
