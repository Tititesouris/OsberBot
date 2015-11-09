package osberbot.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import osberbot.tools.HTTP;
import osberbot.tools.JSON;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class HitboxClient extends Client implements Runnable {

    /**
     * Encapsulating an external websocket client.
     */
    private WebSocketClient webSocketClient;

    /**
     * This flag is true when a connection with the server has been established.
     */
    private boolean connected;

    /**
     * Number of milliseconds to wait for the client to establish a connection with the server before timing out.
     */
    private static final int TIMEOUT = 3000;

    public HitboxClient(String name, String password) {
        this(name, password, false);
    }

    public HitboxClient(String name, String password, boolean debug) {
        super(getServer(), name, password, debug);
    }

    @Override
    public boolean init() {
        if (debug)
            System.out.println("Initializing...");
        if (server != null) {
            if (debug)
                System.out.print("Getting token...");
            password = getToken(name, password);
            if (password != null) {
                if (debug)
                    System.out.print("\tSuccess!\nGetting connection ID...");
                String connectionId = getConnectionId(server);
                if (connectionId != null) {
                    if (debug)
                        System.out.print("\tSuccess!\nCreating websocket client...");
                    try {
                        webSocketClient = new WebSocketClient(new URI("ws://" + server.getAddress() + "/socket.io/1/websocket/" + connectionId), new Draft_10()) {
                            @Override
                            public void onOpen(ServerHandshake serverHandshake) {
                                if (debug)
                                    System.out.println("Connection opened:" + serverHandshake.getHttpStatusMessage());
                                connected = true;
                            }

                            @Override
                            public void onMessage(String s) {
                                getMessage(s);
                            }

                            @Override
                            public void onClose(int i, String s, boolean b) {
                                if (debug)
                                    System.out.println("Connection closed. Code: " + i + ", reason: " + s + ", remote: " + b);
                                connected = false;
                            }

                            @Override
                            public void onError(Exception e) {
                                if (debug) {
                                    System.out.println("---------- WEBSOCKET ERROR ----------:");
                                    e.printStackTrace(System.out);
                                }
                            }
                        };
                        System.out.println("\tSuccess!");
                        return true;
                    } catch (URISyntaxException e) {
                        if (debug) {
                            System.out.println("\tFailure:");
                            e.printStackTrace(System.out);
                        }
                        return false;
                    }
                }
                if (debug) {
                    System.out.println("\tFailure: Null connection ID.");
                }
            }
            if (debug) {
                System.out.println("\tFailure: Null token.");
            }
        }
        return false;
    }

    @Override
    public boolean connect() {
        if (debug)
            System.out.print("Opening connection to " + server + "...");
        if (webSocketClient != null) {
            try {
                webSocketClient.connectBlocking();
                if (debug)
                    System.out.println("\tSuccess!");
                return true;
            } catch (InterruptedException e) {
                if (debug) {
                    System.out.println("\tFailure:");
                    e.printStackTrace(System.out);
                }
                return false;
            }
        }
        if (debug)
            System.out.println("\tFailure: Websocket client not initialized.");
        return false;
    }

    @Override
    public boolean disconnect() {
        try {
            if (debug)
                System.out.print("Closing connection...");
            webSocketClient.closeBlocking();
            long start = System.currentTimeMillis();
            while (!connected && System.currentTimeMillis() - start < TIMEOUT);
            if (connected) {
                if (debug)
                    System.out.println("\tSuccess!");
                return true;
            }
            if (debug)
                System.out.println("\tFailure: Connection timed out. (" + TIMEOUT + "ms)");
            return false;
        } catch (InterruptedException e) {
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    public void run() {
        if (debug)
            System.out.println("Listening...");
        try {
        }
        catch (Exception e) {
            System.out.println("---------- WARNING ----------");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public boolean pong() {
        if (debug)
            System.out.print("Sending PONG...");
        webSocketClient.send("2::");
        if (debug)
            System.out.println("\tSuccess!");
        return true;
    }

    @Override
    protected void getMessage(String message) {
        if (debug)
            System.out.println("<< " + message);
        if (message.equals("2::"))
            ping();
    }

    @Override
    public boolean sendMessage(String channel, String message) {
        if (debug)
            System.out.println(">> [" + channel + "] " + message);
        webSocketClient.send("5:::{\"name\":\"message\",\"args\":[{\"method\":\"chatMsg\",\"params\":{\"channel\":\"" + channel + "\",\"name\":\"" + name + "\",\"nameColor\":\"FA5858\",\"text\":\"" + message + "\"}}]}");
        return true;
    }

    @Override
    protected void getWhisper(String name, String message) {

    }

    @Override
    public boolean sendWhisper(String name, String message) {
        return false;
    }

    @Override
    public boolean joinChannel(String channel) {
        if (debug)
            System.out.println("Joining channel " + channel + ".");
        webSocketClient.send("5:::{\"name\":\"message\",\"args\":[{\"method\":\"leaveChannel\",\"params\":{\"channel\":\"" + channel + "\",\"name\":\"" + name + "\",\"token\":\"" + password + "\",\"isAdmin\":false}}]}");
        return true;
    }

    @Override
    public boolean leaveChannel(String channel) {
        return false;
    }

    private static Server getServer() {
        String get = HTTP.GET("http://api.hitbox.tv/chat/servers");
        if (get != null) {
            JsonArray servers = JSON.getArray(get);
            if (servers != null && servers.size() > 0) {
                return new Server(servers.get(0).getAsJsonObject().get("server_ip").getAsString(), 443);
            }
        }
        return null;
    }

    private String getToken(String name, String password) {
        String post = HTTP.POST("http://api.hitbox.tv/auth/token", "login=" + name + "&pass=" + password);
        if (post != null) {
            JsonObject json = JSON.getObject(post);
            if (json != null && json.has("authToken")) {
                return json.getAsJsonObject().get("authToken").getAsString();
            }
        }
        return null;
    }

    private String getConnectionId(Server server) {
        String get = HTTP.GET("http://" + server.getAddress() + "/socket.io/1/");
        if (get != null) {
            return get.split(":")[0];
        }
        return null;
    }

}
