package firsttry.osberbot.chat.message;

import firsttry.osberbot.chat.user.User;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class HitboxMessage extends Message {

    public HitboxMessage(String channel, User user, String text) {
        super(channel, user, text);
    }

    public HitboxMessage(String channel, String text) {
        super(channel, text);
    }

}
