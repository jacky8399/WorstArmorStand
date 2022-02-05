package dev.sunbread.worstarmorstand.edit.inputs;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

public class NameInput implements Input {
    private final ArmorStand as;

    public NameInput(ArmorStand as) {
        this.as = as;
    }

    @Override
    public boolean input(String str) {
        if (str.equals("&")) {
            as.setCustomName(null);
            as.setCustomNameVisible(false);
        } else {
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', str));
            as.setCustomNameVisible(true);
        }
        return true;
    }

    @Override
    public ArmorStand getArmorStand() {
        return as;
    }

    @Override
    public String getHint() {
        return ChatColor.YELLOW + "Name or & to remove";
    }

}
