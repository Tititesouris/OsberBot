package osberbot.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class HTTP {

    public static String POST(String address, String params) {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(params);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line);
            }
            writer.close();
            reader.close();
            return lines.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String GET(String address) {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line);
            }
            reader.close();
            return lines.toString();
        }
        catch (IOException e) {
            return null;
        }
    }

}
