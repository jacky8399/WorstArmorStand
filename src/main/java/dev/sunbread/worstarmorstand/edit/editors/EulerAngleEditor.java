package dev.sunbread.worstarmorstand.edit.editors;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

public final class EulerAngleEditor implements Editor {
    private final ArmorStand as;
    private final PoseType type;
    private Mode mode;

    public EulerAngleEditor(ArmorStand as, PoseType type) {
        this.as = as;
        this.type = type;
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
        var velocity = Math.toRadians(Math.signum(delta) * Math.pow(delta, 4) * 0.2);
        switch (mode) {
            case X -> dx += velocity;
            case Y -> dy += velocity;
            case Z -> dz += velocity;
        }
        switch (type) {
            case HEAD -> as.setHeadPose(as.getHeadPose().add(dx, dy, dz));
            case BODY -> as.setBodyPose(as.getBodyPose().add(dx, dy, dz));
            case LEFT_ARM -> as.setLeftArmPose(as.getLeftArmPose().add(dx, dy, dz));
            case RIGHT_ARM -> as.setRightArmPose(as.getRightArmPose().add(dx, dy, dz));
            case LEFT_LEG -> as.setLeftLegPose(as.getLeftLegPose().add(dx, dy, dz));
            case RIGHT_LEG -> as.setRightLegPose(as.getRightLegPose().add(dx, dy, dz));
        }
    }

    @Override
    public ArmorStand getArmorStand() {
        return as;
    }

    @Override
    public String getHint() {
        return ChatColor.YELLOW + mode.toReadableString();
    }

    public enum PoseType {HEAD, BODY, LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG}

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
