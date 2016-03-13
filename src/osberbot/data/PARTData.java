package osberbot.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class PARTData extends TwitchData {

    private static final Pattern PATTERN = Pattern.compile(":(\\w+)!\\w+@\\w+\\.tmi\\.twitch\\.tv PART #(\\w+)");

    private String name;

    public PARTData(String raw) {
        super(raw);
        Matcher matcher = PATTERN.matcher(raw);
        if (matcher.find()) {
            this.name = matcher.group(1);
            this.channel = matcher.group(2);
        }
    }

    public static boolean matches(String raw) {
        Matcher matcher = PATTERN.matcher(raw);
        return matcher.find();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PARTData{" +
                "name='" + name + '\'' +
                '}';
    }

}
