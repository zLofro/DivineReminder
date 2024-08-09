package me.lofro.jesusScreamer.client.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.ActionResult;

public class ClientPlayerEvents {

    @FunctionalInterface
    public interface PlayerHealthUpdateEvent {
        Event<PlayerHealthUpdateEvent> EVENT = EventFactory.createArrayBacked(PlayerHealthUpdateEvent.class,
                (listeners) -> (player, oldHealth, newHealth) -> {
                    for (PlayerHealthUpdateEvent listener : listeners) {
                        ActionResult actionResult = listener.update(player, oldHealth, newHealth);

                        if (actionResult != ActionResult.PASS) {
                            return actionResult;
                        }
                    }
                    return ActionResult.PASS;
                });
        ActionResult update(ClientPlayerEntity player, double oldHealth, double newHealth);
    }

}
