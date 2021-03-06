package osberbot.twitch;

import osberbot.Channel;
import osberbot.data.TwitchData;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public class TwitchChannel extends Channel {

    public TwitchChannel(TwitchClient client, String name) {
        super(client, name);
    }

    @Override
    public void receive(TwitchData data) {
        notifyModules(data);
    }

    @Override
    public void send(String message) {
        client.write("PRIVMSG #" + name + " :" + message);
        client.flush();
    }

}
