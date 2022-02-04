package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.edit.Mover;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public final class MoverListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (!Mover.INSTANCE.isMoving(event.getPlayer())) return;
        switch (event.getAction()) {
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> Mover.INSTANCE.setTarget(event.getPlayer(), null);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        if (!Mover.INSTANCE.isMoving(player)) return;
        Mover.INSTANCE.setTarget(player, null);
        event.setCancelled(true);
    }

}
