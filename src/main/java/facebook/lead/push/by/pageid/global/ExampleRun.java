package facebook.lead.push.by.pageid.global;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

public class ExampleRun {
    private static String url;

    public static String getUrl() {
        return url;
    }
    public ExampleRun(String url) {
        ExampleRun.url = url;
    }

    public static void getLeadData(String url) throws IOException {
        String response =HttpUrlConnectionExample.sendGet(url);
//        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        System.out.println(jsonArray);
    }

    public static void main(String[] args) throws IOException {
//        String url = "https://graph.facebook.com/v3.2/439298107151328/leads?access_token=EAACjK6zgswgBAHZBS17gHpZCbuwL9HoYZA7wEpwJ2W8E8CXZCfqOwzIXfaSgmLac9lckTcjKPGxLkXDaxZAY0lRx3ZAtSae9ZAmkwZCQR2z0hVQt8ZArpLmrSQTLb4SFZBKL8PMjVkZCyZBBULPiZBdgBZAS5nWQ4JCYsdYuB8z43J5FTBICVkThPUMwaovwa6QCShfsxcl3HNoZAKX5IC6jf7gAZAQI&pretty=1&limit=25&after=QVFIUnQ1TjVOZAUYybGJMeVB5OERjZAHlrYlp4ZA050Nkp3blRoTTVNbVlIeGlET0NuTmg4bjR0RFRPandzU1RudTZAYMm4wMTIyVkI0S3NPZA0U4VkNJdXRGMUx3";
//        String Nullurl = "https://graph.facebook.com/v3.2/439298107151328/leads?access_token=EAACjK6zgswgBAHZBS17gHpZCbuwL9HoYZA7wEpwJ2W8E8CXZCfqOwzIXfaSgmLac9lckTcjKPGxLkXDaxZAY0lRx3ZAtSae9ZAmkwZCQR2z0hVQt8ZArpLmrSQTLb4SFZBKL8PMjVkZCyZBBULPiZBdgBZAS5nWQ4JCYsdYuB8z43J5FTBICVkThPUMwaovwa6QCShfsxcl3HNoZAKX5IC6jf7gAZAQI&pretty=1&limit=25&after=QVFIUjluNFdjTXB0N09ibExjZAm96NHh1bHU0WjhIa2lpYUZAwa0RjQWFxY2o3dWZARQWhkZAXRQSjE4b0JoZAEFtQmIwUXhvVW1QRHNDdnU2Ml9scFZAHRklrTnRR";
//        ExampleRun exampleRun = new ExampleRun(Nullurl);
//        exampleRun.getLeadData(Nullurl)
        String url = "https://graph.facebook.com/v3.2/439298107151328/leads?access_token=EAACjK6zgswgBAHZBS17gHpZCbuwL9HoYZA7wEpwJ2W8E8CXZCfqOwzIXfaSgmLac9lckTcjKPGxLkXDaxZAY0lRx3ZAtSae9ZAmkwZCQR2z0hVQt8ZArpLmrSQTLb4SFZBKL8PMjVkZCyZBBULPiZBdgBZAS5nWQ4JCYsdYuB8z43J5FTBICVkThPUMwaovwa6QCShfsxcl3HNoZAKX5IC6jf7gAZAQI&pretty=1&limit=25&after=QVFIUnQ1TjVOZAUYybGJMeVB5OERjZAHlrYlp4ZA050Nkp3blRoTTVNbVlIeGlET0NuTmg4bjR0RFRPandzU1RudTZAYMm4wMTIyVkI0S3NPZA0U4VkNJdXRGMUx3";
        ExampleRun exampleRun = new ExampleRun(url);
        exampleRun.getLeadData(url);



//        for(int j=0;j<fieldsAccess.length();j++){
//            try {
//                JSONObject formAccess = fieldsAccess.getJSONObject(j);
//                // form data created time
//                String created_time = formAccess.getString("created_time");
//                // access form id
//                String form_id = formAccess.getString("id");
//                JSONArray fields_data = formAccess.getJSONArray("field_data");
//                HashMap<String, String> mapContainingFieldData = new HashMap<String, String>();
//                mapContainingFieldData.put("page_id", getPageId());
//                mapContainingFieldData.put("lead_id", getLeadId());
//                mapContainingFieldData.put("form_id", form_id);
//                mapContainingFieldData.put("created_time", created_time);
//                for(int k=0; k< fields_data.length(); k++){
//                    JSONObject data = fields_data.getJSONObject(k);
//                    JSONArray data_values = data.getJSONArray("values");
//                    mapContainingFieldData.put(data.getString("name"), data_values.getString(0));
//                }
//                leadsDataList.add(mapContainingFieldData);
////                        System.out.println(mapContainingFieldData.keySet());
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }
}
