package me.lofro.jesusScreamer.client.listeners;

import me.lofro.jesusScreamer.client.ModClient;
import me.lofro.jesusScreamer.client.events.ClientPlayerEvents;
import net.minecraft.util.ActionResult;

public class ClientPlayerEventsListener {

    public static void register() {
        onPlayerHealthUpdate();
    }

    public static void onPlayerHealthUpdate() {
        ClientPlayerEvents.PlayerHealthUpdateEvent.EVENT.register((player, oldHealth, newHealth) -> {
            if (newHealth <= 1.5 && !ModClient.getInstance().getJesusManager().isAlreadyDisplaying()) {

            }

            return ActionResult.SUCCESS;
        });
    }

}
