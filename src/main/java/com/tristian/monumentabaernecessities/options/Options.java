package com.tristian.monumentabaernecessities.options;

import ch.njol.minecraft.config.annotations.Category;
import ch.njol.minecraft.uiframework.ElementPosition;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;

// credit where credit is due: Unofficial Monumenta Mod
public class Options implements ch.njol.minecraft.config.Options {


    @Category("dev")
    public boolean debugOptionsEnabled = false;

    /**
     * For cz hovering tooltip
     */
    @Category("inventory")
    public boolean showCzRolls = true;

    @Category("inventory")
    public boolean inventoryWoolItemOutlines = true;

    /**
     *
     */
    @Category("world")
    public boolean cosmicStyleDamageIndicators = true;

    @Category("hud")
    public boolean displayActiveSituationals_enabled = true;
    @Category("hud")
    public ElementPosition displayActiveSituationals_position = new ElementPosition(1.0f, 0, 0.5f, 0, 0, 0);



    @Override
    public void onUpdate() {
        MonumentaBaerNecessities.saveConfig();

    }

    public boolean categoryVisible(String category) {
        return debugOptionsEnabled || !category.equals("debug");
    }

}
