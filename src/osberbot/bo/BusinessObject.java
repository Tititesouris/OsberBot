package osberbot.bo;

import java.sql.Timestamp;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class BusinessObject {

    protected Integer id;

    protected Timestamp createdAt;

    protected Timestamp updatedAt;

    public BusinessObject() {
        this(null, null, null);
    }

    public BusinessObject(Integer id) {
        this(id, null, null);
    }

    public BusinessObject(Integer id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
