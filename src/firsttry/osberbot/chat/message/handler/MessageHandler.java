package firsttry.osberbot.chat.message.handler;

import firsttry.osberbot.chat.message.Message;

/**
 * This interface gathers all the methods necessary for a client to handle a message sent by a server and send an appropriate message back.
 * Note that this interface and the child classes's role is not to handle pings, only messages that can alter the data.
 *
 * @author Tititesouris
 * @see firsttry.osberbot.chat.client.Client
 */
public interface MessageHandler {

    /**
     * This method is generally the only one that gets called outside of the message handler.
     * Its job is to handle the unaltered message sent by the server, call the appropriate methods of the message handler and return an appropriate message for the client to send back to the server.
     *
     * @param message Message received from the server
     * @return The message to send back to the server. Or null if nothing should get sent.
     */
    Message handle(String message);

    /**
     * This method's job is to handle a message received from the server, knowing all the information sent by the server about the channel and the author.
     * This information in bundled in the Message object.
     *
     * @param message Message sent by the user
     * @return The message to send back to the server. Or null if nothing should get sent.
     */
    Message handleMessage(Message message);

    /**
     * This method's job is to handle a command received from the server, knowing the channel it was used in and the user it was used by, and return an appropriate message for the client to send back to the server.
     *
     * @param channel Channel the command was used in
     * @param name    Name of the user the command was used by
     * @param command Text containing the command, its parameters and their values
     * @return The message to send back to the server. Or null if nothing should get sent.
     */
    Message handleCommand(String channel, String name, String command);

}
