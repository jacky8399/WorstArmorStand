package dev.sunbread.worstarmorstand.edit;

import dev.sunbread.worstarmorstand.edit.inputs.Input;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class InputProcessor {
    public static final InputProcessor INSTANCE = new InputProcessor();
    private final Map<UUID, Input> inputs = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> targets = new ConcurrentHashMap<>();

    private InputProcessor() {
    }

    public synchronized boolean isInputting(Player player) {
        return inputs.containsKey(player.getUniqueId());
    }

    public synchronized boolean isInUse(ArmorStand as) {
        return targets.containsValue(as.getUniqueId());
    }

    public synchronized void input(Player player, String str) {
        Optional.of(inputs.get(player.getUniqueId())).ifPresent(it -> {
            if (it.input(str)) {
                setInput(player, null);
            } else {
                showTitle(player, it.getHint());
            }
        });
    }

    public synchronized void setInput(Player player, Input input) {
        if (input == null) {
            inputs.remove(player.getUniqueId());
            targets.remove(player.getUniqueId());
            showTitle(player, null);
        } else {
            inputs.put(player.getUniqueId(), input);
            targets.put(player.getUniqueId(), input.getArmorStand().getUniqueId());
            showTitle(player, input.getHint());
        }
    }

    private void showTitle(Player player, String text) {
        if (text == null) player.resetTitle();
        else player.sendTitle(text, ChatColor.YELLOW + "Input in the chat bar", 0, 70, 0);
    }

}
