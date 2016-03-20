package osberbot.bo;

import java.sql.Timestamp;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class CommandBO extends NamedBusinessObject {

    private String content;

    private ChannelBO channel;

    private RankBO rank;

    public CommandBO() {
        this(null, null, null, null, null);
    }

    public CommandBO(Integer id, String name, String content, ChannelBO channel, RankBO rank) {
        this(id, name, content, channel, rank, null, null);
    }

    public CommandBO(Integer id, String name, String content, ChannelBO channel, RankBO rank, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name, createdAt, updatedAt);
        this.content = content;
        this.channel = channel;
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChannelBO getChannel() {
        return channel;
    }

    public void setChannel(ChannelBO channel) {
        this.channel = channel;
    }

    public RankBO getRank() {
        return rank;
    }

    public void setRank(RankBO rank) {
        this.rank = rank;
    }
}
