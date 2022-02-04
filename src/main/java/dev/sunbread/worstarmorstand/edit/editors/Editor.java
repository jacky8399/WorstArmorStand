package dev.sunbread.worstarmorstand.edit.editors;

import org.bukkit.entity.ArmorStand;

public interface Editor {

    void toggleMode();

    void apply(int delta);

    ArmorStand getArmorStand();

    String getHint();

}
