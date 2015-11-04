package osberbot.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import osberbot.tools.HTTP;
import osberbot.tools.JSON;

import java.io.*;
import java.net.*;
import java.util.Properties;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class Hitbox {

    private String name;

    private String token;

    private String url;

    public boolean connect(String login, String pass) {
        this.name = login;
        String server = getServer();
        if (server != null) {
            token = getToken(login, pass);
            if (token != null) {
                String connectionId = getConnectionId(server);
                if (connectionId != null) {
                    url = "ws://" + server + "/socket.io/1/websocket/" + connectionId;
                    return true;
                }
            }
        }
        return false;
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

    private String getToken(String login, String pass) {
        String post = HTTP.POST("http://api.hitbox.tv/auth/token", "login=" + login + "&pass=" + pass);
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

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getUrl() {
        return url;
    }

}
