package osberbot.chat.user;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public abstract class User {

    /**
     * Channel the user is in.
     */
    protected final String channel;

    /**
     * Name of the user.
     */
    protected final String name;

    /**
     * Color of the user.
     */
    protected final String color;

    /**
     * Creates a new user.
     *
     * @param channel   Channel the user is in
     * @param name      Name of the user
     * @param color     Color of the user
     */
    public User(String channel, String name, String color) {
        this.channel = channel;
        this.name = name;
        this.color = color;
    }

    /**
     * Returns the channel the user is in.
     *
     * @return  Channel the user is in.
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Returns the name of the user.
     *
     * @return  Name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the color of the user.
     *
     * @return  Color of the user.
     */
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name + "(#" + color + ")";
    }

}
