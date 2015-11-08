package osberbot.chat.client;

import osberbot.chat.connection.TwitchConnection;

/**
 * Created by Tititesouris on 08/11/2015.
 */
public class TwitchClient extends IRCClient {

    public TwitchClient(TwitchConnection connection) {
        super(connection);
    }

    @Override
    public void joinChannel(String channel) {
        System.out.println("Join " + channel);
        send("JOIN #" + channel);
    }

    @Override
    public void sendMessage(String channel, String message) {
        send("PRIVMSG #" + channel + " :" + message + '\n');
    }

    @Override
    public void ping() {
        send("PONG tmi.twitch.tv\n");
    }

    @Override
    public void getMessage(String message) {
        System.out.println(message);
    }

}
