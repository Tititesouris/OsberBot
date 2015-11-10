package osberbot.chat.client;

import osberbot.chat.Server;
import osberbot.chat.message.Message;
import osberbot.chat.message.MessageQueue;
import osberbot.chat.message.TwitchMessage;
import osberbot.chat.message.handler.TwitchMessageHandler;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a client connecting to a Twitch chat server using the IRC protocol.
 *
 * @author Tititesouris
 * @see Client
 */
public class TwitchClient extends Client implements Runnable {

    /**
     * Socket connection to the server.
     */
    private Socket socket;

    /**
     * Reader allowing the client to read messages sent by the server.
     */
    private BufferedReader reader;

    /**
     * Writer allowing the client to write messages to the server.
     */
    private BufferedWriter writer;

    /**
     * Queue of messages that have yet to be sent.
     */
    private final MessageQueue messageQueue;

    /**
     * Creates a new Twitch client.
     *
     * @param name     Name to connect to the server with
     * @param password Password to connect to the server with
     */
    public TwitchClient(String name, String password) {
        super(getServer(), name, password, new TwitchMessageHandler());
        this.messageQueue = new MessageQueue(2, 30000);
    }

    @Override
    public boolean init() {
        try {
            if (debug)
                System.out.print("Creating socket connection to " + server + "...");
            socket = new Socket(server.getAddress(), server.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (debug)
                System.out.println("\tSuccess!");
            return true;
        } catch (IOException e) {
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    public boolean connect() {
        try {
            if (debug)
                System.out.print("Connecting to the server.");
            writer.write("PASS " + password + "\n");
            writer.write("NICK " + name + "\n");
            if (debug)
                System.out.print('.');
            writer.write("CAP REQ :twitch.tv/membership\n");
            writer.write("CAP REQ :twitch.tv/commands\n");
            writer.write("CAP REQ :twitch.tv/tags\n");
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
        } catch (IOException e) {
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
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
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    public void run() {
        if (debug)
            System.out.println("Listening...");
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                getMessage(line);
            }
        } catch (Exception e) {
            System.out.println("---------- WARNING ----------");
            e.printStackTrace(System.out);
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
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    protected void getMessage(String message) {
        if (debug)
            System.out.println("<< " + message);

        if (message.contains("PING")) {
            ping();
        } else {
            Message reply = messageHandler.handle(message);
            if (reply != null) {
                sendMessage(reply);
            }
        }
    }

    @Override
    public boolean sendMessage(Message message) {
        if (message instanceof TwitchMessage) {
            messageQueue.add(message);
            try {
                while (messageQueue.canSend()) {
                    TwitchMessage twitchMessage = (TwitchMessage) messageQueue.pop();
                    writer.write("PRIVMSG #" + twitchMessage.getChannel() + " :" + twitchMessage.getText() + '\n');
                    writer.flush();
                    if (debug)
                        System.out.println(">> [" + twitchMessage.getChannel() + "] " + twitchMessage.getText());
                }
                return true;
            } catch (IOException e) {
                if (debug) {
                    System.out.println("\tFailure:");
                    e.printStackTrace(System.out);
                }
                return false;
            }

        }
        return false;
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
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
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
            if (debug) {
                System.out.println("\tFailure:");
                e.printStackTrace(System.out);
            }
            return false;
        }
    }

    private static Server getServer() {
        return new Server("irc.twitch.tv", 6667);
    }

}
