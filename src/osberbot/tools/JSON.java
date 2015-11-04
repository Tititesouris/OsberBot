package osberbot.tools;

import com.google.gson.*;

/**
 * Created by Quentin Brault on 04/11/2015.
 */
public class JSON {

    private static final JsonParser parser = new JsonParser();

    public static JsonObject getObject(String string) {
        try {
            JsonElement json = parser.parse(string);
            if (json.isJsonObject()) {
                return json.getAsJsonObject();
            }
        }
        catch (JsonSyntaxException e) {
            return null;
        }
        return null;
    }

    public static JsonArray getArray(String string) {
        try {
            JsonElement json = parser.parse(string);
            if (json.isJsonArray()) {
                return json.getAsJsonArray();
            }
        }
        catch (JsonSyntaxException e) {
            return null;
        }
        return null;
    }

}
