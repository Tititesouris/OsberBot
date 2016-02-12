package firsttry.osberbot.chat;

import firsttry.osberbot.chat.message.Message;
/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class Quotes {

    public static void input(Message message) {
        String text = message.getText();
        String[] args = text.split(" ", 2)[1].toLowerCase().split(" ");
    }

}
