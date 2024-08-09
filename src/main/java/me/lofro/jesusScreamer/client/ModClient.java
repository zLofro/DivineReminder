package me.lofro.jesusScreamer.client;

import lombok.Getter;
import me.lofro.jesusScreamer.client.jesus.JesusManager;
import me.lofro.jesusScreamer.global.utils.ModConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModClient implements ClientModInitializer, ModInitializer {

    private static @Getter ModClient instance;

    private @Getter JesusManager jesusManager;

    private @Getter ExecutorService modExecutorService;

    private static final @Getter Logger logger = LoggerFactory.getLogger(ModConstants.MOD_ID);

    @Override
    public void onInitializeClient() {
        this.modExecutorService = Executors.newSingleThreadExecutor();

        this.jesusManager = new JesusManager(this);
    }

    @Override
    public void onInitialize() {

    }

}
