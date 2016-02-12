package firsttry.osberbot.chat.message;

import firsttry.osberbot.chat.user.User;

/**
 * This abstract class represents a message that is sent from the client to the server and vice versa.
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
     * Author of the message.
     */
    protected User user;

    /**
     * The actual content of the message.
     */
    public String text;

    /**
     * Creates a new message.
     *
     * @param channel   Channel the message belongs to
     * @param user      Author of the message
     * @param text      The actual content of the message
     */
    public Message(String channel, User user, String text) {
        this.channel = channel;
        this.user = user;
        this.text = text;
    }

    /**
     * Creates a new message without specifying the user.
     *
     * @param channel   Channel the message belongs to
     * @param text      The actual content of the message
     */
    public Message(String channel, String text) {
        this.channel = channel;
        this.text = text;
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
     * Returns the author of the message.
     *
     * @return  Author of the message.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the actual content of the message.
     *
     * @return Actual content of the message.
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + channel + "] " + user.toString() + ": " + text;
    }

}
