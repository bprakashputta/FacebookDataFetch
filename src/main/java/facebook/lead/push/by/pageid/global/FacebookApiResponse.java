package facebook.lead.push.by.pageid.global;

import facebook.lead.push.by.pageid.database.GenericDB;
import facebook.lead.push.by.pageid.entity.FacebookLeadPush;
import facebook.lead.push.by.pageid.tables.Facebookleadpush;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import
public class FacebookApiResponse{
    private String newleadsUrl;
    public static String old_url;
    public static String domainUrl = "https://graph.facebook.com/";
    public static String string_page = "?fields=leadgen_forms{id,leads}&access_token=";
    public static String string_leads = "/leads?access_token=";

    public String getNewUrl() {
        return newleadsUrl;
    }

    public void setNewUrl(String domainUrl, String leadId, String accessTokenPage, String after) {
        this.newleadsUrl = domainUrl + leadId + string_leads+ accessTokenPage + "&after=" + after;
    }

    public FacebookApiResponse() {

    }

    @SuppressWarnings("rawtypes")
    public ArrayList<HashMap<String, String>> getJsonResponse(String url, String pageId, String page_access_token) {
//        long count=0;
        try {
            ArrayList<HashMap<String, String>> finalLeadDataList = new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> leadsDataList = new ArrayList<HashMap<String, String>>();
            String facebookResponse = HttpUrlConnectionExample.sendGet(url);
            JSONObject jsonObject = new JSONObject(facebookResponse);
            JSONObject leadgen_forms = jsonObject.getJSONObject("leadgen_forms");
            JSONArray leadgen_data = leadgen_forms.getJSONArray("data");
           //Iterating the lead id's - 2
            for(int i=0; i< leadgen_data.length(); i++){
                JSONObject leadAccess = leadgen_data.getJSONObject(i);
                //If the data array has an json
                // object with the name leads
                try {
                    if(leadAccess.getJSONObject("leads") != null){
                        JSONObject leads = leadAccess.getJSONObject("leads");
                        // Set Lead id
                        String leadId = leadAccess.getString("id");
                        System.out.println(pageId+ "===>" + leadId);
                        JSONArray fieldsAccess = leads.getJSONArray("data");
                        leadsDataList = extractLeadsData(fieldsAccess, leadId, pageId);
                        finalLeadDataList.addAll(leadsDataList);
                        leadsDataList.clear();
                        //cursors to navigate to next page
                        JSONObject paging = leads.getJSONObject("paging");
                        JSONObject cursors = paging.getJSONObject("cursors");
                        setNewUrl(domainUrl,leadId, page_access_token, cursors.getString("after"));
                        System.out.println(finalLeadDataList.size());

                        while (fieldsAccess.length() != 0 ){
                            try {
                                String nextLeadsJsonResponse = HttpUrlConnectionExample.sendGet(getNewUrl());
                                leadAccess = new JSONObject(nextLeadsJsonResponse);
//                          System.out.println(leadAccess);
                                fieldsAccess = leadAccess.getJSONArray("data");
                                leadsDataList = getNextLeadsData(fieldsAccess, leadId, pageId);
                                finalLeadDataList.addAll(leadsDataList);
                                leadsDataList.clear();
                                if(fieldsAccess.length() != 0){
//                            System.out.println("Heello worlds : "+leadAccess.getJSONObject("paging"));
                                    paging = leadAccess.getJSONObject("paging");
                                    cursors = paging.getJSONObject("cursors");
                                    setNewUrl(domainUrl, leadId, page_access_token, cursors.getString("after"));
                                    System.out.println(getNewUrl());
                                }else{
                                    continue;
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }else{
                        continue;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

//            for (HashMap<String, String> stringStringHashMap : finalLeadDataList) {
//                System.out.println(stringStringHashMap);
//            }
            System.out.println(finalLeadDataList.size());
            return finalLeadDataList;
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Count is : "+count);
        return null;
    }

    private ArrayList<HashMap<String, String>> extractLeadsData(JSONArray fieldsAccess, String leadId, String pageId) {
        ArrayList<HashMap<String, String>> leadDataList = new ArrayList<HashMap<String, String>>();
        for(int j = 0; j<fieldsAccess.length(); j++){
            try {
                JSONObject formAccess = fieldsAccess.getJSONObject(j);
                // form data created time
                String created_time = formAccess.getString("created_time");
                // access form id
                String formId = formAccess.getString("id");
                JSONArray fields_data = formAccess.getJSONArray("field_data");
                HashMap<String, String> mapContainingFieldData = new HashMap<String, String>();
                mapContainingFieldData.put("page_id", pageId);
                mapContainingFieldData.put("lead_id", leadId);
                mapContainingFieldData.put("form_id", formId);
                mapContainingFieldData.put("created_time", created_time);
                for(int k=0; k< fields_data.length(); k++){
                    JSONObject data = fields_data.getJSONObject(k);
                    JSONArray data_values = data.getJSONArray("values");
                    mapContainingFieldData.put(data.getString("name"), data_values.getString(0));
                }
                leadDataList.add(mapContainingFieldData);
//                        System.out.println(mapContainingFieldData.keySet());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return leadDataList;
    }

    private ArrayList<HashMap<String, String>> getNextLeadsData(JSONArray data_leads, String leadId, String pageId) {
//        System.out.println(data_leads.length());
        ArrayList<HashMap<String, String>> leadDataList = new ArrayList<HashMap<String, String>>();
        for(int i=0; i< data_leads.length(); i++){
            try {
                JSONObject formAccess = data_leads.getJSONObject(i);
                // form data created time
                String created_time = formAccess.getString("created_time");
                // access form id
                String formId = formAccess.getString("id");
                JSONArray fields_data = formAccess.getJSONArray("field_data");
                HashMap<String, String> mapContainingFieldData = new HashMap<String, String>();
                mapContainingFieldData.put("page_id", pageId);
                mapContainingFieldData.put("lead_id", leadId);
                mapContainingFieldData.put("form_id", formId);
                mapContainingFieldData.put("created_time", created_time);
                for(int k=0; k< fields_data.length(); k++){
                    JSONObject data = fields_data.getJSONObject(k);
                    JSONArray data_values = data.getJSONArray("values");
                    mapContainingFieldData.put(data.getString("name"), data_values.getString(0));
                }
                leadDataList.add(mapContainingFieldData);
//                        System.out.println(mapContainingFieldData.keySet());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return leadDataList;
    }

    private static void insertDataIntoDatabase(ArrayList<HashMap<String, String>> finalLeadDataList) {
        for (HashMap hashMap : finalLeadDataList){
            String page_id;
            if(hashMap.get("page_id")!=null){
                page_id = hashMap.get("page_id").toString();
            }else {
                page_id = "";
            }
            String lead_id;
            if(hashMap.get("lead_id")!=null){
                lead_id = hashMap.get("lead_id").toString();
            }else {
                lead_id = "";
            }
            String form_id;
            if(hashMap.get("form_id")!=null){
                form_id = hashMap.get("form_id").toString();
            }else {
                form_id = "";
            }
            String customer;
            if(hashMap.get("customer")!=null){
                customer = hashMap.get("customer").toString();
            }else {
                customer = "";
            }
            String webform_id;
            if(hashMap.get("webform_id")!=null){
                webform_id = hashMap.get("webform_id").toString();
            }else {
                webform_id = "";
            }
            String full_name;
            if(hashMap.get("full_name")!=null){
                full_name = hashMap.get("full_name").toString();
            }else {
                full_name = "";
            }
            String when_are_you_planning_to_make_a_purchase;
            if(hashMap.get("when_are_you_planning_to_make_a_purchase?")!=null){
                when_are_you_planning_to_make_a_purchase = hashMap.get("when_are_you_planning_to_make_a_purchase?").toString();
            }else {
                when_are_you_planning_to_make_a_purchase = "";
            }
            String phone_number;
            if(hashMap.get("phone_number")!=null){
                phone_number = hashMap.get("phone_number").toString();
            }else {
                phone_number = "";
            }
            String email;
            if(hashMap.get("email")!=null){
                email = hashMap.get("email").toString();
            }else {
                email = "";
            }
            String created_time;
            if(hashMap.get("created_time")!=null){
                created_time = hashMap.get("created_time").toString();
            }else {
                created_time = "";
            }
//            System.out.println(customer);
            FacebookLeadPush facebookLeadPush = new FacebookLeadPush(page_id,lead_id,form_id,customer,webform_id,full_name,when_are_you_planning_to_make_a_purchase,phone_number,email,created_time);
//            System.out.println(facebookLeadPush.customer);
            new GenericDB<FacebookLeadPush>().addRow(Facebookleadpush.FACEBOOKLEADPUSH,facebookLeadPush);
        }

    }

    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> finalLeadDataList = new ArrayList<HashMap<String, String>>();
        ArrayList<HashMap<String, String>> leadsDataList = new ArrayList<HashMap<String, String>>();
        String[][] excelData = ReadExcelFileData.getExcelData("~/facebookdatafetch/src/main/java/facebook/lead/push/by/pageid/docs/pageid_data.xlsx");
        String[] pageId = ReadExcelFileData.getPageIdArray(excelData);
//        for(int i=0; i< pageId.length; i++){
//            System.out.println(pageId[i]);
//        }
//        String[] pageId = {"3446574535223432"};
//        HashMap<String, String> pageAccessToken = GetPageAccessToken.getPageAccessTokenFromWebsite(pageId);
        HashMap<String, String> pageAccessToken = GetPageAccessToken.getPageAccessTokenFromSavedFile(pageId);
//        System.out.println(pageAccessToken);
        for(int i=0; i<pageAccessToken.size(); i++) {
            String facebookUrl = domainUrl + pageId[i] + "?fields=leadgen_forms{id,leads}&access_token=" +pageAccessToken.get(pageId[i]);
            System.out.println(facebookUrl);
            FacebookApiResponse facebookApiResponse = new FacebookApiResponse();
            leadsDataList = facebookApiResponse.getJsonResponse(facebookUrl, pageId[i], pageAccessToken.get(pageId[i]));
            int count = 0;
            for (HashMap hashMap : leadsDataList){
                System.out.println(count +".) "+hashMap);
                count++;
            }
            insertDataIntoDatabase(leadsDataList);
            finalLeadDataList.addAll(leadsDataList);
            leadsDataList.clear();
        }
        System.out.println("#################################\n#################################\n#################################");
//        for(HashMap hashMap : finalLeadDataList){
//            System.out.println(hashMap.get("customer").toString());
//        }
        System.out.println("#################################\n#################################\n#################################");
        System.out.println("Size of final list : " + finalLeadDataList.size());
//        insertDataIntoDatabase(finalLeadDataList);
        System.out.println("#################################\n#################################\n#################################");
    }

}
