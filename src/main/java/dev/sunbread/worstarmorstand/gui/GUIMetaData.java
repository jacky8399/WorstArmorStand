package dev.sunbread.worstarmorstand.gui;

import org.bukkit.entity.ArmorStand;

public final class GUIMetaData {
    private final ArmorStand as;

    public GUIMetaData(ArmorStand as) {
        this.as = as;
    }

    public ArmorStand getArmorStand() {
        return as;
    }

}
