package osberbot;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class Emote {

    private int id;

    private int min;

    private int max;

    public Emote(int id, int min, int max) {
        this.id= id;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Emote{" +
                "id=" + id +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

}
