package osberbot.data;

import osberbot.Emote;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public class PRIVMSGData extends TwitchData {

    private static final Pattern PATTERN = Pattern.compile("@color=#([0-9A-F]{6});display-name=(\\w+);emotes=(.*);mod=(\\d);room-id=(\\d+);(?:sent-ts=\\d+;)?subscriber=(\\d);(?:tmi-sent-ts=\\d+;)?turbo=(\\d);user-id=(\\d+);user-type=(\\w*) :\\w+!\\w+@\\w+\\.tmi\\.twitch\\.tv PRIVMSG #(\\w+) :(.*)");
    private static final Pattern EMOTE_PATTERN = Pattern.compile("(\\d+):(\\d+)-(\\d+)");

    private String color;

    private String name;

    private Emote[] emotes;

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
            Matcher emoteMatcher = EMOTE_PATTERN.matcher(matcher.group(3));
            this.emotes = new Emote[emoteMatcher.groupCount()-1];
            int i = 0;
            while (emoteMatcher.find()) {
                this.emotes[i++] = new Emote(Integer.valueOf(emoteMatcher.group(1)), Integer.valueOf(emoteMatcher.group(2)), Integer.valueOf(emoteMatcher.group(3)));
            }
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

    public Emote[] getEmotes() {
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
