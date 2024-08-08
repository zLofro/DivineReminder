package vermillion.productions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vermillion.productions.client.ModClient;
import vermillion.productions.global.data.adapters.LocalDateSerializer;
import vermillion.productions.global.utils.ModConstants;
import vermillion.productions.server.ModServer;

import java.time.LocalDate;

public class Main implements ModInitializer {

    private static @Getter Main instance;

    private static final @Getter Logger logger = LoggerFactory.getLogger(ModConstants.MOD_ID);

    private ModServer modServer;
    private ModClient modClient;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    @Override
    public void onInitialize() {
        instance = this;

        this.modServer = new ModServer(this);
        this.modClient = new ModClient();
    }

    /**
     * @return the Gson constant.
     */
    public static Gson gson() {
        return gson;
    }

    /**
     * @return the mod's server manager.
     */
    public ModServer serverManager() {
        return modServer;
    }

    /**
     * @return the mod's client manager.
     */
    public ModClient clientManager() {
        return modClient;
    }

}
