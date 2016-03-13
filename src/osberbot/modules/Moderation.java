package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.data.MODEData;
import osberbot.data.PRIVMSGData;
import osberbot.data.TwitchData;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class Moderation extends Module {

    @Override
    public void input(Channel channel, TwitchData data) {
        if (data instanceof PRIVMSGData) {
            PRIVMSGData privmsgData = (PRIVMSGData) data;
            System.out.println("~~~" + privmsgData.getMessage());
            if (privmsgData.getMessage().contains("$swearing$")) {
                if (!privmsgData.isMod()) {
                    output(channel, "Oy! Watch yo mouth.");
                }
            }
        }
    }

}
