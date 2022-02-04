package dev.sunbread.worstarmorstand.gui.features.tools;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.edit.InputProcessor;
import dev.sunbread.worstarmorstand.edit.inputs.NameInput;
import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import dev.sunbread.worstarmorstand.gui.features.Feature;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public final class NameFeature implements Feature {

    @Override
    public ItemStack getItem(Player player, GUIMetaData meta) {
        var is = new ItemStack(Material.NAME_TAG);
        var im = Objects.requireNonNull(is.getItemMeta());
        im.setDisplayName(ChatColor.YELLOW + "Set Display Name");
        var name = meta.getArmorStand().getCustomName();
        im.setLore(List.of(ChatColor.AQUA + "Name: " + ChatColor.BLUE + (name == null ? meta.getArmorStand().getName() : name)));
        Util.addItemFlags(im);
        is.setItemMeta(im);
        return is;
    }

    @Override
    public void operate(Player player, GUIMetaData meta, ClickType click, ItemStack oldItem, ItemStack newItem) {
        Bukkit.getScheduler().runTask(Util.getPlugin(), () -> {
            player.closeInventory();
            InputProcessor.INSTANCE.setInput(player, new NameInput(meta.getArmorStand()));
        });
    }

}
