package dev.sunbread.worstarmorstand.edit;

import dev.sunbread.worstarmorstand.edit.editors.Editor;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class EditorManager {
    public static final EditorManager INSTANCE = new EditorManager();
    private final Map<UUID, Editor> editors = new HashMap<>();
    private final Map<UUID, UUID> targets = new HashMap<>();

    private EditorManager() {
    }

    public boolean isEditing(Player player) {
        return editors.containsKey(player.getUniqueId());
    }

    public boolean isInUse(ArmorStand as) {
        return targets.containsValue(as.getUniqueId());
    }

    public void toggleMode(Player player) {
        Optional.of(editors.get(player.getUniqueId())).ifPresent(it -> {
            it.toggleMode();
            showTitle(player, it.getHint());
        });
    }

    public void apply(Player player, int delta) {
        Optional.of(editors.get(player.getUniqueId())).ifPresent(it -> {
            it.apply(delta);
            showTitle(player, it.getHint());
        });
    }

    public void setEditor(Player player, Editor editor) {
        if (editor == null) {
            editors.remove(player.getUniqueId());
            targets.remove(player.getUniqueId());
            showTitle(player, null);
        } else {
            editors.put(player.getUniqueId(), editor);
            targets.put(player.getUniqueId(), editor.getArmorStand().getUniqueId());
            showTitle(player, editor.getHint());
        }
    }

    private void showTitle(Player player, String text) {
        if (text == null) player.resetTitle();
        else player.sendTitle(text, ChatColor.YELLOW + "Toggle mode: F Key, Leave: Mouse Left", 0, 600, 0);
    }

}
