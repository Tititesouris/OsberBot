package firsttry.osberbot.tools;

import com.google.gson.*;

/**
 * This utilitarian class allows for easy Json operations.
 */
public class JSON {

    private static final JsonParser parser = new JsonParser();

    public static JsonObject getObject(String string) {
        try {
            JsonElement json = parser.parse(string);
            if (json.isJsonObject()) {
                return json.getAsJsonObject();
            }
        } catch (JsonSyntaxException e) {
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
        } catch (JsonSyntaxException e) {
            return null;
        }
        return null;
    }

}
