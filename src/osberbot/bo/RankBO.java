package osberbot.bo;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class RankBO {

    private Integer id;

    private String name;

    public RankBO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean hasPower(PowerBO power) {
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
