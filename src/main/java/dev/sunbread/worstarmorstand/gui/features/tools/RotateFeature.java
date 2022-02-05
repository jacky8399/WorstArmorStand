package dev.sunbread.worstarmorstand.gui.features.tools;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.edit.EditorManager;
import dev.sunbread.worstarmorstand.edit.editors.YawEditor;
import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import dev.sunbread.worstarmorstand.gui.features.Feature;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public final class RotateFeature implements Feature {

    @Override
    public ItemStack getItem(Player player, GUIMetaData meta) {
        var is = new ItemStack(Material.FLINT);
        var im = Objects.requireNonNull(is.getItemMeta());
        im.setDisplayName(ChatColor.YELLOW + "Rotate Armor Stand");
        Util.addItemFlags(im);
        is.setItemMeta(im);
        return is;
    }

    @Override
    public void operate(Player player, GUIMetaData meta, ClickType click, ItemStack oldItem, ItemStack newItem) {
        Bukkit.getScheduler().runTask(Util.getPlugin(), () -> {
            player.closeInventory();
            EditorManager.INSTANCE.setEditor(player, new YawEditor(meta.getArmorStand()));
        });
    }

}
