package osberbot.data;

import osberbot.Emote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private static final Pattern EMOTE_PATTERN = Pattern.compile("(\\d+):(\\d+-\\d+(?:,\\d+-\\d+)*)");

    private static final Pattern EMOTE_POSITION_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

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
            List<Emote> emotes = new ArrayList<>();
            while (emoteMatcher.find()) {
                int id = Integer.valueOf(emoteMatcher.group(1));
                Matcher emotePositionMatcher = EMOTE_POSITION_PATTERN.matcher(emoteMatcher.group(2));
                while (emotePositionMatcher.find()) {
                    emotes.add(new Emote(id, Integer.valueOf(emotePositionMatcher.group(1)), Integer.valueOf(emotePositionMatcher.group(2))));
                }
            }
            this.emotes = emotes.toArray(new Emote[emotes.size()]);
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

    @Override
    public String toString() {
        return "PRIVMSGData{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", emotes=" + Arrays.toString(emotes) +
                ", mod=" + mod +
                ", room=" + room +
                ", subscriber=" + subscriber +
                ", turbo=" + turbo +
                ", user=" + user +
                ", userType='" + userType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
