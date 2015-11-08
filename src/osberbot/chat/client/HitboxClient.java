package osberbot.chat.client;

import osberbot.chat.connection.HitboxConnection;
import osberbot.chat.NotConnectedException;

/**
 * Created by Tititesouris on 07/11/2015.
 */
public class HitboxClient extends WebsocketClient {

    public HitboxClient(HitboxConnection hitbox) throws NotConnectedException {
        super(hitbox);
    }

    @Override
    public void joinChannel(String channel) {
        this.send("5:::{\"name\":\"message\",\"args\":[{\"method\":\"joinChannel\",\"params\":{\"channel\":\"" + channel + "\",\"name\":\"" + connection.getName() + "\",\"token\":\"" + connection.getPassword() + "\",\"isAdmin\":false}}]}");
    }

    @Override
    public void sendMessage(String channel, String message) {
        this.send("5:::{\"name\":\"message\",\"args\":[{\"method\":\"chatMsg\",\"params\":{\"channel\":\"" + channel + "\",\"name\":\"" + connection.getName() + "\",\"nameColor\":\"FA5858\",\"text\":\"" + message + "\"}}]}");
    }

    @Override
    public void ping() {
        send("2::");
    }

    @Override
    public void getMessage(String message) {
        if (message.equals("!stop")) {
            close();
        }
        else if (message.equals("2::"))
            ping();
    }

}
