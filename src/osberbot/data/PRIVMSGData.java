package osberbot.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public class PRIVMSGData extends TwitchData {

    private static Pattern PATTERN = Pattern.compile("@color=#([0-9A-F]{6});display-name=(\\w+);emotes=(.*);mod=(\\d);room-id=(\\d+);(?:sent-ts=\\d+;)?subscriber=(\\d);(?:tmi-sent-ts=\\d+;)?turbo=(\\d);user-id=(\\d+);user-type=(\\w*) :\\w+!\\w+@\\w+\\.tmi\\.twitch\\.tv PRIVMSG #(\\w+) :(.*)");

    private String color;

    private String name;

    private String emotes;

    private boolean mod;

    private int room;

    private boolean subscriber;

    private boolean turbo;

    private int user;

    private String userType;

    private String message;

    public PRIVMSGData(String raw) {
        super(raw);
        Matcher matcher = PATTERN.matcher(raw);
        if (matcher.find()) {
            this.color = matcher.group(1);
            this.name = matcher.group(2);
            this.emotes = matcher.group(3);
            this.mod = matcher.group(4).equals("1");
            this.room = Integer.valueOf(matcher.group(5));
            this.subscriber = matcher.group(6).equals("1");
            this.turbo = matcher.group(7).equals("1");
            this.user = Integer.valueOf(matcher.group(8));
            this.userType = matcher.group(9);
            this.channel = matcher.group(10);
            this.message = matcher.group(11);
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

    public int getRoom() {
        return room;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public int getUser() {
        return user;
    }

    public String getUserType() {
        return userType;
    }

    public String getMessage() {
        return message;
    }

}
