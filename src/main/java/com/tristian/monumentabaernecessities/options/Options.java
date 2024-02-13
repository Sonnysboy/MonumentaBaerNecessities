package com.tristian.monumentabaernecessities.options;

import ch.njol.minecraft.config.annotations.Category;
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


    @Override
    public void onUpdate() {
        MonumentaBaerNecessities.saveConfig();

    }

    public boolean categoryVisible(String category) {
        return debugOptionsEnabled || !category.equals("debug");
    }

}
