package dev.sunbread.worstarmorstand.gui.features.slots;

import dev.sunbread.worstarmorstand.gui.GUIMetaData;
import dev.sunbread.worstarmorstand.gui.features.SlotFeature;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public final class LegsSlotFeature extends SlotFeature {

    @Override
    protected ItemStack getSlot(GUIMetaData meta) {
        return Objects.requireNonNull(meta.getArmorStand().getEquipment()).getLeggings();
    }

    @Override
    protected void setSlot(GUIMetaData meta, ItemStack is) {
        Objects.requireNonNull(meta.getArmorStand().getEquipment()).setLeggings(is);
    }

}
