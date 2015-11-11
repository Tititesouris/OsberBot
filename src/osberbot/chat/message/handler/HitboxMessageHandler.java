package osberbot.chat.message.handler;

import com.google.gson.JsonObject;
import osberbot.chat.message.HitboxMessage;
import osberbot.chat.message.Message;
import osberbot.tools.JSON;

/**
 * TODO: Description
 *
 * @author Tititesouris
 */
public class HitboxMessageHandler implements MessageHandler {

    @Override
    public HitboxMessage handle(String message) {
        JsonObject jsonObject = JSON.getObject(message.substring(4));
        String msgName = jsonObject.get("name").getAsString();
        if (msgName.equals("message")) {
            JsonObject args = JSON.getObject(jsonObject.get("args").getAsJsonArray().get(0).getAsString());
            String method = args.get("method").getAsString();
            if (method.equals("chatMsg")) {
                JsonObject params = args.get("params").getAsJsonObject();
                String channel = params.get("channel").getAsString();
                String name = params.get("name").getAsString();
                String color = params.get("nameColor").getAsString();
                String text = params.get("text").getAsString();
                int time = params.get("time").getAsInt();
                String role = params.get("role").getAsString();
                boolean isFollower = params.get("isFollower").getAsBoolean();
                boolean isSubscriber = params.get("isSubscriber").getAsBoolean();
                boolean isOwner = params.get("isOwner").getAsBoolean();
                boolean isStaff = params.get("isStaff").getAsBoolean();
                boolean isCommunity = params.get("isCommunity").getAsBoolean();
                boolean media = params.get("media").getAsBoolean();
                if (text.startsWith("!say ")) {
                    return new HitboxMessage(channel, text.substring(5));
                }
            }
        }
        return null;
    }

    @Override
    public HitboxMessage handleMessage(Message message) {
        return null;
    }

    @Override
    public HitboxMessage handleCommand(String channel, String name, String command) {
        return null;
    }

}
