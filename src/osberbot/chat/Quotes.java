package osberbot.chat;

import osberbot.chat.message.Message;
import osberbot.models.Quote;
import osberbot.models.dao.QuoteDAO;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class Quotes {

    private static final QuoteDAO quoteDAO = new QuoteDAO();

    public static void input(Message message) {
        String text = message.getText();
        String[] args = text.split(" ", 2)[1].toLowerCase().split(" ");
        if (args.length >= 1) {
            switch (args[0]) {
                case "add": //!quote add <text>
                    if (args.length >= 2)
                        quoteDAO.add(new Quote(0, text.split(" ", 3)[2]));
                    break;
            }
        }
    }

}
