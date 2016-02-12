package firsttry.osberbot.chat.message;

import firsttry.osberbot.chat.user.User;

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
