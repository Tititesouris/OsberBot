package osberbot.bo;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class Command {

    private Integer id;

    private String name;

    private String content;

    private RankBO rank;

    public Command() {
    }

    public Command(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RankBO getRank() {
        return rank;
    }

    public void setRank(RankBO rank) {
        this.rank = rank;
    }
}
