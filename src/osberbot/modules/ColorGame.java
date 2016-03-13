package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.data.PRIVMSGData;
import osberbot.data.TwitchData;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class ColorGame extends Module {

    private Map<String, List<String>> teams;

    @Override
    public void input(Channel channel, TwitchData data) {
        if (teams == null) {
            reset();
        }
        if (data instanceof PRIVMSGData) {
            PRIVMSGData privmsgData = (PRIVMSGData) data;
            if (privmsgData.getMessage().startsWith("&join")) {
                Color color = Color.decode("#" + privmsgData.getColor());
                System.out.println(color);
                String team = "NONE";
                if (color.getRed() > color.getGreen() && color.getRed() > color.getBlue()) {
                    team = "RED";
                }
                else if (color.getGreen() > color.getRed() && color.getGreen() > color.getBlue()) {
                    team = "GREEN";
                }
                else if (color.getBlue() > color.getRed() && color.getBlue() > color.getGreen()) {
                    team = "BLUE";
                }
                join(channel, team, privmsgData.getName());
            }
        }
    }

    private void join(Channel channel, String team, String name) {
        teams.get(team).add(name.toLowerCase());
        output(channel, name + " joins team " + team + ".");
    }

    private void reset() {
        teams = new HashMap<>();
        teams.put("RED", new ArrayList<String>());
        teams.put("GREEN", new ArrayList<String>());
        teams.put("BLUE", new ArrayList<String>());
    }

}
