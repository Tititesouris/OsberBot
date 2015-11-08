package osberbot.chat;

import osberbot.chat.client.IRCClient;
import osberbot.chat.client.WebsocketClient;
import osberbot.chat.client.TwitchClient;
import osberbot.chat.connection.IRCConnection;
import osberbot.chat.connection.TwitchConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Tititesouris on 07/11/2015.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Properties twitchProperties = new Properties();
            twitchProperties.load(new FileInputStream("config/twitch.properties"));
            Properties hitboxProperties = new Properties();
            hitboxProperties.load(new FileInputStream("config/hitbox.properties"));
            IRCConnection twitch = new TwitchConnection(twitchProperties.getProperty("login"), twitchProperties.getProperty("pass"));
            IRCClient client = new TwitchClient((TwitchConnection) twitch);
            client.run();
            client.joinChannel("tititesouris");
            client.sendMessage("tititesouris", "Hello World!");
            /*
            WebsocketConnection hitbox = new HitboxConnection(hitboxProperties.getProperty("login"), hitboxProperties.getProperty("pass"));
            if (hitbox.init()) {
                WebsocketClient client = new HitboxClient((HitboxConnection) hitbox);
                client.open();
                client.joinChannel("tititesouris");
                client.sendMessage("tititesouris", "tititesouris", "Hello World!");
            }*/
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
