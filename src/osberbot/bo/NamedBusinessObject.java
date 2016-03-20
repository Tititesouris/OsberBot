package osberbot.bo;

import java.sql.Timestamp;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class NamedBusinessObject extends BusinessObject {

    protected String name;

    public NamedBusinessObject() {
        this(null, null, null, null);
    }

    public NamedBusinessObject(Integer id, String name) {
        this(id, name, null, null);
    }

    public NamedBusinessObject(Integer id, String name, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
