package facebook.lead.push.by.pageid.global;

import org.mortbay.util.ajax.JSON;

public class GetPermanentFacebookAccessToken {
//    https://graph.facebook.com/v9.0/oauth/access_token?grant_type=fb_exchange_token&client_id=179407979328264&client_secret=c653ee328fcd9b2449fdfc8d7c53e996&fb_exchange_token=EAACjK6zgswgBACTVyZCzHkSdEvm9ptsXR7BKvPKQ8LNB3787RKUa6oPZBmG2I3xtL4jbUoUO45ZAru6B6CYKZAbF9AtD4CmpCkiwxJ5yhXusY5O8NfjVt2vO6xiYeZA3RTZAySpE7WhuQe4QZCFau4ELBki0bKPnZCAVNVibpTcQmRUKMiUIibo0aOR0HQRmwX9YI9ZAcVf4VqLFaXxjWH4fJ5v5s0QjtQliZCiHkLHBjGI2x72yCd8rJz
    public static String API_URL = "https://graph.facebook.com/v9.0";
    public static String temporaryUserAccessToken = "EAACjK6zgswgBAFQc3O8b6mLyZACpUka4cRS42xOGPoBGj7puwnkAFGiLQJfZBAUbMGvvdG65e2oZCPDji4NAOoLxKtb4ntsKPhwJK0TFZCHiyB0caXiiROCkdfxCzUe4smdFsZCBX1ZCZCqu5hn5jGP9VADoPWOVtZBO8LQb9nxZAvrzW6PcO3hBGtoNuRP8dZBVErNVNgVMyzA7SzpMbuD3zFa8mqQe1Sxim6VdUdLCEfoWF7Yq1p4UZB0HOMWobAQqxcZD";
    public static String client_id = "179407979328264";
    public static String client_secret = "c653ee328fcd9b2449fdfc8d7c53e996";
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
