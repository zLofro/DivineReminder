package vermillion.productions.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import vermillion.productions.Main;

public class ModServer {

    private final Main main;

    private MinecraftServer minecraftServer;

    public ModServer(final Main main) {
        this.main = main;

        ServerLifecycleEvents.SERVER_STARTED.register((server -> {
            this.minecraftServer = server;
        }));
        ServerLifecycleEvents.SERVER_STOPPED.register((server -> {
        }));
    }

    /**
     * @return the current Minecraft Server.
     */
    public MinecraftServer minecraftServer() {
        return minecraftServer;
    }

}
