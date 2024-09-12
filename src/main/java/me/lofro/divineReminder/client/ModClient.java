package me.lofro.divineReminder.client;

import lombok.Getter;
import me.lofro.divineReminder.client.render.JesusMemeHud;
import me.lofro.divineReminder.global.soundevents.ModSoundEvents;
import me.lofro.divineReminder.global.utils.ModConstants;
import me.lofro.divineReminder.global.utils.config.DefaultConfig;
import me.lofro.divineReminder.global.utils.config.SimpleConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModClient implements ClientModInitializer {

    private static @Getter ModClient instance;

    private static final @Getter Logger logger = LoggerFactory.getLogger(ModConstants.MOD_ID);

    public Config config() {return config;}
    private Config config;
    public record Config(int phases) {}

    @Override
    public void onInitializeClient() {
        instance = this;

        // Setups the config.
        SimpleConfig config = SimpleConfig.of("divinereminder-config").provider(new DefaultConfig()
                .write("The times the Jesus meme image appears per trigger.")
                .addVal("phases", 5)
        ).request();
        this.config = new Config(
                config.getOrDefault("phases", 5)
        );

        ModSoundEvents.register();

        // Registers the Jesus meme hud callback.
        HudRenderCallback.EVENT.register(JesusMemeHud.getInstance());
    }

}
