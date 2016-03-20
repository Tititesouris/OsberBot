package osberbot.bo;

import osberbot.Channel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class UserBO extends NamedBusinessObject {

    private List<ChannelBO> channels;

    public UserBO() {
        this(null, null, new ArrayList<ChannelBO>(), null, null);
    }

    public UserBO(Integer id, String name, List<ChannelBO> channels) {
        this(id, name, channels, null, null);
    }

    public UserBO(Integer id, String name, List<ChannelBO> channels, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name, createdAt, updatedAt);
        this.channels = channels;
    }

    public List<ChannelBO> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelBO> channels) {
        this.channels = channels;
    }

}
