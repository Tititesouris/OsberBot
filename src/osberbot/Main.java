package osberbot;

import osberbot.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tititesouris on 27/09/2015.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Connection connection = new Connection();

        ResultSet results = connection.execute("SELECT * FROM users WHERE id > ? AND id < ?", new Object[]{1, 4});
        try {
            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
    }

}
