package dev.sunbread.worstarmorstand.gui;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GUIRecorder {
    public static final GUIRecorder INSTANCE = new GUIRecorder();
    private final Map<UUID, UUID> targets = new HashMap<>();

    public void recordUse(Player player, ArmorStand as) {
        targets.put(player.getUniqueId(), as.getUniqueId());
    }

    public void recordClose(Player player) {
        targets.remove(player.getUniqueId());
    }

    public boolean isInUse(ArmorStand as) {
        return targets.containsValue(as.getUniqueId());
    }

}
