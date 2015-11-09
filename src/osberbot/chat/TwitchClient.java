package osberbot.chat;

import java.io.*;
import java.net.Socket;

/**
 * Created by Quentin Brault on 09/11/2015.
 */
public class TwitchClient extends Client {

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

    @Override
    public boolean init() {
        try {
            socket = new Socket(server.getAddress(), server.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean connect() {
        try {
            writer.write("PASS " + password + "\n");
            writer.write("NICK " + name + "\n");
            writer.flush();
            return reader.readLine().contains(":tmi.twitch.tv 001");
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }



    @Override
    public boolean pong() {
        try {
            writer.write("PONG tmi.twitch.tv\n");
            writer.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void getMessage(String message) {
        System.out.println("Message: " + message);
    }

    @Override
    public boolean sendMessage(String channel, String message) {
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
            writer.write("JOIN #" + channel + '\n');
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean leaveChannel(String channel) {
        return false;
    }

    private static Server getServer() {
        return new Server("192.16.64.175", 6667);
    }

}
