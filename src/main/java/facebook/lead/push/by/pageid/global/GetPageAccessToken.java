package facebook.lead.push.by.pageid.global;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetPageAccessToken {
//    public static String userAccessToken = "";
//    Get the page Access token from the GraphAPI page on Facebook for Developers
    public static String userAccessToken = "";
    public static String domainUrl = "https://graph.facebook.com/";
    public static String url;
    public static String fieldsString = "?fields=access_token&access_token=";

    public static void setUrl(String pageid){
        url = domainUrl+pageid+fieldsString + userAccessToken;
    }
    public static String getUrl(){
        return url;
    }

    public static HashMap<String, String> getPageAccessTokenFromWebsite(String[] pageId){
        try {
            HashMap<String, String> mapContaingPageData = new HashMap<String, String>();
            for(int i=0;i<pageId.length;i++){
                setUrl(pageId[i]);
                System.out.println(getUrl());
                String response = HttpUrlConnectionExample.sendGet(getUrl());
                System.out.println(response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
//                System.out.println(jsonObject);
//                System.out.println(jsonObject.getString("access_token"));
                mapContaingPageData.put(pageId[i], jsonObject.getString("access_token"));
            }
            return mapContaingPageData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveDataAsJson(HashMap<String, String> mapContainingPageData)  {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        try {
            String jsonData = objectMapper.writeValueAsString(mapContainingPageData);
            JsonNode tree = objectMapper.readTree(jsonData);
            String formattedJson = objectMapper.writeValueAsString(tree);
            FileWriter fileWriter = new FileWriter("~/facebookdatafetch/src/main/java/facebook/lead/push/by/pageid/docs/pageid_data.json");
            fileWriter.write(formattedJson);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> getPageAccessTokenFromSavedFile(String[] pageId) {
        try {
            HashMap<String, String> pageAccessTokensMap = new HashMap<String, String>();
            BufferedReader reader = new BufferedReader( new FileReader("~/facebookdatafetch/src/main/java/facebook/lead/push/by/pageid/docs/pageid_data.json"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            String data = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(data);
//            System.out.println(jsonObject);
            for (String str : pageId){
//                System.out.println(jsonObject.getString(str));
                try {
                    if(jsonObject.has(str)){
                        pageAccessTokensMap.put(str, jsonObject.getString(str));
                    }else {
                        continue;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return pageAccessTokensMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String[] pageId = {"107890267741685","1938678122885828","501671067014720","679579309083679","721421228220504","292912844650192","1819914298126708","271085653526789","2220650924817075","326767608133324"};
//        GetPageAccessToken getPageAccessToken = new GetPageAccessToken();
//        HashMap<String, String> pageAccessTokenMapFromSavedFile = getPageAccessTokenFromSavedFile(pageId);
//        for (String token : pageAccessTokenMapFromSavedFile.keySet()){
//            System.out.println(token);
//        }
        String[][] excelData = ReadExcelFileData.getExcelData("/~/facebookdatafetch/src/main/java/facebook/lead/push/by/pageid/docs/pageid_data.xlsx");
        String[] pageId = ReadExcelFileData.getPageIdArray(excelData);
        String[] somepageID = new String[20];
        for(int i=0;i<somepageID.length; i++){
            somepageID[i] = pageId[i];
        }
        HashMap<String, String> mapContainingPageData =  getPageAccessTokenFromWebsite(somepageID);
        saveDataAsJson(mapContainingPageData);
        System.out.println(mapContainingPageData.size());
    }

}
