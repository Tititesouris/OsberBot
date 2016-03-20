package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.data.PRIVMSGData;
import osberbot.data.TwitchData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class NewsModule extends Module {

    private static final Pattern PATTERN = Pattern.compile("^~test~news (list|add|rem|help)(?: (\\S+)(?: (.+))?)?$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void input(Channel channel, TwitchData data) {
        if (data instanceof PRIVMSGData) {
            PRIVMSGData privmsgData = (PRIVMSGData) data;
            Matcher matcher = PATTERN.matcher(privmsgData.getMessage());
            if (matcher.find()) {
                String action = matcher.group(1);
                String content = matcher.group(2);
                String id = matcher.group(2);// ?
                switch (action) {
                    case "list":
                        output(channel, list());
                        break;
                    case "add":
                        output(channel, add(content));
                        break;
                    case "rem":
                        output(channel, remove(id));
                        break;
                    case "help":
                        output(channel, help());
                        break;
                    default:
                        output(channel, "Something went wrong. Error code: ERR_PARSING_CMD_UNKNOWN_ACTION");
                }
            }
        }
    }

    private String add(String content) {
        if (content == null)
            return "Missing news content.";
        int id = 5;
        return "Added news #" + id + " with content '" + content + "'.";
    }

    private String remove(String name) {
        if (name == null)
            return "Missing news id.";
        int id = 5;
        return "Removed news #" + id + " with content '" + name + "'.";
    }

    private String list() {
        return "a, b, c";
    }

    private String help() {
        return "Syntax: !news (list|add <content>|rem <id>|help)";
    }

}
