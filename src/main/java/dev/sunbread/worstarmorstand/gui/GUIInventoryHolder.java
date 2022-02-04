package dev.sunbread.worstarmorstand.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public final class GUIInventoryHolder implements InventoryHolder {
    private final GUIMetaData meta;

    public GUIInventoryHolder(GUIMetaData meta) {
        this.meta = meta;
    }

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, InventoryType.CHEST);
    }

    public GUIMetaData getMetaData() {
        return meta;
    }

}
