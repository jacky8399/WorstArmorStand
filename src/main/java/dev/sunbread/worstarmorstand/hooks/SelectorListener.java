package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.gui.GUI;
import org.bukkit.*;
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
        if (Util.isUsing(event.getPlayer())) return;
        if (Util.isInUse(as)) {
            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.MASTER, 1.0F, 0.5F);
            event.getPlayer().sendTitle(ChatColor.YELLOW + "In Use",
                    ChatColor.YELLOW + "You can't edit this armor stand at now!", 0, 70, 0);
            return;
        }
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
