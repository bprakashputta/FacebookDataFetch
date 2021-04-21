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
////        String URL = "https://graph.facebook.com/{pageid}?fields=leadgen_forms{id,leads}&access_token={}&after=";
////        String facebookUrl = "https://graph.facebook.com/{pageid}?fields=leadgen_forms{id,leads}&access_token={}&after=";
//        System.out.println(sendGet(URL));
//    }
}
