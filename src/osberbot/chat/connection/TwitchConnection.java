package osberbot.chat.connection;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public class TwitchConnection extends IRCConnection {

    public TwitchConnection(String name, String password) {
        super(name, password, "192.16.64.174", 443);
    }

}
