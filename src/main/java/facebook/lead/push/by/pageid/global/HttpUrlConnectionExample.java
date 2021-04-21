package facebook.lead.push.by.pageid.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionExample {
    private static String USER_AGENT = "Mozilla/5.0";
    private static String urlStr;
    public HttpUrlConnectionExample(String url) {
        urlStr = url;
    }

    public static String sendGet(String urlStr) throws IOException{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();
//        JSONObject jsonResponse = new JSONObject(result.toString());
        return result.toString();
    }

//    public static void main(String[] args) throws IOException {
////        String URL = "https://graph.facebook.com/107890267741685?fields=leadgen_forms{id,leads}&access_token=EAACjK6zgswgBAHZBS17gHpZCbuwL9HoYZA7wEpwJ2W8E8CXZCfqOwzIXfaSgmLac9lckTcjKPGxLkXDaxZAY0lRx3ZAtSae9ZAmkwZCQR2z0hVQt8ZArpLmrSQTLb4SFZBKL8PMjVkZCyZBBULPiZBdgBZAS5nWQ4JCYsdYuB8z43J5FTBICVkThPUMwaovwa6QCShfsxcl3HNoZAKX5IC6jf7gAZAQI&after=";
////        String facebookUrl = "https://graph.facebook.com/107890267741685?fields=leadgen_forms{id,leads}&access_token=EAACjK6zgswgBAHZBS17gHpZCbuwL9HoYZA7wEpwJ2W8E8CXZCfqOwzIXfaSgmLac9lckTcjKPGxLkXDaxZAY0lRx3ZAtSae9ZAmkwZCQR2z0hVQt8ZArpLmrSQTLb4SFZBKL8PMjVkZCyZBBULPiZBdgBZAS5nWQ4JCYsdYuB8z43J5FTBICVkThPUMwaovwa6QCShfsxcl3HNoZAKX5IC6jf7gAZAQI&after=";
//        System.out.println(sendGet(URL));
//    }
}
