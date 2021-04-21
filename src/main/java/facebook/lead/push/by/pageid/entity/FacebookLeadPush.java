package facebook.lead.push.by.pageid.entity;

public class FacebookLeadPush {
    public String page_id;
    public String lead_id;
    public String form_id;
    public String customer;
    public String webform_id;
    public String full_name;
    public String when_are_you_planning_to_make_a_purchase;
    public String phone_number;
    public String email;
    public String created_time;

    public FacebookLeadPush(String page_id, String lead_id, String form_id, String customer, String webform_id, String full_name, String when_are_you_planning_to_make_a_purchase, String phone_number, String email, String created_time) {
        this.page_id = page_id;
        this.lead_id = lead_id;
        this.form_id = form_id;
        this.customer = customer;
        this.webform_id = webform_id;
        this.full_name = full_name;
        this.when_are_you_planning_to_make_a_purchase = when_are_you_planning_to_make_a_purchase;
        this.phone_number = phone_number;
        this.email = email;
        this.created_time = created_time;
    }

    public static void main(String[] args) {
//        {
//        page_id=107890267741685,
//        created_time=2021-03-08T08:46:41+0000,
//        full_name=test,
//        when_are_you_planning_to_make_a_purchase?=0-1_month,
//        form_id=3145369725729059,
//        phone_number=+919999999999,
//        lead_id=1373728129647200,
//        email=test@gmail.com}

//        FacebookLeadPush facebookLeadPush = new FacebookLeadPush("107890267741685","","","","","","","","","");
//        System.out.println(facebookLeadPush.page_id);
    }
}
