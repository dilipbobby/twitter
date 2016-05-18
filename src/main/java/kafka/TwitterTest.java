package kafka;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;

import oauth.signpost.exception.OAuthMessageSignerException;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.api.TweetsResources;


public class TwitterTest {
	static OAuthConsumer consumer;
	static HttpResponse response;
	static String m;
	// giving the access twitter permissions

	static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";

	public static void main(String[] args) throws Exception {

		try (BufferedReader br = new BufferedReader(new FileReader("/home/storm/Desktop/replay.json")))
		{

			String Line;

			while ((Line = br.readLine()) != null) {
				//System.out.println(Line);
				JSONArray tweetarray=new JSONArray(Line);
				//System.out.println(tweetarray);
				JSONObject tweetobj=tweetarray.getJSONObject(0);
				//System.out.println(tweetobj);
				
				//twitter tweets
				
				
				
				
				//--------------------TWEET USERS ----------------------//
				
				 //replay users
		
					String in_reply_to_status_id_str=tweetobj.getString("in_reply_to_status_id_str");
				    String  in_reply_to_user_id_str=tweetobj.getString("in_reply_to_user_id_str");
				   
				    if(in_reply_to_status_id_str != null && in_reply_to_user_id_str != null){
				    	  
				    	  in_reply_to_user_id_str=tweetobj.getString("in_reply_to_user_id_str");
		   			    	

				    	  String in_reply_to_screen_name=tweetobj.getString("in_reply_to_screen_name");
				    	//  System.out.println(in_reply_to_screen_name);
				    	  
						}
				//tweet user
				    
				   JSONObject tweetuser=tweetobj.getJSONObject("user");
				   //System.out.println(tweetuser);
			
				String tweet_username=tweetuser.getString("name");
				String tweet_user_scname=tweetuser.getString("screen_name");
				String tweet_user_location=tweetuser.getString("location");
				String tweet_user_description=tweetuser.getString("description");
				try{
				int tweet_user_utcofset=tweetuser.getInt("utc_offset");
				}catch(Exception e){
					int  tweet_user_utcofset=0;
					e.printStackTrace();
					}
				String tweet_user_timezone=tweetuser.getString("time_zone");
				String tweet_user_lang=tweetuser.getString("lang");
				String tweet_user_url=tweetuser.getString("url");
				try{
				String tweet_user_expanded_url=tweetuser.getString("expanded_url");
				}catch(Exception e ){
				String tweet_user_expanded_url="";
			//	e.printStackTrace();
				}//expanded url catch close
				
				String tweet_user_profile_image=tweetuser.getString("profile_image_url");

				String tweet_user_profile_background_image=tweetuser.getString("profile_background_image_url");

				String tweet_user_profile_bc_image_https=tweetuser.getString("profile_background_image_url_https");

				String tweet_user_profile_text_color=tweetuser.getString("profile_text_color");

				String tweet_user_profile_sidebar_fill_color=tweetuser.getString("profile_sidebar_fill_color");

				String tweet_user_use_backgeround_image=tweetuser.getString("profile_use_background_image");

				String tweet_user_background_color=tweetuser.getString("profile_background_color");

				String tweet_user_id=tweetuser.getString("id_str");
			    
			    ///usermentions 
			    JSONObject tweetentites=tweetobj.getJSONObject("entities");
			   			    JSONArray tweetmentions=tweetentites.getJSONArray("user_mentions");
			   			    for(int i=0;i<tweetmentions.length();i++){
			   			    	JSONObject tweetmentionobj=tweetmentions.getJSONObject(i);
				   			    //System.out.println(tweetmentionobj);
				   			    String mention_user_name=tweetmentionobj.getString("name");

				   			    String mention_user_sc_name=tweetmentionobj.getString("screen_name");
				   		       
				   			    String mention_user_id=tweetmentionobj.getString("id");

			   			    }

//retweetUsers
			   			    try{
			   			    JSONObject retweetobj=tweetobj.getJSONObject("retweeted_status");
			   			    JSONObject retweetuserobj=retweetobj.getJSONObject("user");
			   			
			   			    String Retweet_username=retweetuserobj.getString("name");
							String Retweet_user_id=tweetuser.getString("id_str");
                            String Retweet_user_scname=retweetuserobj.getString("screen_name");
			   			    System.out.println(Retweet_username);
			   			    
			   			 String RT_in_reply_to_status_id_str=retweetobj.getString("in_reply_to_status_id_str");
						    String  RT_in_reply_to_user_id_str=retweetobj.getString("in_reply_to_user_id_str");
						   
						    if(RT_in_reply_to_status_id_str != null && RT_in_reply_to_user_id_str != null){
						    	  
						    	  RT_in_reply_to_user_id_str=retweetobj.getString("in_reply_to_user_id_str");
				   			      String RT_in_reply_to_screen_name=retweetobj.getString("in_reply_to_screen_name");
						    	  System.out.println(RT_in_reply_to_screen_name);
						    	  
								}
  }catch(Exception e ){
			   			    	//e.printStackTrace();
			   			    }
			}//while close

		} catch (IOException e) {
			e.printStackTrace();
		} 
	

	}// main close

} // class close
