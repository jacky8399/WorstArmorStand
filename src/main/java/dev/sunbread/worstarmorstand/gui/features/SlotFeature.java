package dev.sunbread.worstarmorstand.gui.features;

import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class SlotFeature implements Feature {

    public ItemStack getItem(Player player, GUIMetaData meta) {
        return getSlot(meta);
    }

    @Override
    public void operate(Player player, GUIMetaData meta, ClickType click, @NotNull ItemStack oldItem, @NotNull ItemStack newItem) {
        if (click == ClickType.LEFT && check(player, meta, oldItem, newItem)) {
            setSlot(meta, newItem);
            player.setItemOnCursor(oldItem);
        }
    }

    protected abstract ItemStack getSlot(GUIMetaData meta);

    protected abstract void setSlot(GUIMetaData meta, ItemStack is);

    protected abstract boolean check(Player player, GUIMetaData meta, ItemStack oldItem, ItemStack newItem);

}
