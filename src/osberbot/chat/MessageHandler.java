package osberbot.chat;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class MessageHandler {

    public static boolean handle(String message) {
        String[] messageParts = message.split(" ", 4);
        if (messageParts.length == 4) {
            if (messageParts[1].equals("PRIVMSG")) {
                String channel = messageParts[2].substring(1);
                String content = messageParts[3].substring(1);
                return true;
            }
        }
        return false;
    }

}
