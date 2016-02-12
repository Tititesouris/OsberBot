package firsttry.osberbot.chat.client;

import firsttry.osberbot.chat.Server;
import firsttry.osberbot.chat.message.Message;
import firsttry.osberbot.chat.message.handler.MessageHandler;

/**
 * This abstract class represents a client capable of connecting to a server composed of channels using a name and a password.
 * This abstract class must be extended by any client, whether it is an IRC client or a Websocket client, in order to allow cross-compatibility and simple data handling.
 *
 * @author Tititesouris
 * @see Server
 */
abstract class Client {

    /**
     * Server the client can connect to.
     */
    protected final Server server;

    /**
     * Name of the client.
     */
    protected final String name;

    /**
     * Password of the client.
     */
    protected final String password;

    /**
     * Message handler for the client to process the messages sent by the server.
     */
    protected final MessageHandler messageHandler;

    /**
     * Whether or not the client is in debug mode.
     */
    protected boolean debug;

    /**
     * Creates a new client.
     *
     * @param server         Server the client can connect to
     * @param name           Name to connect to the server with
     * @param password       Password to connect to the server with
     * @param messageHandler Message handler for the client to process the messages sent by the server
     */
    public Client(Server server, String name, String password, MessageHandler messageHandler) {
        this.server = server;
        this.name = name;
        this.password = password;
        this.messageHandler = messageHandler;
    }

    /**
     * This method does the eventual preparation before connecting to the server.
     *
     * @return True if the client initialized correctly. False otherwise.
     */
    public abstract boolean init();

    /**
     * This method connects the client to the server.
     *
     * @return True if the client could connect to the server. False otherwise.
     */
    public abstract boolean connect();

    /**
     * This method disconnects the client from the server.
     *
     * @return True if the client could disconnect from the server. False otherwise.
     */
    public abstract boolean disconnect();

    /**
     * This method gets called when the server pings the client.
     * Its only purpose is to call the pong() method to keep the connection alive with the server.
     */
    protected void ping() {
        pong();
    }

    /**
     * This method must be called by the client upon receiving a ping to keep the connection alive with the server.
     *
     * @return True if the client could ping the server. False otherwise.
     */
    public abstract boolean pong();

    /**
     * This method gets called when the client receives a message from the channel.
     * This method does not get called when the client receives a whisper from a user.
     *
     * @param message Message received by the client
     */
    protected abstract void getMessage(String message);

    /**
     * This method sends a message to a channel of the server.
     *
     * @param message Message to send to the server
     * @return True if the message was sent successfully. False otherwise.
     */
    public abstract boolean sendMessage(Message message);

    /**
     * This method gets called when the client receives a whisper from a user.
     * This method does not get called when the client receives a message from the channel.
     *
     * @param name    Name of the user sending the whisper
     * @param message Message received by the client
     */
    protected abstract void getWhisper(String name, String message);

    /**
     * This method sends a whisper to a client of the server.
     *
     * @param name    Name of the recipient of the whisper
     * @param message Message to send to the recipient
     * @return True if the whisper was sent successfully. False otherwise.
     */
    public abstract boolean sendWhisper(String name, String message);

    /**
     * This method connects the client to a channel of the server.
     *
     * @param channel Channel to join
     * @return True if the client could join the channel. False otherwise.
     */
    public abstract boolean joinChannel(String channel);

    /**
     * This method disconnects the client from a channel of the server.
     *
     * @param channel Channel to leave
     * @return True if the client could leave the channel. False otherwise.
     */
    public abstract boolean leaveChannel(String channel);

    /**
     * Enables or disables debugging.
     *
     * @param debug Enables debugging if true. Disables debugging if false.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
