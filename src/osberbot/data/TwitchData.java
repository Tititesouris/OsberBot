package osberbot.data;

import java.util.regex.Matcher;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public abstract class TwitchData {

    protected String raw;

    protected String channel;

    public TwitchData(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "TwitchData{" +
                "raw='" + raw + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }

}
