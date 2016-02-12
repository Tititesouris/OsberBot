package osberbot;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/02/12
 */
public class Logger {

    public static void log(String log) {
        System.out.println(log);
    }

    public static void error(String error) {
        System.out.println("---[ERROR] " + error);
    }

    public static void error(Exception e) {
        System.out.println("---[ERROR]---");
        e.printStackTrace();
    }

}
