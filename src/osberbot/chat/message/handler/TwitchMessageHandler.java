package osberbot.chat.message.handler;

import osberbot.chat.message.TwitchMessage;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class TwitchMessageHandler implements MessageHandler {

    @Override
    public TwitchMessage handle(String message) {
        String[] messageParts = message.split(" ", 5);
        if (messageParts.length == 5) {
            if (messageParts[2].equals("PRIVMSG")) {
                String channel = messageParts[3].substring(1);
                String text = messageParts[4].substring(1);
                return new TwitchMessage(channel, text);
            }
        }
        return null;
    }

    @Override
    public TwitchMessage handleMessage(String channel, String name, String message) {
        return null;
    }

    @Override
    public TwitchMessage handleCommand(String channel, String name, String command) {
        return null;
    }

}
