package com.tristian.monumentabaernecessities.api.situationals;

/**
 * Represents a situational i.e Poise
 * Contains a method isActive to determine if the situational is active.
 */
public interface Situational {

    boolean isActive();

    /** i.e "Second Wind"
     *
     * @return The key as stored from a {@link com.tristian.monumentabaernecessities.api.MonumentaItem}
     */
    String enchantKey();
}
