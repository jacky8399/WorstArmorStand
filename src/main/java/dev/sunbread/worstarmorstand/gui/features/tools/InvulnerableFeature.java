package dev.sunbread.worstarmorstand.gui.features.tools;

import dev.sunbread.worstarmorstand.Util;
import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import dev.sunbread.worstarmorstand.gui.features.Feature;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class InvulnerableFeature implements Feature {

    @Override
    public ItemStack getItem(Player player, GUIMetaData meta) {
        var is = new ItemStack(Material.TOTEM_OF_UNDYING);
        var im = Objects.requireNonNull(is.getItemMeta());
        im.setLore(List.of(ChatColor.AQUA + "Status: " + Util.genStatus(meta.getArmorStand().isInvulnerable())));
        im.setDisplayName(ChatColor.YELLOW + "Set Invulnerable");
        Util.addItemFlags(im);
        is.setItemMeta(im);
        return is;
    }

    @Override
    public void operate(Player player, GUIMetaData meta, ClickType click, @NotNull ItemStack oldItem, @NotNull ItemStack newItem) {
        var as = meta.getArmorStand();
        as.setInvulnerable(!as.isInvulnerable());
    }

}
