package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.bo.CommandBO;
import osberbot.dao.CommandsDAO;
import osberbot.data.PRIVMSGData;
import osberbot.data.TwitchData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/19
 */
public class CommandsModule extends Module {

    private CommandsDAO dao = new CommandsDAO();

    private static final Pattern PATTERN = Pattern.compile("^~test~cmd (list|add|rem|help)(?: (\\S+)(?: (.+))?)?$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void input(Channel channel, TwitchData data) {
        if (data instanceof PRIVMSGData) {
            PRIVMSGData privmsgData = (PRIVMSGData) data;
            Matcher matcher = PATTERN.matcher(privmsgData.getMessage());
            if (matcher.find()) {
                String action = matcher.group(1);
                String name = matcher.group(2);
                String content = matcher.group(3);
                switch (action) {
                    case "list":
                        output(channel, list());
                        break;
                    case "add":
                        output(channel, add(name, content));
                        break;
                    case "rem":
                        output(channel, remove(name));
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

    private String add(String name, String content) {
        if (name == null)
            return "Missing command name.";
        if (content == null)
            return "Missing command content.";
        dao.add(new CommandBO());
        return "Added command '" + name + "' with content '" + content + "'.";
    }

    private String remove(String name) {
        if (name == null)
            return "Missing command name.";
        return "Removed command '" + name + "'.";
    }

    private String list() {
        return "a, b, c";
    }

    private String help() {
        return "Syntax: !cmd (list|add <name> <content>|rem <name>|help)";
    }

}
