package osberbot.chat;

/**
 * This class represents a server to which clients connect to using its address and port.
 *
 * @author Tititesouris
 */
public class Server {

    /**
     * Address to access the server.
     */
    private final String address;

    /**
     * Port to access the server.
     */
    private final int port;

    /**
     * Creates a new server.
     *
     * @param address Address to access the server
     * @param port    Port to access the server
     */
    public Server(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return address + ":" + port;
    }

}
