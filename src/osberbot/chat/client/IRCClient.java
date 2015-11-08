package osberbot.chat.client;

import osberbot.chat.connection.IRCConnection;

import java.io.*;
import java.net.Socket;

/**
 * Created by Tititesouris on 08/11/2015.
 */
public abstract class IRCClient implements Runnable, Client {

    protected IRCConnection connection;

    private String server;

    private int port;

    private Socket socket;

    private BufferedReader reader;

    private BufferedWriter writer;

    public IRCClient(IRCConnection connection) {
        this.connection = connection;
        this.server = connection.getServer();
        this.port = connection.getPort();
    }

    @Override
    public void run() {
        open();
    }

    @Override
    public void open() {
        try {
            socket = new Socket(server, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            connect();
            String line;
            while ((line = reader.readLine()) != null) {
                getMessage(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean send(String message) {
        try {
            writer.write(message);
            writer.flush();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    protected boolean connect() {
        boolean ok = send("PASS " + connection.getPassword());
        return ok && send("NICK " + connection.getName());
    }

    public void close() {
        try {
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
