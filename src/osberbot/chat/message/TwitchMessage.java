package osberbot.chat.message;

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
     * @param text    The actual content of the message
     */
    public TwitchMessage(String channel, String text) {
        super(channel, text);
    }

}
