package osberbot.chat.connection;


/**
 * Created by Tititesouris on 08/11/2015.
 */
public abstract class IRCConnection extends Connection {

    protected String server;

    protected int port;

    public IRCConnection(String name, String password, String server, int port) {
        super(name, password);
        this.server = server;
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
