package osberbot.chat;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class TwitchMessageHandler implements MessageHandler {

    @Override
    public String handle(String message) {
        String[] messageParts = message.split(" ", 4);
        if (messageParts.length == 4) {
            if (messageParts[1].equals("PRIVMSG")) {
                String channel = messageParts[2].substring(1);
                String content = messageParts[3].substring(1);
                return "[" + channel + "] " + content;
            }
        }
        return null;
    }

    @Override
    public String handleMessage(String channel, String name, String message) {
        return null;
    }

    @Override
    public String handleCommand(String channel, String name, String command) {
        return null;
    }

}
