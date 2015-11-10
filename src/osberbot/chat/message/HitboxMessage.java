package osberbot.chat.message;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class HitboxMessage extends Message {

    /**
     * Color of the name of the author.
     */
    private String color;

    /**
     * Creates a new message that can be transferred between a Hitbox client and server.
     *
     * @param channel   Channel the message belongs to
     * @param name      Name of the author of the message
     * @param text      The actual content of the message
     * @param color     Color of the name of the author
     */
    public HitboxMessage(String channel, String name, String text, String color) {
        super(channel, name, text);
        this.color = color;
    }

    /**
     * Returns the color of the name of the author of the message.
     *
     * @return  Color of the name of the author of the message
     */
    public String getColor() {
        return color;
    }

}
