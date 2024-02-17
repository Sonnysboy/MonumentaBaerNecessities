package com.tristian.monumentabaernecessities.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 *  Credit: Unofficial monumenta mod
 */
@Mixin(PlayerListHud.class)
public interface PlayerListHudAccessor {

    @Accessor("header")
    Text getHeader();

    @Accessor("footer")
    Text getFooter();
}