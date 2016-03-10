package osberbot;

import osberbot.twitch.TwitchClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public class Main {

    public static void main(String[] args) {
        String name = null;
        String password = null;
        try {
            Properties properties = new Properties();
            FileInputStream in;
            in = new FileInputStream("config/twitch.properties");
            properties.load(in);
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TwitchClient twitch = new TwitchClient(name, password);
        twitch.connect();
        Channel tititesouris = twitch.join("tititesouris");
        new Thread(tititesouris).start();
        tititesouris.addObserver(new ChannelObserver() {
            @Override
            public void receive(String message) {
                System.out.println(message);
                if (message.equals("@color=#2E8B57;display-name=Tititesouris;emotes=;mod=0;room-id=24372791;subscriber=0;turbo=0;user-id=24372791;user-type= :tititesouris!tititesouris@tititesouris.tmi.twitch.tv PRIVMSG #tititesouris :!beep")) {
                    tititesouris.send("Boop");
                }
            }
        });
    }

}
