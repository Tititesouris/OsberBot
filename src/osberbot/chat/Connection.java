package osberbot.chat;

import com.google.gson.JsonObject;
import org.java_websocket.WebSocketImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class Connection {

    public static void main(String[] args) {
        try {
            Properties hitboxProperties = new Properties();
            hitboxProperties.load(new FileInputStream("config/hitbox.properties"));
            Hitbox hitbox = new Hitbox();
            if (hitbox.connect(hitboxProperties.getProperty("login"), hitboxProperties.getProperty("pass"))) {
                Client client = new Client(hitbox);
                try {
                    client.connectBlocking();
                    //client.joinChannel("tititesouris");
                    Scanner s = new Scanner(System.in);
                    s.next();
                    s.close();
                    client.close();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*
                JsonObject obj = new JsonObject();
                obj.addProperty("event", "addChannel");
                obj.addProperty("channel", "tititesouris");
                client.send(obj.toString());
                */
            }
            /*JsonObject obj = new JsonObject();
            obj.addProperty("event", "addChannel");
            obj.addProperty("channel", "tititesouris");
            hitbox.send(obj.toString());
            Scanner s = new Scanner(System.in);
            s.next();
            s.close();
            hitbox.close();*/
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
