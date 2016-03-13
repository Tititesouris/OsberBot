package osberbot.modules;

import osberbot.Channel;
import osberbot.Module;
import osberbot.data.TwitchData;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/13
 */
public class ColorGame extends Module {

    @Override
    public void input(Channel channel, TwitchData data) {

        //@color=#2E8B57;display-name=Tititesouris;emotes=1:0-1;mod=1;room-id=25654602;subscriber=0;turbo=0;user-id=24372791;user-type=mod :tititesouris!tititesouris@tititesouris.tmi.twitch.tv PRIVMSG #honneyplay ::)
        //@color=#2E8B57;display-name=Tititesouris;emotes=22639:0-7/9:9-10/46249:12-19/86:21-30/1900:32-39;mod=1;room-id=25654602;subscriber=0;turbo=0;user-id=24372791;user-type=mod :tititesouris!tititesouris@tititesouris.tmi.twitch.tv PRIVMSG #honneyplay :BabyRage <3 deExcite BibleThump RalpherZ
        //BabyRage <3 deExcite BibleThump RalpherZ
    }

}
