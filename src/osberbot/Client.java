package osberbot;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public abstract class Client {

    private String ip;

    private int port;

    protected Socket socket;

    private BufferedReader reader;

    private PrintWriter writer;

    protected String login;

    protected String password;

    private List<Channel> channels;

    public Client(String ip, int port, String login, String password) {
        this.ip = ip;
        this.port = port;
        this.login = login;
        this.password = password;
        this.channels = new ArrayList<>();

        try {
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    protected void write(String message) {
        writer.println(message);
    }

    protected void flush() {
        writer.flush();
    }

    public abstract boolean connect();

    public abstract boolean disconnect();

    public abstract Channel join(String channel);

    public abstract boolean leave(Channel channel);

}
