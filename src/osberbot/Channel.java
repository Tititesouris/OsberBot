package osberbot;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public class Channel {

    private Client client;

    private String name;

    public Channel(Client client, String name) {
        this.client = client;
        this.name = name;
    }

    public void send(String message) {
        client.write(message);
        client.flush();
    }

}
