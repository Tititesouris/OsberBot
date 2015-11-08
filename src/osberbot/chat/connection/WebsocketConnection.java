package osberbot.chat.connection;

import osberbot.chat.NotConnectedException;
import osberbot.chat.connection.Connection;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Tititesouris on 02/11/2015.
 */
public abstract class WebsocketConnection extends Connection {

    protected String url;

    public WebsocketConnection(String name, String password) {
        super(name, password);
    }

    protected abstract boolean init();

    protected abstract boolean connect();

    /**
     * Returns the url to connect to the websocket server.
     * The url is supposed to be set when calling connect().
     *
     * @return  Url to connect to.
     * @throws NotConnectedException    If the url is malformed, it must be that the url was not properly set. This means that connect() was not called or doesn't set the url properly.
     */
    public URI getUrl() throws NotConnectedException {
        try {
            return new URI(url);
        }
        catch (URISyntaxException e) {
            throw new NotConnectedException();
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
