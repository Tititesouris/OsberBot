package osberbot;

import osberbot.modules.Moderation;
import osberbot.modules.Uptime;
import osberbot.twitch.TwitchClient;

import java.io.*;
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

        for (String channelName : new String[]{"osberbot", "tititesouris", "starrlett20", "honneyplay"}) {
            Channel channel = twitch.join(channelName);
            channel.addModule(new Uptime()).addModule(new Moderation());
        }

        twitch.run();

    }
}
