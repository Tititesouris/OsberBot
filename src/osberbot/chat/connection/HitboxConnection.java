package osberbot.chat.connection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import osberbot.chat.connection.WebsocketConnection;
import osberbot.tools.HTTP;
import osberbot.tools.JSON;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class HitboxConnection extends WebsocketConnection {

    public HitboxConnection(String name, String password) {
        super(name, password);
    }

    @Override
    protected boolean init() {
        return connect();
    }

    private String getServer() {
        String get = HTTP.GET("http://api.hitbox.tv/chat/servers");
        if (get != null) {
            JsonArray servers = JSON.getArray(get);
            if (servers != null && servers.size() > 0) {
                return servers.get(0).getAsJsonObject().get("server_ip").getAsString();
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

    private String getConnectionId(String server) {
        String get = HTTP.GET("http://" + server + "/socket.io/1/");
        if (get != null) {
            return get.split(":")[0];
        }
        return null;
    }

    @Override
    protected boolean connect() {
        String server = getServer();
        if (server != null) {
            password = getToken(name, password);
            if (password != null) {
                String connectionId = getConnectionId(server);
                if (connectionId != null) {
                    url = "ws://" + server + "/socket.io/1/websocket/" + connectionId;
                    return true;
                }
            }
        }
        return false;
    }

}
