package firsttry.osberbot.notifications.updates;

import firsttry.osberbot.notifications.updates.actions.UpdateAction;

import java.util.ArrayList;
import java.util.List;

public abstract class UpdateObservable {

    private List<UpdateObserver> observers = new ArrayList<>();

    public boolean addObserver(UpdateObserver observer) {
        return observers.add(observer);
    }

    public boolean removeObserver(UpdateObserver observer) {
        return observers.remove(observer);
    }

    public int clear() {
        int size = observers.size();
        observers = new ArrayList<>();
        return size;
    }

    public int notifyObservers(UpdateAction action) {
        for (UpdateObserver observer : observers) {
            observer.onUpdate(action);
        }
        return observers.size();
    }

}
