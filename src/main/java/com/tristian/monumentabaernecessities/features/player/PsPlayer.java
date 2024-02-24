package com.tristian.monumentabaernecessities.features.player;

import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

import java.security.KeyRep;
import java.util.Optional;

/**
 * /ps a player by looking at them and using the keybind
 */
public class PsPlayer {
    public static KeyBinding binding;



    public static void register() {
        binding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.monumenta-baer-necessities.psplayer",
                InputUtil.Type.MOUSE,
                GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
                "category.monumenta-baer-necessities"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (binding.wasPressed()) {
                getHoveredPlayer().ifPresent(x -> {
                    if (MonumentaBaerNecessities.options.debugOptionsEnabled) {
                        MonumentaBaerNecessities.mc.player.sendMessage(Text.of("Looking at something!"));
                    }
                    if (x instanceof PlayerEntity pe) {
                        Text t = pe.getName();
                        if (MonumentaBaerNecessities.options.debugOptionsEnabled) {
                            assert MonumentaBaerNecessities.mc.player != null;
                            MonumentaBaerNecessities.mc.player.sendMessage(Text.of("Looking at player!"));
                            MonumentaBaerNecessities.mc.player.sendMessage(Text.of("Looking at " + t));
                        }
                        assert MonumentaBaerNecessities.mc.player != null;
                        MonumentaBaerNecessities.mc.player.networkHandler.sendChatCommand("ps " + t.getString());

                    }
                });
            }
        });
    }

    private static Optional<Entity> getHoveredPlayer() {
        HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;
        if (null == hitResult) return Optional.empty();
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            return Optional.of(((EntityHitResult) hitResult).getEntity());
        }
        return Optional.empty();
    }
}
