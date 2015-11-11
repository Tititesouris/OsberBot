package osberbot.chat.message.handler;

import osberbot.chat.message.Message;
import osberbot.chat.message.TwitchMessage;
import osberbot.chat.user.TwitchUser;
import osberbot.chat.user.User;

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
                String[] messageInfos = messageParts[0].split(";", -1);
                String color = messageInfos[0].split("#", -1)[1];
                color = (!color.equals("")) ? color : null;
                String name = messageInfos[1].split("=", -1)[1];
                String emotes = messageInfos[2].split("=", -1)[1];
                emotes = (!emotes.equals("")) ? emotes : null;
                boolean subscriber = messageInfos[3].split("=", -1)[1].equals("1");
                boolean turbo = messageInfos[4].split("=", -1)[1].equals("1");
                int userId = Integer.valueOf(messageInfos[5].split("=", -1)[1]);
                String userType = messageInfos[6].split("=", -1)[1];
                userType = (!userType.equals("")) ? userType : null;
                String channel = messageParts[3].substring(1);
                String text = messageParts[4].substring(1);
                User user = new TwitchUser(channel, name, color, userId, userType, subscriber, turbo, emotes);
                Message msg = new TwitchMessage(channel, user, text);
                System.out.println(msg);
                return handleMessage(msg);
            }
        }
        return null;
    }

    @Override
    public TwitchMessage handleMessage(Message message) {
        String text = message.getText();
        String textLower = text.toLowerCase();
        if (textLower.startsWith("!say ")) {
            String params = text.split(" ", 2)[1];
            if (params.startsWith("#"))
                return new TwitchMessage(params.split(" ", 1)[0].substring(1), message.getUser(), params.split(" ", 2)[1]);
            return new TwitchMessage(message.getChannel(), message.getUser(), params);
        }
        return null;
    }

    @Override
    public TwitchMessage handleCommand(String channel, String name, String command) {
        return null;
    }

}
