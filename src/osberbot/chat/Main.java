package osberbot.chat;

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
            /*
            TwitchClient twitch = new TwitchClient(twitchProperties.getProperty("name"), twitchProperties.getProperty("password"), true);
            if (twitch.init() && twitch.connect()) {
                Thread twitchChat = new Thread(twitch);
                twitchChat.start();
                twitch.joinChannel("tititesouris");
                twitch.sendMessage("tititesouris", "Hello World!");
            }*/
            HitboxClient hitbox = new HitboxClient(hitboxProperties.getProperty("name"), hitboxProperties.getProperty("password"), true);
            if (hitbox.init() && hitbox.connect()) {
                Thread hitboxChat = new Thread(hitbox);
                hitboxChat.start();
                hitbox.joinChannel("tititesouris");
                hitbox.sendMessage("tititesouris", "Hello World!");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
