package com.tristian.monumentabaernecessities.features;

import com.tristian.monumentabaernecessities.api.features.Feature;
import com.tristian.monumentabaernecessities.features.debug.ItemDebuggingHelpers;
import com.tristian.monumentabaernecessities.features.inventory.AbbreviateRarity;
import com.tristian.monumentabaernecessities.features.inventory.ColorByWoolDungeon;
import com.tristian.monumentabaernecessities.features.overlays.CZCharmOverlay;
import com.tristian.monumentabaernecessities.features.player.PsPlayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A Container to hold all the features
 */
public class Features {

    public static final AbbreviateRarity abbreviateRarityFeature;

    public static final ColorByWoolDungeon colorByWoolDungeonFeature;

    public static final CZCharmOverlay czCharmOverlay;

    public static final PsPlayer psPlayer;

    public static final ItemDebuggingHelpers itemDebuggingHelpers;

    private static final List<? extends Feature> features;


    static {
        abbreviateRarityFeature = new AbbreviateRarity();
        colorByWoolDungeonFeature = new ColorByWoolDungeon();
        czCharmOverlay = new CZCharmOverlay();
        psPlayer = new PsPlayer();
        itemDebuggingHelpers = new ItemDebuggingHelpers();
        features = Arrays.asList(abbreviateRarityFeature,
                colorByWoolDungeonFeature,
                czCharmOverlay,
                psPlayer,
                itemDebuggingHelpers);
    }

    public static void loadFeatures() {
        features.forEach(Feature::init);
    }

}
