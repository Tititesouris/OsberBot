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

    private List<Module> modules;

    public Channel(Client client, String name) {
        this.client = client;
        this.name = name;
        this.modules = new ArrayList<>();
    }

    protected abstract void send(String message);

    @Override
    public void run() {
        String line;
        while ((line = client.read()) != null) {
            notifyModules(line);
        }
    }

    private void notifyModules(String message) {
        for (Module module : modules)
            module.input(this, message);
    }

    public Channel addModule(Module observer) {
        modules.add(observer);
        return this;
    }

    public Channel removeModule(Module observer) {
        modules.remove(observer);
        return this;
    }

    public String getName() {
        return name;
    }

}
