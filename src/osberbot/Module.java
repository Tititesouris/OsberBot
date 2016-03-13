package osberbot;

import osberbot.data.TwitchData;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public abstract class Module {

    public abstract void input(Channel channel, TwitchData data);

    protected void output(Channel channel, String message) {
        channel.send(message);
    }

}
