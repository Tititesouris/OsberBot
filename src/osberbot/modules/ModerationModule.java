package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.data.PRIVMSGData;
import osberbot.data.TwitchData;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class ModerationModule extends Module {

    @Override
    public void input(Channel channel, TwitchData data) {
        if (data instanceof PRIVMSGData) {
            PRIVMSGData privmsgData = (PRIVMSGData) data;
            if (!privmsgData.isMod()) {
                checkSwearing(channel, privmsgData);
                checkEmotes(channel, privmsgData);
            }
        }
    }

    private void checkSwearing(Channel channel, PRIVMSGData privmsgData) {
        if (privmsgData.getMessage().contains("$swearing$")) {
            output(channel, "Oy! Watch yo mouth " + privmsgData.getName() + ".");
        }
    }

    private void checkEmotes(Channel channel, PRIVMSGData privmsgData) {
        if (privmsgData.getEmotes().length > 3) {
            output(channel, "Stop spamming emotes " + privmsgData.getName() + ".");
        }
    }

}
