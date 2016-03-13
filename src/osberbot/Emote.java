package osberbot;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class Emote {

    private int id;

    private int start;

    private int end;

    public Emote(int id, int start, int end) {
        this.id= id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return end - start;
    }

    @Override
    public String toString() {
        return "Emote{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

}
