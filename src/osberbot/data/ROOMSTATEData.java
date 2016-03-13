package osberbot.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public class ROOMSTATEData extends TwitchData {

    private static final Pattern PATTERN = Pattern.compile("@broadcaster-lang=(\\w*);r9k=(\\d);slow=(\\d);subs-only=(\\d) :tmi\\.twitch\\.tv ROOMSTATE #(\\w+)");

    private String language;

    private boolean r9k;

    private boolean slow;

    private boolean subsOnly;

    public ROOMSTATEData(String raw) {
        super(raw);
        Matcher matcher = PATTERN.matcher(raw);
        if (matcher.find()) {
            this.language = matcher.group(1);
            this.r9k = matcher.group(2).equals("1");
            this.slow = matcher.group(3).equals("1");
            this.subsOnly = matcher.group(4).equals("1");
            this.channel = matcher.group(5);
        }
    }

    public static boolean matches(String raw) {
        Matcher matcher = PATTERN.matcher(raw);
        return matcher.find();
    }

    public String getLanguage() {
        return language;
    }

    public boolean isR9k() {
        return r9k;
    }

    public boolean isSlow() {
        return slow;
    }

    public boolean isSubsOnly() {
        return subsOnly;
    }

    @Override
    public String toString() {
        return "ROOMSTATEData{" +
                "language='" + language + '\'' +
                ", r9k=" + r9k +
                ", slow=" + slow +
                ", subsOnly=" + subsOnly +
                '}';
    }

}
