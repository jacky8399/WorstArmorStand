package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.edit.InputProcessor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class InputListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!InputProcessor.INSTANCE.isInputting(event.getPlayer())) return;
        var player = event.getPlayer();
        var str = event.getMessage();
        Bukkit.getScheduler().runTask(Util.getPlugin(), () -> InputProcessor.INSTANCE.input(player, str));
        event.setCancelled(true);
    }

}
