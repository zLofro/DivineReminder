package me.lofro.divineReminder.global.mixin.client.networking;

import me.lofro.divineReminder.client.render.JesusMemeHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onHealthUpdate", at = @At("HEAD"))
    private void modifyHealthUpdate(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        var newHealth = packet.getHealth();

        var clientPlayer = client.player;

        if (clientPlayer != null) {
            var oldHealth = clientPlayer.getHealth();

            if (newHealth <= 2 && !(JesusMemeHud.getTickDuration() > 0) && newHealth < oldHealth) {
                JesusMemeHud.getInstance().tickDuration(5 * JesusMemeHud.PHASE_DURATION);
            }
        }
    }

}
