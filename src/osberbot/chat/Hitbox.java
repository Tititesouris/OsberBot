package osberbot.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.api.scripting.JSObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import osberbot.tools.JSON;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class Hitbox extends WebSocketClient {

    public Hitbox() throws URISyntaxException {
        super(new URI("ws://ec2-54-226-192-118.compute-1.amazonaws.com:80"));

        try {
            Properties properties = new Properties();
            FileInputStream in = new FileInputStream("config/hitbox.properties");
            properties.load(in);
            in.close();

            String api = properties.getProperty("api");
            String login = properties.getProperty("login");
            String pass = properties.getProperty("pass");

            JsonElement json = JSON.getJsonPost(api + "auth/token", "login=" + login + "&pass=" + pass);
            if (json.isJsonObject() && json.getAsJsonObject().has("authToken")) {
                String auth = json.getAsJsonObject().get("authToken").getAsString();
                System.out.println(auth);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("hand: " + handshake);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("mesg: " + message);
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject)parser.parse(message);
        String channel = obj.get("channel").getAsString();
        System.out.println(channel + " ###");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("close: " + code + "  " + reason + "  " + reason);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

}