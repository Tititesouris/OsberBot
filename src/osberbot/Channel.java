package osberbot;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public abstract class Channel implements Runnable {

    protected Client client;

    protected String name;

    private List<ChannelObserver> observers;

    public Channel(Client client, String name) {
        this.client = client;
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public abstract void send(String message);

    @Override
    public void run() {
        String line;
        while ((line = client.read()) != null) {
            notifyObservers(line);
        }
    }

    private void notifyObservers(String message) {
        for (ChannelObserver observer : observers)
            observer.receive(message);
    }

    public boolean addObserver(ChannelObserver observer) {
        return observers.add(observer);
    }

    public boolean removeObserver(ChannelObserver observer) {
        return observers.remove(observer);
    }

}
