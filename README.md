# FacebookDataFetch
  In this project I have implemented, a java code to send GET request to the facebook using the access token I obtained from Facebook using the GraphAPI. Using the   project we can fetch facebook profile data, facebook pages data, facebook ads data and much more that is present on the facebookt account. We can get simple data   such as friend list to complex data such as the facebook ads lead data.
  
## Implementations in the Project
  * Using the temporary user access_token, client_id, client_secret we send a get request to get the permanent user access_token. 
  * Read Excel sheet data containing the list of page_id for the ads campaigns.
  * Construct URL to fetch the leads data of the campaign.
  * Send HTTP request to facebook.com to get the results we need.
  * Using the permanent user access token we can get the facebook leads data for our campaigns.
  * Process the JSON data received from facebook, and process the data using JSON Objects and store it in a local database.
  * We can then apply trigger to POST this data into the server.

## Tech Stack
  * Java
  * Spring MVC
  * PostgreSQL
  * Jetty server
  * Facebook Graph API
  * Maven Dependencies
  * JSON 
  * JOOQ
  * Socket Programming
