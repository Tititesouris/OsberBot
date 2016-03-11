package firsttry.osberbot.sql;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides a framework to facilitate operations with the database.
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
            connection.setAutoCommit(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean prepare(String query) {
        try {
            statement = connection.prepareStatement(query);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean prepare(String query, Object[] parameters) {
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet executeQuery() {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    public int executeUpdate() {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}