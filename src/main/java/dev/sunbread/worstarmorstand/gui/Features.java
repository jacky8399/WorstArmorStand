package dev.sunbread.worstarmorstand.gui;

import dev.sunbread.worstarmorstand.gui.features.Feature;
import dev.sunbread.worstarmorstand.gui.features.poses.*;
import dev.sunbread.worstarmorstand.gui.features.slots.*;
import dev.sunbread.worstarmorstand.gui.features.tools.*;

public enum Features {
    HEAD_SLOT(1, new HeadSlotFeature(), "worstas.use.slot"),
    BODY_SLOT(10, new BodySlotFeature(), "worstas.use.slot"),
    LEGS_SLOT(19, new LegsSlotFeature(), "worstas.use.slot"),
    FEET_SLOT(28, new FeetSlotFeature(), "worstas.use.slot"),
    RIGHT_HAND_SLOT(9, new RightHandSlotFeature(), "worstas.use.slot"),
    LEFT_HAND_SLOT(11, new LeftHandSlotFeature(), "worstas.use.slot"),
    HEAD_POSE(7, new HeadPoseFeature(), "worstas.use.pose"),
    BODY_POSE(16, new BodyPoseFeature(), "worstas.use.pose"),
    RIGHT_ARM_POSE(15, new RightArmPoseFeature(), "worstas.use.pose"),
    LEFT_ARM_POSE(17, new LeftArmPoseFeature(), "worstas.use.pose"),
    RIGHT_LEG_POSE(24, new RightLegPoseFeature(), "worstas.use.pose"),
    LEFT_LEG_POSE(26, new LeftLegPoseFeature(), "worstas.use.pose"),
    SHIFT(33, new ShiftFeature(), "worstas.use.shift"),
    ROT(34, new RotateFeature(), "worstas.use.rot"),
    MOVE(35, new MoveFeature(), "worstas.use.move"),
    NAME(36, new NameFeature(), "worstas.use.name"),
    ARMS(37, new ArmsFeature(), "worstas.use.arms"),
    BASE(38, new BaseFeature(), "worstas.use.base"),
    VIS(39, new VisibleFeature(), "worstas.use.vis"),
    LOCK(40, new EquipmentLockFeature(), "worstas.use.lock"),
    GRAV(41, new GravityFeature(), "worstas.use.grav"),
    INVUL(42, new InvulnerableFeature(), "worstas.use.invul"),
    SMALL(43, new SmallFeature(), "worstas.use.small"),
    GLOW(44, new GlowingFeature(), "worstas.use.glow"),
    CLONE(45, new CloneFeature(), "worstas.use.clone"),
    DEL(53, new DeleteFeature(), "worstas.use.del");

    private final int slot;
    private final Feature content;
    private final String permission;

    Features(int slot, Feature content, String permission) {
        this.slot = slot;
        this.content = content;
        this.permission = permission;
    }

    public int getSlot() {
        return slot;
    }

    public Feature getContent() {
        return content;
    }

    public String getPermission() {
        return permission;
    }

    public static Features getFeatureBySlot(int slot) {
        for (var feature : values()) {
            if (feature.getSlot() == slot) return feature;
        }
        return null;
    }

}
