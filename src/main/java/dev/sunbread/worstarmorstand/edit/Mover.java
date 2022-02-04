package dev.sunbread.worstarmorstand.edit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.*;

public final class Mover {
    public static final Mover INSTANCE = new Mover();
    private final Map<UUID, UUID> targets = new HashMap<>();

    private Mover() {
    }

    public boolean isMoving(Player player) {
        return targets.containsKey(player.getUniqueId());
    }

    public boolean isInUse(ArmorStand as) {
        return targets.containsValue(as.getUniqueId());
    }

    public void move(Player player) {
        Optional.of(targets.get(player.getUniqueId())).flatMap(it -> Optional.ofNullable(Bukkit.getEntity(it))).ifPresent(target -> {
            var world = Objects.requireNonNull(player.getLocation().getWorld());
            var start = player.getEyeLocation();
            var direction = start.getDirection();
            var raytrace = world.rayTraceBlocks(start, direction, 10.0, FluidCollisionMode.NEVER, true);
            if (raytrace != null && raytrace.getHitBlock() != null && raytrace.getHitBlockFace() != null) {
                var loc = raytrace.getHitPosition().toLocation(world);
                var block = raytrace.getHitBlock();
                loc.setY(block.getY() + 1);
                raytrace.getHitBlock().getCollisionShape().getBoundingBoxes().forEach(bb -> {
                    var y = block.getY() + bb.getMaxY();
                    if (loc.getY() > y) loc.setY(y);
                });
                if (!loc.getBlock().getType().isSolid()) {
                    loc.setYaw(start.getYaw() + 180);
                    target.teleport(loc);
                }
            }
            showTitle(player);
        });
    }

    public void setTarget(Player player, ArmorStand target) {
        if (target == null) {
            targets.remove(player.getUniqueId());
            player.resetTitle();
        } else {
            targets.put(player.getUniqueId(), target.getUniqueId());
            showTitle(player);
        }
    }

    private void showTitle(Player player) {
        player.sendTitle(ChatColor.YELLOW + "Move", ChatColor.YELLOW + "Drag mouse to move, left click to drop",
                0, 70, 0);
    }

}
