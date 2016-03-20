package osberbot.dao;

import osberbot.bo.CommandBO;

import java.sql.SQLException;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/20
 */
public class CommandsDAO extends DataAccessObject {

    public CommandBO add(CommandBO command) {
        try {
            connection.prepareStatement("INSERT INTO commands (channel_id, rank_id, name, content) VALUES (" + command.getChannel().getId() + ", " + command.getRank().getId() + ", '" + command.getName() + "', " + command.getContent() + ") RETURNING id, channel_id, rank_id, name, content, created_at, updated_at");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // add to database, database checks integrity
        // get from database what was added
        return new CommandBO();
    }

}
