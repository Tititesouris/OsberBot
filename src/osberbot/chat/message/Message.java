package osberbot.chat.message;

/**
 * This abstract class represents a message that can be received from or sent to a server.
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
     * Name of the author of the message.
     */
    protected String name;

    /**
     * The actual content of the message.
     */
    public String text;

    /**
     * Creates a new message.
     *
     * @param channel   Channel the message belongs to
     * @param name      Name of the author of the message
     * @param text      The actual content of the message
     */
    public Message(String channel, String name, String text) {
        this.channel = channel;
        this.name = name;
        this.text = text;
    }

    /**
     * Returns the channel the message belongs to.
     *
     * @return  Channel the message belongs to
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Returns the name of the author of the message.
     *
     * @return  Name of the author of the message.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the actual content of the message.
     *
     * @return  Actual content of the message.
     */
    public String getText() {
        return text;
    }

}
