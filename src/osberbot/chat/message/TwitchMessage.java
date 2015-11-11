package osberbot.chat.message;

import osberbot.chat.user.User;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class TwitchMessage extends Message {

    public TwitchMessage(String channel, User user, String text) {
        super(channel, user, text);
    }

    public TwitchMessage(String channel, String text) {
        super(channel, text);
    }

}
