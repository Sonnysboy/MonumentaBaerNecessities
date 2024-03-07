package com.tristian.monumentabaernecessities.hud;

import ch.njol.minecraft.uiframework.ElementPosition;
import ch.njol.minecraft.uiframework.hud.HudElement;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.api.situationals.Situational;
import com.tristian.monumentabaernecessities.api.situationals.Situationals;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SituationalsHud extends HudElement {


    public static SituationalsHud INSTANCE = new SituationalsHud();

    @Override
    protected boolean isEnabled() {
        return MonumentaBaerNecessities.options.displayActiveSituationals_enabled;
    }

    @Override
    protected boolean isVisible() {
        return true;
    }

    @Override
    protected int getWidth() {
        return MonumentaBaerNecessities.mc.textRenderer.getWidth("Reflexes: Inactive.."); // give us a bit more room than we might need
    }

    @Override
    protected int getHeight() {
        return 0;
    }

    @Override
    protected ElementPosition getPosition() {
        return MonumentaBaerNecessities.options.displayActiveSituationals_position;
    }

    @Override
    protected int getZOffset() {
        return 0;
    }

    @Override
    protected void render(DrawContext drawContext, float tickDelta) {
        if (client.options.hudHidden || client.player == null || client.player.isSpectator()) {
            return;
        }
        int i = 0;
        for (SituationalText text: SituationalText.values()) {
            drawOutlinedText(drawContext, text.getDisplayText().toString(), 0, i += MonumentaBaerNecessities.mc.textRenderer.fontHeight + 4, 0);

        }
    }
    // The situationals, and their respective text. Text taken from Wools chattriggers' script.
    private enum SituationalText {
        POISE(Formatting.BLUE + "Poise", Situationals.POISE),
        ETHEREAL(Formatting.DARK_GRAY + "Ethereal", Situationals.ETHEREAL),
        TEMPO(Formatting.GOLD + "Tempo", Situationals.TEMPO),
        REFLEXES(Formatting.DARK_AQUA + "Reflexes", Situationals.REFLEXES),
        CLOAKED(Formatting.DARK_GRAY + "Cloaked", Situationals.CLOAKED);
//        todo steadfast


        private final String text;
        private final Situational situational;

        /**
         *
         * @param text The text displayed in the hud.
         */
        SituationalText(String text, Situational situational) {
            this.text = text;
            this.situational = situational;
        }

        /** Creates the text object for displaying on the UI for this specific situational.
         *
         * @return The text object to be displayed on the ui. I.e, Tempo: Active, Second Wind: Inactive.
         */
        Text getDisplayText() {
            return Text.of(text + Formatting.GRAY + ": " +
                    ((situational.isActive()) ? Formatting.GREEN + "Active" : Formatting.RED + "Inactive"));

        }
    }
}
