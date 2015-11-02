package osberbot.tools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class JSON {

    public static JsonElement getJsonPost(String address, String params) {
        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(params);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(reader.readLine());
            writer.close();
            reader.close();
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}
