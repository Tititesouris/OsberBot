package osberbot.chat.message;

import java.sql.Timestamp;

/**
 * This abstract class represents a message that is sent from the client to the server.
 * Some fields are public to allow message handlers to alter them easily.
 * For those fields the getters should still be used if they are not going to be modified.
 *
 * @author Tititesouris
 */
public abstract class Message {

    /**
     * Channel the message belongs to.
     */
    protected String channel;

    /**
     * The actual content of the message.
     */
    public String text;

    /**
     * The timestamp the message was created at.
     */
    protected Timestamp time;

    /**
     * Creates a new message.
     *
     * @param channel Channel the message belongs to
     * @param text    The actual content of the message
     */
    public Message(String channel, String text) {
        this.channel = channel;
        this.text = text;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the channel the message belongs to.
     *
     * @return Channel the message belongs to
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Returns the actual content of the message.
     *
     * @return Actual content of the message.
     */
    public String getText() {
        return text;
    }

}
