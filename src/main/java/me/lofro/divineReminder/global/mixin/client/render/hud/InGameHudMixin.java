package me.lofro.divineReminder.global.mixin.client.render.hud;

import me.lofro.divineReminder.client.render.JesusMemeHud;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void modifyHudTick(CallbackInfo ci) {
        var tickDuration = JesusMemeHud.getTickDuration();

        if (tickDuration <= 0) return;

        JesusMemeHud.setTickDuration(tickDuration - 1);
        JesusMemeHud.setSoundTicker(JesusMemeHud.getSoundTicker() - 1);
    }

}
