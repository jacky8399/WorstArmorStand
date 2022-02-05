package dev.sunbread.worstarmorstand.edit.editors;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

public final class YawEditor implements Editor {
    private final ArmorStand as;

    public YawEditor(ArmorStand as) {
        this.as = as;
    }

    @Override
    public void toggleMode() {
    }

    @Override
    public void apply(int delta) {
        var velocity = Math.signum(delta) * Math.pow(delta, 2) * 1;
        var loc = as.getLocation();
        loc.setYaw((float) (loc.getYaw() + velocity));
        as.teleport(loc);
    }

    @Override
    public ArmorStand getArmorStand() {
        return as;
    }

    @Override
    public String getHint() {
        return ChatColor.YELLOW + "Rotate";
    }

}
