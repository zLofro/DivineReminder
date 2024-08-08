package vermillion.productions.server.global.utils.customGlyphs;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import vermillion.productions.server.global.utils.timedTitle.InvalidTitleTimings;
import vermillion.productions.server.global.utils.timedTitle.MinimumStayTimeIsGreaterThatStayTime;
import vermillion.productions.server.global.utils.timedTitle.TimedTitle;
import vermillion.productions.server.global.utils.titles.SendActionBar;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Animation {
    public enum Position {Title, Subtitle, ActionBar}

    private static final HashMap<String, Character[]> animations = new HashMap<>(0, 1);

    public static void load(String animation) throws ArrayStoreException, IndexOutOfBoundsException, NoFramesException {
        animation = normalizeAnimation(animation);
        if (animations.get(animation) != null) return;

        ArrayList<Character> frames = new ArrayList<>(0);
        try {
            var inputStreamAnimation = Animation.class.getResourceAsStream("/data/theabyss2/animations/"+animation);

            assert inputStreamAnimation != null;

            JsonArray rawFrames = ((JsonArray) JsonParser.parseReader(new InputStreamReader(inputStreamAnimation)));

            rawFrames.forEach(f -> frames.add((char)f.getAsInt()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ArrayStoreException("An attempt was made to cast an element of the JSONArray to a Character. However, this action failed because the element was NOT an Integer.");
        }

        if (frames.isEmpty()) throw new NoFramesException(animation);

        animations.put(animation, frames.toArray(new Character[0]));
    }

    public static Character[] get(String animation) throws NoSuchAnimationException {
        animation = normalizeAnimation(animation);
        Character[] frames = animations.get(animation);
        if (frames == null) throw new NoSuchAnimationException(animation);
        return frames.clone();
    }

    public static void play(ServerPlayerEntity p, String animation, int fadeIn, int fadeOut) throws NoSuchAnimationException, InvalidTitleTimings, MinimumStayTimeIsGreaterThatStayTime {
        play(p, Position.Title, animation, fadeIn, fadeOut);
    }
    public static void play(ServerPlayerEntity p, Position position, String animation, int fadeIn, int fadeOut) throws NoSuchAnimationException, InvalidTitleTimings, MinimumStayTimeIsGreaterThatStayTime {
        animation = normalizeAnimation(animation);
        Character[] frames = get(animation);

        send(p, position, frames[0], fadeIn, 20, 0, 1);

        int penultimateIndex = frames.length-2;
        if (penultimateIndex != -1) {
            if (penultimateIndex != 0) {
                for (int i = 1; i < penultimateIndex; i++) {
                    send(p, position, frames[i], 0, 20, 0, 1);
                }
            }
            send(p, position, frames[penultimateIndex+1], 0, 1, fadeOut, 1);
        }

    }

    private static void send(ServerPlayerEntity p, Position pos, char chr, int fIn, int stTime, int fOut, int minStTime) throws InvalidTitleTimings, MinimumStayTimeIsGreaterThatStayTime {
        switch (pos) {
            case Title -> TimedTitle.send(p, Character.toString(chr), "", fIn, stTime, fOut, minStTime);
            case Subtitle -> TimedTitle.send(p, "", Character.toString(chr), fIn, stTime, fOut, minStTime);
            case ActionBar -> {
                if (fIn != 0 || fOut != 0) throw new IllegalArgumentException("Action bars cannot have a fade in or fade out");
                SendActionBar.send(p, Text.of(Character.toString(chr)));
            }
            default -> throw new IllegalArgumentException("No position defined");
        }
    }

    private static String normalizeAnimation(String animation) {
        if (!animation.endsWith(".json")) animation += ".json";
        return animation;
    }
}
