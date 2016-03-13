package osberbot.data;

import sun.security.krb5.internal.PAData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public class USERSTATEData extends TwitchData {

    private static Pattern PATTERN = Pattern.compile("@color=#([0-9A-F]{6});display-name=(\\w+);emote-sets=(\\d);mod=(\\d);subscriber=(\\d);turbo=(\\d);user-type=(\\w*) :tmi\\.twitch\\.tv USERSTATE #(\\w+)");

    private String color;

    private String name;

    private String emotes;

    private boolean mod;

    private boolean subscriber;

    private boolean turbo;

    private String userType;

    public USERSTATEData(String raw) {
        super(raw);
        Matcher matcher = PATTERN.matcher(raw);
        if (matcher.find()) {
            this.color = matcher.group(1);
            this.name = matcher.group(2);
            this.emotes = matcher.group(3);
            this.mod = matcher.group(4).equals("1");
            this.subscriber = matcher.group(5).equals("1");
            this.turbo = matcher.group(6).equals("1");
            this.userType = matcher.group(7);
            this.channel = matcher.group(8);
        }
    }

    public static boolean matches(String raw) {
        Matcher matcher = PATTERN.matcher(raw);
        return matcher.find();
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getEmotes() {
        return emotes;
    }

    public boolean isMod() {
        return mod;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public String getUserType() {
        return userType;
    }

}
