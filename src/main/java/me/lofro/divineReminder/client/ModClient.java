package me.lofro.divineReminder.client;

import lombok.Getter;
import me.lofro.divineReminder.client.render.JesusMemeHud;
import me.lofro.divineReminder.global.soundevents.ModSoundEvents;
import me.lofro.divineReminder.global.utils.ModConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModClient implements ClientModInitializer, ModInitializer {

    private static final @Getter Logger logger = LoggerFactory.getLogger(ModConstants.MOD_ID);

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(JesusMemeHud.getInstance());
    }

    @Override
    public void onInitialize() {
        ModSoundEvents.register();
    }

}
