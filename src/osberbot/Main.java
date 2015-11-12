package osberbot;

import osberbot.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Tititesouris on 27/09/2015.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Connection connection = new Connection();

        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null) {
            connection.execute(line);
            ResultSet results = connection.execute("SELECT * FROM users;");
            try {
                while (results.next()) {
                    System.out.println(results.getString(1) + " " + results.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
        connection.close();
    }

}
