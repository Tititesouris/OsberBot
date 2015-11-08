package osberbot.chat.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import osberbot.chat.NotConnectedException;
import osberbot.chat.connection.WebsocketConnection;

/**
 * Created by Quentin Brault on 04/11/2015.
 */
public abstract class WebsocketClient extends WebSocketClient implements Client {

    protected WebsocketConnection connection;

    public WebsocketClient(WebsocketConnection connection) throws NotConnectedException {
        super(connection.getUrl(), new Draft_10());
        this.connection = connection;
    }

    @Override
    public void open() {
        try {
            connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("hand: " + handshake.getHttpStatus());
    }

    public void onMessage(String message) {
        getMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebsocketClient closed. Code: " + code + ", Reason: " + reason + ", Remote: " + remote);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

}
