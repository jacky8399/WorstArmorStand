package dev.sunbread.worstarmorstand.gui.features;

import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public interface Feature {

    ItemStack getItem(Player player, GUIMetaData meta);

    void operate(Player player, GUIMetaData meta, ClickType click, ItemStack oldItem, ItemStack newItem);

}
