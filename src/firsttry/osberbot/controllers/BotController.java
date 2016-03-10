package firsttry.osberbot.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import firsttry.osberbot.models.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/01/22
 */
public class BotController {

    Properties properties;

    Connection connection;

    PreparedStatement statement;

    public BotController() throws Exception {
        if (!initProperties())
            throw new Exception("Bot properties configuration failed.");
        if (!initDatabase())
            throw new Exception("Bot database configuration failed.");
        if (!initModels())
            throw new Exception("Bot models configuration failed.");
    }

    private boolean initProperties() {
        try {
            properties = new Properties();
            FileInputStream in = new FileInputStream("config/database.properties");
            properties.load(in);
            in.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean initDatabase() {
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String database = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, user, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean initModels() {
        try {
            statement = connection.prepareStatement("SELECT id, name, created_at, updated_at FROM users;");
            ResultSet usersDB = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (usersDB.next()) {
                User user = new User(usersDB.getInt("id"), usersDB.getString("name"), usersDB.getTimestamp("created_at"), usersDB.getTimestamp("updated_at"));
                System.out.println("Adding user " + user);
                users.add(user);

                statement = connection.prepareStatement("SELECT id, name, type, active, created_at, updated_at FROM channels WHERE user_id = ?;");
                statement.setInt(1, user.getId());
                ResultSet channelsDB = statement.executeQuery();
                while (channelsDB.next()) {
                    Channel channel = new Channel(channelsDB.getInt("id"), user, channelsDB.getString("name"), ChannelType.valueOf(channelsDB.getString("type")), channelsDB.getBoolean("active"), channelsDB.getTimestamp("created_at"), channelsDB.getTimestamp("updated_at"));
                    System.out.println("Adding channel " + channel);
                    user.addChannel(channel);

                    statement = connection.prepareStatement("SELECT id, content, popularity, created_at, updated_at FROM quotes WHERE channel_id = ?;");
                    statement.setInt(1, user.getId());
                    ResultSet quotesDB = statement.executeQuery();
                    while (quotesDB.next()) {
                        Quote quote = new Quote(quotesDB.getInt("id"), channel, quotesDB.getString("content"), quotesDB.getInt("popularity"), quotesDB.getTimestamp("created_at"), quotesDB.getTimestamp("updated_at"));
                        System.out.println("Adding quote " + quote);
                        channel.addQuote(quote);
                    }

                    statement = connection.prepareStatement("SELECT id, name, all_powers, powers, created_at, updated_at FROM quotes WHERE channel_id = ?;");
                    statement.setInt(1, user.getId());
                    ResultSet ranksDB = statement.executeQuery();
                    while (ranksDB.next()) {
                        JsonParser parser = new JsonParser();
                        JsonObject powers = (JsonObject) parser.parse(ranksDB.getString("powers"));
                        /*Rank rank = new Rank(ranksDB.getInt("id"), channel, ranksDB.getString("name"), ranksDB.getBoolean("all_powers"), powers, ranksDB.getTimestamp("created_at"), ranksDB.getTimestamp("updated_at"));
                        System.out.println("Adding rank " + rank);
                        channel.addRank(rank);*/
                    }
                }
            }
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
