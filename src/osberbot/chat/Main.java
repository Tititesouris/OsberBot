package osberbot.chat;

import osberbot.chat.client.TwitchClient;
import osberbot.chat.message.TwitchMessage;

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
            twitch.setDebug(true);
            if (twitch.init() && twitch.connect()) {
                Thread twitchChat = new Thread(twitch);
                twitchChat.start();
                twitch.joinChannel("tititesouris");
                twitch.sendMessage(new TwitchMessage("tititesouris", "Hello World!"));
            }
            /*
            HitboxClient hitbox = new HitboxClient(hitboxProperties.getProperty("name"), hitboxProperties.getProperty("password"), true);
            hitbox.setDebug(true);
            if (hitbox.init() && hitbox.connect()) {
                Thread hitboxChat = new Thread(hitbox);
                hitboxChat.start();
                hitbox.joinChannel("tititesouris");
                hitbox.sendMessage(new HitboxMessage("tititesouris", "Hello World!", "FFFFFF"));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
