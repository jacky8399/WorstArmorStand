package dev.sunbread.worstarmorstand.hooks;

import dev.sunbread.worstarmorstand.gui.GUI;
import dev.sunbread.worstarmorstand.gui.GUIRecorder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public final class GUIListener implements Listener {

    public static final ItemStack AIR = new ItemStack(Material.AIR);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!GUI.isInventoryGUI(event.getView().getTopInventory())) return;
        if (event.getView().convertSlot(event.getRawSlot()) == event.getRawSlot()) {
            if (event.getWhoClicked() instanceof Player clicker) {
                var current = event.getCurrentItem();
                GUI.click(clicker, event.getInventory(), event.getRawSlot(), event.getClick(), current != null ? current : AIR, event.getCursor());
            }
            event.setCancelled(true);
        } else if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!GUI.isInventoryGUI(event.getView().getTopInventory())) return;
        for (int rawSlot : event.getRawSlots())
            if (event.getView().convertSlot(rawSlot) == rawSlot) {
                event.setCancelled(true);
                break;
            }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!GUI.isInventoryGUI(event.getView().getTopInventory())) return;
        if (event.getPlayer() instanceof Player player)
            GUIRecorder.INSTANCE.recordClose(player);
    }

}
