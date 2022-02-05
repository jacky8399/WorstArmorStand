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
        double dx = 0, dy = 0, dz = 0;
        var velocity = Math.signum(delta) * Math.pow(delta, 4) * 0.2;
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
        return ChatColor.YELLOW + "Rotation";
    }

}
