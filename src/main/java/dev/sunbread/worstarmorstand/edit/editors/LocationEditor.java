package dev.sunbread.worstarmorstand.edit.editors;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

public final class LocationEditor implements Editor {
    private final ArmorStand as;
    private Mode mode;

    public LocationEditor(ArmorStand as) {
        this.as = as;
        this.mode = Mode.X;
    }

    @Override
    public void toggleMode() {
        switch (mode) {
            case X -> mode = Mode.Y;
            case Y -> mode = Mode.Z;
            case Z -> mode = Mode.X;
        }
    }

    @Override
    public void apply(int delta) {
        double dx = 0, dy = 0, dz = 0;
        var velocity = Math.signum(delta) * Math.pow(delta, 2) * 0.1;
        switch (mode) {
            case X -> dx += velocity;
            case Y -> dy += velocity;
            case Z -> dz += velocity;
        }
        as.teleport(as.getLocation().add(dx, dy, dz));
    }

    @Override
    public ArmorStand getArmorStand() {
        return as;
    }

    @Override
    public String getHint() {
        return ChatColor.YELLOW + mode.toReadableString();
    }

    private enum Mode {
        X("X-axis"), Y("Y-axis"), Z("Z-axis");

        private final String readableName;

        Mode(String readableName) {
            this.readableName = readableName;
        }

        public String toReadableString() {
            return readableName;
        }

    }

}
