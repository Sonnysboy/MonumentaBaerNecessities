package com.tristian.monumentabaernecessities.api.situationals;

import com.tristian.monumentabaernecessities.api.MonumentaItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

import java.util.Optional;

/**
 * Represents a situational i.e Poise
 * Contains a method isActive to determine if the situational is active.
 */
public abstract class Situational {

    private final String statKey;

    public Situational(String statKey) {
        this.statKey = statKey;
    }

    /**
     *
     * @return Whether the situational is active right now.
     */
    public abstract boolean isActive();

    /** i.e "second_wind"
     *
     * @return The key as stored inside an {@link com.tristian.monumentabaernecessities.api.stats.ItemStats} object from a {@link com.tristian.monumentabaernecessities.api.MonumentaItem}
     */
    public String getStatKey() {
        return this.statKey;
    }

    /**
     *
     * @return Whether the player has this enchantment.
     */
    protected boolean isEquipped() {

        assert MinecraftClient.getInstance().player != null;
        for (ItemStack x : MinecraftClient.getInstance().player.getItemsEquipped()) {
            if (MonumentaItem.of(x).map(MonumentaItem::getStats).map(stats -> stats.containsKey(this.getStatKey())).orElse(false)) return true;
        }
        return false;

    }
}
