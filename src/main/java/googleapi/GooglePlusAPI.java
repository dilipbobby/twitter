package googleapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Map;
 
import com.google.gson.Gson;
 
/**
 * Connect to the Google Plus API and download data.
 *
 * @author Shane Chism (schism at acm dot org)
 */
public class GooglePlusAPI {
     
    
    
    private static final String API_KEY = "AIzaSyBHgQZ8EOxZDIXjCb1JayugSw7S4bL98j0";
     
  
   
    public GPObject_PublicUser get( String UID ){
         
        // Initialize our return value
        String json = "";
        GPObject_PublicUser result = null;
         
        try {
             
            // Open a connection to the URL at our URI API request string
            URLConnection gpAPI = new URL( "https://www.googleapis.com/plus/v1/people/" + UID + "?key=" + API_KEY ).openConnection();
            // Declare an HTTPURLConnection resource, used to get the response code (and in the error case, evaluate)
            HttpURLConnection gpConnection = ( ( HttpURLConnection ) gpAPI );
             
            // Determine if the request was a success (Code 200)
            if( gpConnection.getResponseCode() != 200 ){
                // Request was not successful, pull error message from the server
                BufferedReader reader = new BufferedReader( new InputStreamReader( gpConnection.getErrorStream() ) );
                 
                String input;
                while( ( input = reader.readLine() ) != null )
                    json += input;
                 
                reader.close();
                // Generate a custom exception that parses the JSON output and displays to the user
                throw new GooglePlusAPIException( json );
            }
             
            // Get the response text from the server
            BufferedReader reader = new BufferedReader( new InputStreamReader( gpAPI.getInputStream() ) );
             
            String input;
            while( ( input = reader.readLine() ) != null )
                json += input;
             
            reader.close();
             
            // Convert the JSON request to our class object
            Gson gson = new Gson();
            return gson.fromJson( json, GPObject_PublicUser.class );
             
        }catch( GooglePlusAPIException e ){
            //e.show();
        }catch( Exception e ){
            System.out.println( "Google+ API Fatal Error: " + e.getMessage() );
        }
         
        return result;
    }
     
    /**
     * Public User Object storing the result of a generic Google Plus API request:
     * <pre>https://www.googleapis.com/plus/v1/people/[USER_ID]?key=[API_KEY]</pre>
     * This is a Google Plus API Object (demonstrating JSON conversion and object properties).
     * 
     * @see <a href="http://goo.gl/K4lNJ" target="_blank">Google+ API Documentation (attributes explained)</a>
     * @see <a href="http://goo.gl/uYtK4" target="_blank">Sample Google+ API Request</a>
     */
    public class GPObject_PublicUser implements GP_PublicUser {
 
        /*
         * These are cases where the result is just a string, no special data structure is needed.
         * Example: { "displayName": "Jane Smith" }
         */
        private String kind;
        private String id;
        private String displayName;
        private String tagline;
        private String gender;
        private String aboutMe;
        private String url;
         
        /*
         * The Java variable type needs to be correct for the values JSON is returning back to us.
         * We know from the JSON string that "image," for example, looks like this:
         * "image": { "url": "https://server.googleusercontent.com/.../photo.jpg" }
         * As such, we need to make the image variable type match that. An associative array will work nicely.
         */
        private Map<String,String> image;
         
        private Collection<Map<String,String>> urls;
        private Collection<Map<String,String>> organizations;
        private Collection<Map<String,String>> placesLived;
         
        public String getKind(){ return kind; }
        public String getId(){ return id; }
        public String getDisplayName(){ return displayName; }
        public String getTagline(){ return tagline; }
        public String getGender(){ return gender; }
        public String getAboutMe(){ return aboutMe; }
        public String getURL(){ return url; }
         
        public Map<String,String> getImage(){ return image; }
        public Collection<Map<String,String>> getURLs(){ return urls; }
        public Collection<Map<String,String>> getOrganizations(){ return organizations; }
        public Collection<Map<String,String>> getPlacesLived(){ return placesLived; }
    }
}
