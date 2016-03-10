package osberbot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.deploy.net.HttpResponse;
import osberbot.twitch.TwitchClient;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Description
 *
 * @author Tititesouris
 * @since 2016/03/10
 */
public class Main {

    public static void main(String[] args) {
        String name = null;
        String password = null;
        try {
            Properties properties = new Properties();
            FileInputStream in;
            in = new FileInputStream("config/twitch.properties");
            properties.load(in);
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TwitchClient twitch = new TwitchClient(name, password);
        twitch.connect();
        Channel tititesouris = twitch.join("starrlett20");
        new Thread(tititesouris).start();
        tititesouris.addObserver(new ChannelObserver() {
            @Override
            public void receive(String message) {
                System.out.println(message);
                if (message.contains("!uptime")) {
                    try {

                        String httpsURL = "https://api.twitch.tv/kraken/streams/starrlett20";
                        URL myurl = new URL(httpsURL);
                        HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
                        InputStream ins = con.getInputStream();
                        InputStreamReader isr = new InputStreamReader(ins);
                        BufferedReader in = new BufferedReader(isr);

                        String data = "";
                        String inputLine;

                        while ((inputLine = in.readLine()) != null)
                        {
                            data += inputLine;
                        }

                        in.close();

                        JsonParser parser = new JsonParser();
                        JsonObject json = (JsonObject) parser.parse(data);
                        if (json.get("stream") == null) {
                            tititesouris.send("The stream hasn't started yet");
                        }
                        else {
                            String start = json.get("stream").getAsJsonObject().get("created_at").getAsString();
                            System.out.println(start);
                            String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                            SimpleDateFormat sdf = new SimpleDateFormat(format);
                            Date d = sdf.parse(start);
                            System.out.println(d);
                            String diff = String.format("%d hours, %d min, %d sec",
                                    TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - d.getTime()),
                                    TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - d.getTime()) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - d.getTime())),
                                    TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - d.getTime()) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - d.getTime()))
                            );
                            tititesouris.send("The stream started " + diff + " ago.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
