package osberbot;

import osberbot.modules.Uptime;
import osberbot.twitch.TwitchChannel;
import osberbot.twitch.TwitchClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public class Main {

    private static List<String> channels = new ArrayList<>();

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

        channels.add("tititesouris");
        channels.add("starrlett20");
        channels.add("honneyplay");
        Uptime uptime = new Uptime();

        for (String channel : channels) {
            new Thread(twitch.join(channel).addModule(uptime)).start();
        }
    }

}
