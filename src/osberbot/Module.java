package osberbot;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public abstract class Module {

    public abstract void input(Channel channel, String message);

    protected void output(Channel channel, String message) {
        channel.send(message);
    }

}
