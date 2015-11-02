package osberbot.chat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class Twitch extends WebSocketClient {

    public Twitch() throws URISyntaxException {
        super(new URI("ws://192.16.64.212:80"));
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
