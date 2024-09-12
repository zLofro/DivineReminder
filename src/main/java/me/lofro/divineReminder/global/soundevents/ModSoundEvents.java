package me.lofro.divineReminder.global.soundevents;

import me.lofro.divineReminder.global.utils.ModConstants;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {

    public static SoundEvent JESUS_MEME_CLOCK_BELL;

    public static void register() {
        JESUS_MEME_CLOCK_BELL = register("ambient.clockbell.sound");
    }

    private static SoundEvent register(String id) {
        var identifier = new Identifier(ModConstants.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

}
