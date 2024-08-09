package me.lofro.jesusScreamer.client.jesus;

import lombok.Getter;
import lombok.Setter;
import me.lofro.jesusScreamer.client.ModClient;

public class JesusManager {

    private final ModClient modClient;

    private @Getter @Setter boolean alreadyDisplaying;

    public JesusManager(final ModClient modClient) {
        this.modClient = modClient;
        this.alreadyDisplaying = false;
    }

    public void startDisplay() {
        this.alreadyDisplaying = true;
    }

    public void stopDisplay() {
        this.alreadyDisplaying = false;
    }

}
