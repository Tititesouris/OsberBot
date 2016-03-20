package osberbot.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class DataAccessObject {

    protected static Connection connection;

    public DataAccessObject() {
        setup();
    }

    private static void setup() {
        if (connection == null) {
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
                //connection.setAutoCommit(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
