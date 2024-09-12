package me.lofro.divineReminder.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Getter;
import me.lofro.divineReminder.global.soundevents.ModSoundEvents;
import me.lofro.divineReminder.global.utils.ModConstants;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;

public class JesusMemeHud implements HudRenderCallback {

    public static final float PHASE_DURATION = 3.0f * 20f;

    @Getter
    public static final JesusMemeHud instance = new JesusMemeHud();

    private static final Identifier CHRIST_IMAGE = new Identifier(ModConstants.MOD_ID, "textures/sprites/jesus.png");

    @Getter
    private static float tickDuration = -1;
    private static float soundTicker = -1;

    private static void playSound(MinecraftClient minecraftClient, SoundEvent jesusSound) {
        final @Nullable ClientPlayerEntity clientPlayer = minecraftClient.player;
        if (clientPlayer == null) return;

        clientPlayer.playSound(jesusSound, 1.0f, 1.0f);
    }

    public void tickDuration(float tickDuration) {
        JesusMemeHud.tickDuration = tickDuration;
        soundTicker = -1;
    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if (tickDuration <= 0) return;

        final MinecraftClient minecraftClient = MinecraftClient.getInstance();

        final int screenWidth = minecraftClient.getWindow().getScaledWidth();
        final int screenHeight = minecraftClient.getWindow().getScaledHeight();

        final float module = tickDuration % PHASE_DURATION;
        final float opacityCoefficient = 1-0f - module / PHASE_DURATION;

        final float opacity = (float) Math.max(0, Math.min(1, Math.pow(1 - opacityCoefficient, 4.0)));

        if (soundTicker <= 0) {
            soundTicker = PHASE_DURATION;
            minecraftClient.execute(() -> JesusMemeHud.playSound(minecraftClient, ModSoundEvents.JESUS_MEME_CLOCK_BELL));
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, opacity);
        RenderSystem.setShaderTexture(0, JesusMemeHud.CHRIST_IMAGE);
        RenderSystem.enableBlend();

        DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

        RenderSystem.disableBlend();

        tickDuration -= tickDelta;
        soundTicker -= tickDelta;
    }

}