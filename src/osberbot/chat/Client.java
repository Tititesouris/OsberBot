package osberbot.chat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Quentin Brault on 04/11/2015.
 */
public class Client extends WebSocketClient {

    private Hitbox hitbox;

    public Client(Hitbox hitbox) throws URISyntaxException {
        super(new URI(hitbox.getUrl()), new Draft_10());
        this.hitbox = hitbox;
    }

    public void joinChannel(String channel){
        this.send("5:::{\"name\":\"message\",\"args\":[{\"method\":\"joinChannel\",\"params\":{\"channel\":\"" + channel + "\",\"name\":\"" + hitbox.getName() + "\",\"token\":\"" + hitbox.getToken() + "\",\"isAdmin\":false}}]}");
        System.out.println("Channel Joined.");
    }

    public void ping() {
        send("2::");
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("hand: " + handshake);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("msg: " + message);
        if (message.equals("2::"))
            ping();
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
