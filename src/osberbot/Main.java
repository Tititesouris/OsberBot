package osberbot;

import osberbot.twitch.TwitchClient;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public class Main {

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            FileInputStream in;
            in = new FileInputStream("config/twitch.properties");
            properties.load(in);
            String name = properties.getProperty("name");
            String password = properties.getProperty("password");

            TwitchClient twitch = new TwitchClient(name, password);
            twitch.connect();

            Connection db = new DatabaseConnection().getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM channels WHERE type = 'TWITCH';");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            for (String channelName : new String[]{"osberbot", "tititesouris", "starrlett20", "honneyplay"}) {
                Channel channel = twitch.join(channelName);
            }

            twitch.run();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
