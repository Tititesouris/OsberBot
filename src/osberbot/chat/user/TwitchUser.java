package osberbot.chat.user;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class TwitchUser extends User {

    /**
     * TODO
     */
    int userId;

    /**
     * TODO
     */
    String userType;

    /**
     * Whether or not the user is subscribed to the channel.
     */
    private boolean subscriber;

    /**
     * Whether or not the user is turbo.
     */
    boolean turbo;

    /**
     * TODO
     */
    private String emotes;

    /**
     * Creates a new Twitch user.   TODO
     *
     * @param channel
     * @param name
     * @param color
     * @param userId
     * @param userType
     * @param subscriber
     * @param turbo
     * @param emotes
     */
    public TwitchUser(String channel, String name, String color, int userId, String userType, boolean subscriber, boolean turbo, String emotes) {
        super(channel, name, color);
        this.userId = userId;
        this.userType = userType;
        this.subscriber = subscriber;
        this.turbo = turbo;
        this.emotes = emotes;
    }

    /**
     * TODO
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * TODO
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Returns whether or not the user is subscribed to the channel.
     *
     * @return  True if the user is subscribed to the channel. False otherwise.
     */
    public boolean isSubscriber() {
        return subscriber;
    }
    /**
     * Returns whether or not the user is turbo.
     *
     * @return  True if the user is turbo. False otherwise.
     */
    public boolean isTurbo() {
        return turbo;
    }

    /**
     * TODO
     * @return
     */
    public String getEmotes() {
        return emotes;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + ((subscriber) ? "sub, " : "") + ((turbo) ? "turbo, " : "") + ((emotes != null) ? emotes + ", " : "") + userId + ", " + userType + "}";
    }

}
