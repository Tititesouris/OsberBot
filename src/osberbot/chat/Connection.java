package osberbot.chat;

import com.google.gson.JsonObject;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class Connection {

    public static void main(String[] args) {
        try {
            Hitbox hitbox = new Hitbox();
            hitbox.connect();
            JsonObject obj = new JsonObject();
            obj.addProperty("event", "addChannel");
            obj.addProperty("channel", "tititesouris");
            hitbox.send(obj.toString());
            Scanner s = new Scanner(System.in);
            s.next();
            s.close();
            hitbox.close();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
