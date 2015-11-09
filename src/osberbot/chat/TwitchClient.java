package osberbot.chat;

import java.io.*;
import java.net.Socket;

/**
 * Created by Quentin Brault on 09/11/2015.
 */
public class TwitchClient extends Client implements Runnable {

    private Socket socket;

    private BufferedReader reader;

    private BufferedWriter writer;

    /**
     * Creates a new Twitch client.
     *
     * @param name      Name to connect to the server with
     * @param password  Password to connect to the server with
     */
    public TwitchClient(String name, String password) {
        super(getServer(), name, password);
    }

    /**
     * Creates a new Twitch client.
     *
     * @param name      Name to connect to the server with
     * @param password  Password to connect to the server with
     * @param debug     Whether or not the client is in debug mode.
     */
    public TwitchClient(String name, String password, boolean debug) {
        super(getServer(), name, password, debug);
    }

    @Override
    public boolean init() {
        try {
            if (debug)
                System.out.print("Creating socket connection to " + server.getAddress() + ":" + server.getPort() + "...");
            socket = new Socket(server.getAddress(), server.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean connect() {
        try {
            if (debug)
                System.out.print("Connecting to the server.");
            writer.write("PASS " + password + "\n");
            if (debug)
                System.out.print('.');
            writer.write("NICK " + name + "\n");
            if (debug)
                System.out.print('.');
            writer.flush();
            String line = reader.readLine();
            if (line.contains(":tmi.twitch.tv 001")) {
                if (debug)
                    System.out.println("\tSuccess!");
                return true;
            }
            if (debug)
                System.out.println("\tFailure: " + line);
            return false;
        }
        catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            if (debug)
                System.out.print("Closing connection...");
            socket.close();
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    public void run() {
        if (debug)
            System.out.println("Listening...");
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                getMessage(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean pong() {
        try {
            if (debug)
                System.out.print("Sending PONG...");
            writer.write("PONG tmi.twitch.tv\n");
            writer.flush();
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    @Override
    protected void getMessage(String message) {
        if (debug)
            System.out.println("<< " + message);
        if (message.contains("PING")) {
            ping();
        }
    }

    @Override
    public boolean sendMessage(String channel, String message) {
        if (debug)
            System.out.println(">> [" + channel + "] " + message);
        try {
            writer.write("PRIVMSG #" + channel + " :" + message + '\n');
            writer.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void getWhisper(String name, String message) {

    }

    @Override
    public boolean sendWhisper(String name, String message) {
        return false;
    }

    @Override
    public boolean joinChannel(String channel) {
        try {
            if (debug)
                System.out.print("Joining channel " + channel + "...");
            writer.write("JOIN #" + channel + '\n');
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean leaveChannel(String channel) {
        try {
            if (debug)
                System.out.print("Leaving channel " + channel + "...");
            writer.write("PART #" + channel + '\n');
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug)
                System.out.println("\tFailure: " + e.getMessage());
            return false;
        }
    }

    private static Server getServer() {
        return new Server("192.16.64.175", 6667);
    }

}
