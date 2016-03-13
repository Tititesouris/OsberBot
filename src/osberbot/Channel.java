package osberbot;

import osberbot.data.TwitchData;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public abstract class Channel {

    protected Client client;

    protected String name;

    private List<Module> modules;

    public Channel(Client client, String name) {
        this.client = client;
        this.name = name;
        this.modules = new ArrayList<>();
    }

    public abstract void receive(TwitchData data);

    protected abstract void send(String message);

    protected void notifyModules(TwitchData message) {
        for (Module module : modules)
            module.input(this, message);
    }

    public Channel addModule(Module module) {
        modules.add(module);
        return this;
    }

    public Channel removeModule(Module module) {
        modules.remove(module);
        return this;
    }

    public String getName() {
        return name;
    }

}
