package me.lofro.jesusScreamer.global.mixin.client.entity;

import com.mojang.authlib.GameProfile;
import me.lofro.jesusScreamer.client.events.ClientPlayerEvents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void modifyApplyDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (!this.isInvulnerableTo(source)) {
            var oldHealth = this.getHealth();
            var newHealth = this.getHealth() - amount;

            this.setHealth(newHealth);

            ClientPlayerEvents.PlayerHealthUpdateEvent.EVENT.invoker().update(((ClientPlayerEntity) (Object)this), oldHealth, newHealth);
        }
        ci.cancel();
    }

}
