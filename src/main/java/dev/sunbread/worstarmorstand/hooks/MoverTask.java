package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.edit.Mover;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public final class MoverTask extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream().filter(Mover.INSTANCE::isMoving).forEach(Mover.INSTANCE::move);
    }

}
