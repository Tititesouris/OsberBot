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
            TwitchClient twitch = new TwitchClient(twitchProperties.getProperty("name"), twitchProperties.getProperty("password"));
            twitch.init();
            System.out.println(twitch.connect());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
