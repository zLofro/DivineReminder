package me.lofro.divineReminder.global.soundevents;

import me.lofro.divineReminder.global.utils.ModConstants;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {

    // The block bell meme sound effect.
    public static SoundEvent JESUS_MEME_CLOCK_BELL;

    // Registers all the mod's ID sound events.
    public static void register() {
        JESUS_MEME_CLOCK_BELL = register("ambient.clockbell.sound");
    }

    /**
     * Registers a new {@link SoundEvent} to the mod's ID.
     *
     * @param id the {@link SoundEvent} ID.
     * @return the registered {@link SoundEvent}
     */
    private static SoundEvent register(String id) {
        var identifier = new Identifier(ModConstants.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

}
