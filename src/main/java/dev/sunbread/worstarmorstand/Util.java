package dev.sunbread.worstarmorstand;

import dev.sunbread.worstarmorstand.edit.EditorManager;
import dev.sunbread.worstarmorstand.edit.InputProcessor;
import dev.sunbread.worstarmorstand.edit.Mover;
import dev.sunbread.worstarmorstand.gui.GUI;
import dev.sunbread.worstarmorstand.gui.GUIRecorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public final class Util {

    public static int calculateDelta(int previousSlot, int newSlot) {
        int delta = (9 + newSlot - previousSlot) % 9;
        if (delta > 4) delta -= 9;
        return delta;
    }

    public static boolean isUsing(Player player) {
        return GUI.isInventoryGUI(player.getOpenInventory().getTopInventory()) ||
                EditorManager.INSTANCE.isEditing(player) || InputProcessor.INSTANCE.isInputting(player) ||
                Mover.INSTANCE.isMoving(player);
    }

    public static boolean isInUse(ArmorStand as) {
        return GUIRecorder.INSTANCE.isInUse(as) || EditorManager.INSTANCE.isInUse(as) ||
                InputProcessor.INSTANCE.isInUse(as) || Mover.INSTANCE.isInUse(as);
    }

    public static void closeAll() {
        Bukkit.getOnlinePlayers().forEach(Util::close);
    }

    public static void close(Player player) {
        if (GUI.isInventoryGUI(player.getOpenInventory().getTopInventory())) player.closeInventory();
        if (EditorManager.INSTANCE.isEditing(player)) EditorManager.INSTANCE.setEditor(player, null);
        if (InputProcessor.INSTANCE.isInputting(player)) InputProcessor.INSTANCE.setInput(player, null);
        if (Mover.INSTANCE.isMoving(player)) Mover.INSTANCE.setTarget(player, null);
    }

    public static String truncateString(String str, int length) {
        return str.substring(0, Math.min(str.length(), length));
    }

    public static boolean hasEquipmentLock(ArmorStand as) {
        for (var slot : EquipmentSlot.values())
            for (var lock : org.bukkit.entity.ArmorStand.LockType.values()) {
                if (!as.hasEquipmentLock(slot, lock)) return false;
            }
        return true;
    }

    public static void setEquipmentLock(ArmorStand as, boolean locked) {
        for (var slot : EquipmentSlot.values())
            for (var lock : org.bukkit.entity.ArmorStand.LockType.values()) {
                if (locked) as.addEquipmentLock(slot, lock);
                else as.removeEquipmentLock(slot, lock);
            }
    }

    public static void addItemFlags(ItemMeta meta) {
        meta.addItemFlags(
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_DYE
        );
    }

    public static ArmorStand cloneArmorStand(ArmorStand as) {
        var clone = (ArmorStand) as.getWorld().spawnEntity(as.getLocation(), EntityType.ARMOR_STAND);
        var equip = Objects.requireNonNull(as.getEquipment());
        var cloneEquip = Objects.requireNonNull(clone.getEquipment());
        cloneEquip.setHelmet(equip.getHelmet());
        cloneEquip.setChestplate(equip.getChestplate());
        cloneEquip.setLeggings(equip.getLeggings());
        cloneEquip.setBoots(equip.getBoots());
        cloneEquip.setItemInMainHand(equip.getItemInMainHand());
        cloneEquip.setItemInOffHand(equip.getItemInOffHand());
        clone.setHeadPose(as.getHeadPose());
        clone.setBodyPose(as.getBodyPose());
        clone.setRightArmPose(as.getRightArmPose());
        clone.setLeftArmPose(as.getLeftArmPose());
        clone.setRightLegPose(as.getRightLegPose());
        clone.setLeftLegPose(as.getLeftLegPose());
        clone.setCustomName(as.getCustomName());
        clone.setCustomNameVisible(as.isCustomNameVisible());
        clone.setArms(as.hasArms());
        clone.setBasePlate(as.hasBasePlate());
        clone.setVisible(as.isVisible());
        for (var slot : EquipmentSlot.values())
            for (var lock : org.bukkit.entity.ArmorStand.LockType.values())
                if (as.hasEquipmentLock(slot, lock)) clone.addEquipmentLock(slot, lock);
        clone.setGravity(as.hasGravity());
        clone.setInvulnerable(as.isInvulnerable());
        clone.setSmall(as.isSmall());
        clone.setGlowing(as.isGlowing());
        return clone;
    }

    public static String genStatus(boolean status) {
        return status ? ChatColor.GREEN + "ON" : ChatColor.RED + "OFF";
    }

    public static WorstArmorStand getPlugin() {
        return WorstArmorStand.plugin;
    }

}
