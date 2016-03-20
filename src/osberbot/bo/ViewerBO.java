package osberbot.bo;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class ViewerBO {

    private Integer id;

    private String name;

    private Boolean moderator;

    private RankBO rank;

    public ViewerBO(Integer id, String name, RankBO rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    public boolean hasPower(PowerBO power) {
        if (rank != null)
            return rank.hasPower(power);
        return moderator;
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

    public RankBO getRank() {
        return rank;
    }

    public void setRank(RankBO rank) {
        this.rank = rank;
    }
}
