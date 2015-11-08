package osberbot.chat.connection;

/**
 * Created by Tititesouris on 08/11/2015.
 */
public abstract class Connection {

    protected String name;

    protected String password;

    public Connection(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
