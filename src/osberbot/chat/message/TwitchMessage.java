package osberbot.chat.message;

import osberbot.chat.message.Message;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class TwitchMessage extends Message {

    /**
     * Creates a new message that can be transferred between a Twitch client and server.
     *
     * @param channel Channel the message belongs to
     * @param name    Name of the author of the message
     * @param text    The actual content of the message
     */
    public TwitchMessage(String channel, String name, String text) {
        super(channel, name, text);
    }

}
