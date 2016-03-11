package osberbot.modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import osberbot.Channel;
import osberbot.Module;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/11
 */
public class Uptime extends Module {

    @Override
    public void input(Channel channel, String message) {
        System.out.println(message);
        if (message.contains("!uptime")) {
            try {
                String httpsURL = "https://api.twitch.tv/kraken/streams/" + channel.getName();
                URL myurl = new URL(httpsURL);
                HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
                InputStream ins = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                BufferedReader in = new BufferedReader(isr);

                String data = "";
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    data += inputLine;
                }

                in.close();

                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(data);
                if (json.get("stream").isJsonNull()) {
                    output(channel, "The stream hasn't started yet");
                } else {
                    String start = json.get("stream").getAsJsonObject().get("created_at").getAsString();
                    String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date d = sdf.parse(start);
                    String diff = String.format("%d hours, %d min, %d sec",
                            TimeUnit.MILLISECONDS.toHours(new Date().getTime() - d.getTime()),
                            TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - d.getTime()) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(new Date().getTime() - d.getTime())),
                            TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - d.getTime()) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - d.getTime()))
                    );
                    output(channel, "The stream started " + diff + " ago.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}