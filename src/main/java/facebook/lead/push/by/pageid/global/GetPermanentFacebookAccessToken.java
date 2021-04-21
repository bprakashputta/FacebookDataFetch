package facebook.lead.push.by.pageid.global;

import org.mortbay.util.ajax.JSON;

public class GetPermanentFacebookAccessToken {
//    https://graph.facebook.com/v9.0/oauth/access_token?grant_type=fb_exchange_token&client_id={}&client_secret={}&fb_exchange_token={}
    public static String API_URL = "https://graph.facebook.com/v9.0";
    //Get Temporary Access token to your facebook account from Facebook for Developers
    public static String temporaryUserAccessToken = "";
    // Get the client_id and client_secret from your project
    public static String client_id = "";
    public static String client_secret = "";
    public static String oAuth = "/oauth/access_token?grant_type=fb_exchange_token";

    public static String getPermanentUserAccessToken(String temporaryUserAccessToken){
        try {
        //   1 Generate Long-Lived Access Token
            String urlStr = API_URL + oAuth+ "&client_id=" +client_id+ "&client_secret="+client_secret+"&fb_exchange_token="+ temporaryUserAccessToken;
            System.out.println(urlStr);
            String response = HttpUrlConnectionExample.sendGet(urlStr);
//            String response = HttpUrlConnectionExample.sendGet("");
            System.out.println(response);
//            String access_token = JSON.parse(response.body)['access_token'];
//            response = RestClient.get("#{API_URL}/me?access_token=#{access_token}")
//            id = JSON.parse(response.body)['id']
//
//  # 3 Get Permanent Page Access Token
//                    response = RestClient.get("#{API_URL}/#{id}/accounts?access_token=#{access_token}")
//
//  # 4 Array of items the user has access to
//            JSON.parse(response.body)['data']
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getPermanentUserAccessToken(temporaryUserAccessToken);
    }
}
