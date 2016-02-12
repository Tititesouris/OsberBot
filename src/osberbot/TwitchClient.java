package osberbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public class TwitchClient extends Client {

    public TwitchClient(String login, String password) {
        super("irc.twitch.tv", 6667, login, password);
    }

    @Override
    public boolean connect() {
        Logger.log("Connecting to Twitch.");
        write("NICK " + login);
        write("PASS " + password);
        write("CAP REQ :twitch.tv/membership");
        write("CAP REQ :twitch.tv/commands");
        write("CAP REQ :twitch.tv/tags");
        flush();
        if (read().contains(":tmi.twitch.tv 001")) {
            Logger.log("Successfully connected to Twitch.");
            return true;
        }
        Logger.log("Failed to connect to Twitch");
        return false;
    }

    @Override
    public boolean disconnect() {
        Logger.log("Disconnecting from Twitch.");
        try {
            socket.close();
            Logger.log("Successfully disconnected from Twitch.");
            return true;
        } catch (IOException e) {
            Logger.log("Failed to disconnect from Twitch.");
            Logger.error(e);
            return false;
        }
    }

    @Override
    public Channel join(String channel) {
        Logger.log("Joining channel #" + channel);
        write("JOIN #" + channel);
        Logger.log("Successfully joined channel #" + channel);
        return new Channel(this, channel);
    }

    @Override
    public boolean leave(Channel channel) {
        return false;
    }


/*
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
*/
}
