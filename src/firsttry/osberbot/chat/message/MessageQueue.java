package firsttry.osberbot.chat.message;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a queue of messages that are waiting to be sent to the server.
 *
 * @author Tititesouris
 */
public class MessageQueue {

    /**
     * List of messages to be sent.
     */
    private final List<Message> messages;

    /**
     * Timestamps of the last {@code messageLimit} messages sent.
     */
    private final long[] timestamps;

    /**
     * Maximum amount of messages that can be sent in the time limit.
     */
    private final int messageLimit;

    /**
     * Time interval within which there must not be more than {@code messageLimit} messages sent, in milliseconds.
     */
    private final long timeLimit;

    /**
     * Creates a new message queue.
     * This queue cannot send more than {@code messageLimit} messages within {@code timeLimit} milliseconds.
     *
     * @param messageLimit  Maximum amount of messages that can be sent in the time limit
     * @param timeLimit     Time interval within which there must not be more than {@code messageLimit} messages sent, in milliseconds.
     */
    public MessageQueue(int messageLimit, int timeLimit) {
        this.messages = new ArrayList<>();
        this.timestamps = new long[messageLimit];
        this.messageLimit = messageLimit;
        this.timeLimit = timeLimit;
    }

    /**
     * This method returns whether or not the client can send the next message in the queue.
     * That is, if there is any message to send and if the message limit has not been reached.
     *
     * @return  True if the client can send the next message. False otherwise.
     */
    public boolean canSend() {
        for (int i = 0; i < timestamps.length; i++) {
            System.out.println(System.currentTimeMillis() - timestamps[i]);
        }
        return messages.size() > 0 && System.currentTimeMillis() - timestamps[messageLimit - 1] > timeLimit;
    }

    /**
     * This method adds a message to the queue.
     *
     * @param message   Message to add to the queue
     * @return          True if the message was added. False otherwise.
     */
    public boolean add(Message message) {
        return messages.add(message);
    }

    /**
     * This method removes the next message from the queue and returns it.
     * It also stores the current timestamp to keep track of the time limit.
     *
     * @return  Next message in the queue to be sent.
     */
    public Message pop() {
        for (int i = 1; i < messageLimit; i++) {
            timestamps[i] = timestamps[i-1];
        }
        timestamps[0] = System.currentTimeMillis();
        return messages.remove(0);
    }

}
