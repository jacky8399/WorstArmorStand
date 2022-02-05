package dev.sunbread.worstarmorstand.gui;

import dev.sunbread.worstarmorstand.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class GUI {
    private static final int GUI_SIZE = 54;
    private static final ItemStack BACKGROUND_ITEM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

    public static void createGUI(Player player, ArmorStand as) {
        var name = as.getCustomName();
        name = Util.truncateString(name == null ? as.getName() : name, 32);
        var inv = Bukkit.createInventory(new GUIInventoryHolder(new GUIMetaData(as)), GUI_SIZE, name);
        refreshGUI(player, inv);
        player.openInventory(inv);
        GUIRecorder.INSTANCE.recordUse(player, as);
    }

    public static void click(Player player, Inventory inv, int slot, ClickType click, ItemStack current, ItemStack cursor) {
        var feature = Features.getFeatureBySlot(slot);
        if (feature == null) return;
        if (!player.hasPermission(feature.getPermission())) return;
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1.0F, 2.0F);
        feature.getContent().operate(player, getMetaData(inv), click, current, cursor);
        refreshGUI(player, inv);
    }

    public static void refreshGUI(Player player, Inventory inv) {
        for (int slot = 0; slot < inv.getSize(); slot++) {
            inv.setItem(slot, BACKGROUND_ITEM);
        }
        for (var feature : Features.values()) {
            if (!player.hasPermission(feature.getPermission())) continue;
            inv.setItem(feature.getSlot(), new ItemStack(feature.getContent().getItem(player, getMetaData(inv))));
        }
    }

    public static boolean isInventoryGUI(Inventory inv) {
        return getMetaData(inv) != null;
    }

    public static GUIMetaData getMetaData(Inventory inv) {
        return inv.getHolder() instanceof GUIInventoryHolder holder ? holder.getMetaData() : null;
    }

}
