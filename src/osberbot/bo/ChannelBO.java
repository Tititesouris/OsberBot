package osberbot.bo;

import java.sql.Timestamp;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class ChannelBO extends NamedBusinessObject {

    protected Boolean active;

    protected UserBO owner;

    public ChannelBO() {
        this(null, null, null, null, null, null);
    }

    public ChannelBO(Integer id, String name, Boolean active, UserBO owner) {
        this(id, name, active, owner, null, null);
    }

    public ChannelBO(Integer id, String name, Boolean active, UserBO owner, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name, createdAt, updatedAt);
        this.active = active;
        this.owner = owner;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserBO getOwner() {
        return owner;
    }

    public void setOwner(UserBO owner) {
        this.owner = owner;
    }

}
