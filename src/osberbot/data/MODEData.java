package osberbot.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/12
 */
public class MODEData extends TwitchData {

    private static final Pattern PATTERN = Pattern.compile(":jtv MODE #(\\w+) (.{2}) (\\w+)");

    private String mode;

    private String name;

    public MODEData(String raw) {
        super(raw);
        Matcher matcher = PATTERN.matcher(raw);
        if (matcher.find()) {
            this.channel = matcher.group(1);
            this.mode = matcher.group(2);
            this.name = matcher.group(3);
        }
    }

    public static boolean matches(String raw) {
        Matcher matcher = PATTERN.matcher(raw);
        return matcher.find();
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

}
