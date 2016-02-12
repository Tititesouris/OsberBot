package firsttry.osberbot.notifications.inputs;

import firsttry.osberbot.notifications.inputs.actions.InputAction;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public abstract class InputObservable {

    private List<InputObserver> observers = new ArrayList<>();

    public boolean addObserver(InputObserver observer) {
        return observers.add(observer);
    }

    public boolean removeObserver(InputObserver observer) {
        return observers.remove(observer);
    }

    public int clear() {
        int size = observers.size();
        observers = new ArrayList<>();
        return size;
    }

    protected int notifyObservers(InputAction action) {
        for (InputObserver observer : observers) {
            observer.onInput(action);
        }
        return observers.size();
    }

}
