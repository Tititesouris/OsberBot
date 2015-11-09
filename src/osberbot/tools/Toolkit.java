package osberbot.tools;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class Toolkit {

    public static int countSpaces(String string) {
        return string.length() - string.replace(" ", "").length();
    }

}
