package osberbot.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Tititesouris on 27/09/2015.
 */
public class Connection {

    private java.sql.Connection connection;

    private PreparedStatement statement;

    public Connection() {

        try {

            Properties properties = new Properties();
            FileInputStream in = new FileInputStream("config/database.properties");
            properties.load(in);
            in.close();

            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String database = properties.getProperty("database");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, user, password);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet execute(String query, Object[] parameters) {
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            return statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {

        try {
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
