package osberbot.twitch;

import osberbot.Channel;
import osberbot.Client;
import osberbot.data.*;
import osberbot.utils.Logger;

import java.io.IOException;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public class TwitchClient extends Client {

    public TwitchClient(String login, String password) {
        super("irc.chat.twitch.tv", 443, login, password);
    }

    @Override
    public boolean connect() {
        Logger.log("Connecting to Twitch.");
        write("PASS " + password);
        write("NICK " + login);
        write("CAP REQ :twitch.tv/membership");
        write("CAP REQ :twitch.tv/commands");
        write("CAP REQ :twitch.tv/tags");
        flush();
        if (read().contains(":tmi.twitch.tv 001")) {
            for (int i = 0; i < 8; i++) {
                read();
            }
            if (read().contains(":tmi.twitch.tv CAP * ACK :twitch.tv/tags")) {
                Logger.log("Successfully connected to Twitch.");
                return true;
            }
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
        flush();
        Logger.log("Successfully joined channel #" + channel);
        TwitchChannel joined = new TwitchChannel(this, channel);
        channels.add(joined);
        return joined;
    }

    @Override
    public boolean leave(Channel channel) {
        channels.remove(channel);
        return false;
    }

    @Override
    public void run() {
        String line;
        while ((line = read()) != null) {
            if (line.equals("PING :tmi.twitch.tv")) {
                write("PONG");
                flush();
            } else {
                System.out.println("<< " + line);

                if (PRIVMSGData.matches(line)) {
                    PRIVMSGData data = new PRIVMSGData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                } else if (JOINData.matches(line)) {
                    JOINData data = new JOINData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                } else if (PARTData.matches(line)) {
                    PARTData data = new PARTData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                } else if (MODEData.matches(line)) {
                    MODEData data = new MODEData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                } else if (ROOMSTATEData.matches(line)) {
                    ROOMSTATEData data = new ROOMSTATEData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                } else if (USERSTATEData.matches(line)) {
                    USERSTATEData data = new USERSTATEData(line);
                    for (Channel channel : channels) {
                        if (channel.getName().equals(data.getChannel())) {
                            channel.receive(data);
                        }
                    }
                }
            }
        }
    }


}
