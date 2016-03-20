package osberbot.bo;

import java.sql.Timestamp;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class UserBO extends NamedBusinessObject {

    public UserBO() {
    }

    public UserBO(Integer id, String name) {
        super(id, name);
    }

    public UserBO(Integer id, String name, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name, createdAt, updatedAt);
    }

}
