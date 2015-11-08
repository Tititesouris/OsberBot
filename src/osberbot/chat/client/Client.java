package osberbot.chat.client;

/**
 * Created by Tititesouris on 08/11/2015.
 */
public interface Client {

    void joinChannel(String channel);

    void sendMessage(String channel, String message);

    void ping();

    void getMessage(String message);

    void open();

}
