package dev.sunbread.worstarmorstand.edit.inputs;

import org.bukkit.entity.ArmorStand;

public interface Input {

    boolean input(String str);

    ArmorStand getArmorStand();

    String getHint();

}
