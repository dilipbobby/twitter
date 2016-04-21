package kafka;



import java.io.IOException;
import java.util.Properties;

/*import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
*/
//import java.io.IOException;
//import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/*import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;*/
//import kafka.javaapi.producer.Producer;
//import kafka.producer.ProducerConfig;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
//import oauth.signpost.exception.OAuthCommsunicationException;
//import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
//import twitter4j.JSONObjectType;

public class UserTimeline_combine {
	static OAuthConsumer consumer;
	 static HttpResponse response;
	 static String m;
	//giving the access twitter permissions 

	 static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	 	static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	 	static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	 	static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";
	  
	 	public static void main(String[] args) throws Exception{
		
	 		//kafka connection establishment
	 		  
			/*Properties props = new Properties();
		    props.put("metadata.broker.list", "localhost:9092");
		    props.put("serializer.class", "kafka.serializer.StringEncoder");
		    props.put("partitioner.class", "com.aail.kafka.SimplePartitioner");
		    props.put("request.required.acks", "1");
			props.put("retry.backoff.ms", "150");
			props.put("message.send.max.retries","1");
			props.put("topic.metadata.refresh.interval.ms","0");
			
			ProducerConfig config = new ProducerConfig(props);

			
		   final Producer<String, String> producer = new Producer<String, String>(config);
		   */
		
		String search_link =  "https://api.twitter.com/1.1/search/tweets.json?include_entities=true&count=1&q=heymailme143";
		String timeline_link="https://api.twitter.com/1.1/statuses/user_timeline.json?count=4&screen_name=heymailme143";
		//String search_link="https://api.twitter.com/1.1/statuses/user_timeline.json?count=4&screen_name=heymailme143";
		String Screen_name=null;
		String search_tweet_text=null;
		String replyuser=null;
		String replyid=null;
		JSONObject statuses_obj=null;
		JSONObject user_entities=null;
		JSONObject search_entities=null;
		JSONObject mentionuserobj=null;
		JSONArray media=null;
		JSONObject mediobj=null;
		JSONObject mentionuserobjq=null;
		JSONObject mentionuserobjs=null;
		 
		UserTimeline_combine u = new UserTimeline_combine();
		
		Integer doesntmatchcount=0;
		
		String a = u.combine(search_link);
		
		
		//KeyedMessage<String, String> data = new KeyedMessage<String, String>("twitter",a);
		System.out.println(a);
		
		JSONObject search_json = new JSONObject(a);
		System.out.println("SEARCH +++++++++++++++++++"+a);
		
	//	System.out.println("This is search link: "+search_json);
		
		JSONArray statuses = search_json.getJSONArray("statuses");
		for(int i=0;i<statuses.length();i++) {
			
			statuses_obj = statuses.getJSONObject(i);
			String search_created_at = statuses_obj.getString("created_at");
			String search_tweet_id = statuses_obj.getString("id");
		    search_tweet_text = statuses_obj.getString("text");
	       
			JSONObject search_user = statuses_obj.getJSONObject("user");
	        Screen_name=search_user.getString("screen_name");
	        search_entities=statuses_obj.getJSONObject("entities");
	       // System.out.println("This the search dam ture"+search_entities);
	       
	        
	        //USER MENTIONS USERS DETAILS 
	        JSONArray mentionedusers=search_entities.getJSONArray("user_mentions");
	        System.out.println("USERS MENTIONS OF THE Sreach API"+mentionedusers);
	        for(int d=0;d<mentionedusers.length();d++){
	        	 mentionuserobj=mentionedusers.getJSONObject(d);
	        	 String mentioneuserid=mentionuserobj.getString("id_str");
	        	 if(mentioneuserid != null){
	        	String mentioneuser=mentionuserobj.getString("screen_name");
	        	System.out.println("MENTIONED USER NAME"+mentioneuser);
	   		 String mentionuserslink= ("https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name="+mentioneuser);
             String mention=u.combine(mentionuserslink);
             System.out.println("MENTIONS USER TIMES "+mention);
             JSONArray mentionstatus=new JSONArray(mention);
    		 JSONObject mentionobj=mentionstatus.getJSONObject(0);
    		 JSONObject mentionsuserobj=mentionobj.getJSONObject("user");
    		 mentionuserobj.put("mention_user_details", mentionsuserobj);
    		 System.out.println("user added to the drian main"+mentionedusers);
             System.out.println("ADDEDED THE MENTION USER ");
	        	 }//else{System.out.println("NULL THE PUT PUT OF THE MENTIONS ");}
	        	
	        }
	        
			
			System.out.println("***************SEARCH_DATA******************************");
			System.out.println(search_created_at);
			System.out.println(search_tweet_id);
			System.out.println(search_tweet_text);
			System.out.println(Screen_name);
		//	System.out.println("***************SEARCH_DATA_END***************************");
			
		//!Player1.equals("rock") 	//reply-to-user details 
			replyid=statuses_obj.getString("in_reply_to_status_id_str");
			replyuser=statuses_obj.getString("in_reply_to_screen_name");
			

		//replyid != null//!replyid.equals("null");
	if(replyid != null){
		 String user_timelineofreplyuser= ("https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name="+replyuser);
		 String c=u.combine(user_timelineofreplyuser);
		 
		 System.out.println("User of reply To"+c);
		 JSONArray replyuserstatus=new JSONArray(c);
		 JSONObject replyobj=replyuserstatus.getJSONObject(0);
		 JSONObject replyuserobj=replyobj.getJSONObject("user");
	
		System.out.println("REPLY TO USER DETAILS "+replyuserobj);
		statuses_obj.put("in_reply_to_user_details", replyuserobj);
		System.out.println("changed the and added user details json obj"+search_json);
		System.out.println("***********************&&&&&&&&&&& &&&&& SCREEN NAME REPLY"+replyuser);
		System.out.println("*********************** &&&&&&$$$$ reply tweet id "+replyid);
		
		}//if replayuser close
	
	//search entites 
	} // statuses for close
		
		//Getting the quoted Tweet done by user

		String usertimeres=u.combine(timeline_link);
		System.out.println("USER TIME LINE "+usertimeres);
		JSONArray usertymline=new JSONArray(usertimeres);
		for(int y=0;y<usertymline.length();y++){
			
			JSONObject usertymobj=usertymline.getJSONObject(y);
			System.out.println("CHECK IT NOW OR NOT  "+usertymobj);
			String quotedstatuscheck=usertymobj.getString("is_quote_status");
			if(!quotedstatuscheck.isEmpty() && quotedstatuscheck.equals("true")){
				////***Getting user mention details in quoted status**/////
				JSONObject quotesstatus=usertymobj.getJSONObject("quoted_status");
				JSONObject quotesstatusentites=quotesstatus.getJSONObject("entities");
				JSONArray quotestatusmentions=quotesstatusentites.getJSONArray("user_mentions");
				   for(int d=0;d<quotestatusmentions.length();d++){
			        	 mentionuserobjs=quotestatusmentions.getJSONObject(d);
			        	 String mentioneuserids=mentionuserobjs.getString("id_str");
			        	 if(mentioneuserids != null){
			        	String mentioneusers=mentionuserobjs.getString("screen_name");
			        	System.out.println("MENTIONED USER NAME"+mentioneusers);
			   		 String mentionusersqlink= ("https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name="+mentioneusers);
		             String mentionus=u.combine(mentionusersqlink);
		             System.out.println("MENTIONS USER TIMES "+mentionus);
		             JSONArray mentionqstatus=new JSONArray(mentionus);
		    		 JSONObject mentionsobj=mentionqstatus.getJSONObject(0);
		    		 JSONObject mentionsuserobjs=mentionsobj.getJSONObject("user");
		    		 mentionuserobjs.put("mention_user_details", mentionsuserobjs);
		    		 System.out.println("user added to the drian main"+quotestatusmentions);
		             System.out.println("ADDEDED THE MENTION Q USER ");
			        	 }// if close or else{System.out.println("NULL THE PUT PUT OF THE MENTIONS ");}
			        	
			        }
				
				////****///
				System.out.println("QUOTED status "+quotedstatuscheck);
/////////////////////////////////////////
				JSONObject qutotedtweetentities=usertymobj.getJSONObject("entities");
				JSONArray mentionedusersq=qutotedtweetentities.getJSONArray("user_mentions");
		        System.out.println("USERS MENTIONS OF THE Sreach API"+mentionedusersq);
		        for(int d=0;d<mentionedusersq.length();d++){
		        	 mentionuserobjq=mentionedusersq.getJSONObject(d);
		        	 String mentioneuseridq=mentionuserobjq.getString("id_str");
		        	 if(mentioneuseridq != null){
		        	String mentioneuser=mentionuserobjq.getString("screen_name");
		        	System.out.println("MENTIONED USER NAME"+mentioneuser);
		   		 String mentionusersqlink= ("https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name="+mentioneuser);
	             String mentionq=u.combine(mentionusersqlink);
	             System.out.println("MENTIONS USER TIMES "+mentionq);
	             JSONArray mentionqstatus=new JSONArray(mentionq);
	    		 JSONObject mentionqobj=mentionqstatus.getJSONObject(0);
	    		 JSONObject mentionsuserobjq=mentionqobj.getJSONObject("user");
	    		 mentionuserobjq.put("mention_user_details", mentionsuserobjq);
	    		 System.out.println("user added to the drian main"+mentionedusersq);
	             System.out.println("ADDEDED THE MENTION Q USER ");
		        	 }// if close or else{System.out.println("NULL THE PUT PUT OF THE MENTIONS ");}
		        	
		        }//for close quoted mentions
			
////////////////////////////////////////
				statuses.put(usertymobj);
			}//quoted status check
			
		}//usertymline for close
		
		
		//searching for the external entities 
		String user_timeline = ("https://api.twitter.com/1.1/statuses/user_timeline.json?count=200&screen_name="+Screen_name);
		 //			HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=5syedjameer&count=10");

		String b = u.combine(user_timeline);
		
		JSONArray usertimeline_json = new JSONArray(b);
		
		System.out.println("This is usertimeline_link: "+usertimeline_json);
		
		for(int j=0;j<usertimeline_json.length();j++) {
		
		JSONObject usertimeline_obj = usertimeline_json.getJSONObject(j);
		
		String usertimeline_created_at = usertimeline_obj.getString("created_at");
		String usertimeline_tweet_id = usertimeline_obj.getString("id");
		String usetimeline_tweet_text = usertimeline_obj.getString("text");
		
	
		user_entities =usertimeline_obj.getJSONObject("entities");
		
	//	System.out.println("Need this add " +user_entities);
	try{
		 media =user_entities.getJSONArray("media");
		mediobj=media.getJSONObject(0);
		System.out.println("THIS USER MEDIA  "+media);
	}catch(Exception e){System.out.println("NO MEDIA FOUND PLEASE SEE "+e);}
		
		System.out.println("**********************USERTIMELINE_DATA*******************************");
		System.out.println(usertimeline_created_at);
		System.out.println(usertimeline_tweet_id);
		
		System.out.println("***************user and search tweets****************");
	
		System.out.println(usetimeline_tweet_text);
		System.out.println(search_tweet_text);

	     
		if(!search_tweet_text.isEmpty() && search_tweet_text.equals(usetimeline_tweet_text)) { 

			System.out.println("Match found trying to add ex-entities if obj found in the usertimeline obj");
		
			try {
			JSONObject user_extended_entities = usertimeline_obj.getJSONObject("extended_entities");
			System.out.println("USertimeline extended_entities: "+user_extended_entities);
			
			statuses_obj.put("extended_entities",user_extended_entities );
			search_entities.put("media", media);
			//search_entities.put("jaya", mediobj);
			
			System.out.println("Added the founded ex-entities to the search json %%%%%%%%%%%%%%   "+statuses_obj);
	
			}catch(Exception e){System.out.println("No ex_entities are founded to add and printing the exception"+e);
		}
							
		} //if close
				
		else{
			
		//	producer.send(data);
			System.out.println(" tweet doesn't match and no ex-entities are added to search json");
			System.out.println("SEND THE DATA");
			doesntmatchcount++;
			System.out.println("Count that doesn't match "+	doesntmatchcount);
			
		}//else close
		
		} //usertimeline_json for close*/
		System.out.println("Last one ouput "+search_json);

	}// main close
	 	
	 	public String combine(String link) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException {
	 		
	 		System.out.println(link);
	 		
	 		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey,ConsumerSecret);
			consumer.setTokenWithSecret(AccessToken, AccessSecret);
	 		
	 		HttpGet request = new HttpGet(link);
	 		
	 		//search
	 		        	        
	 			    consumer.sign(request);
	 		        
	 			    HttpClient client = new DefaultHttpClient();
	 		        
	 			    HttpResponse response = client.execute(request);
	 		        
	 			    int statusCode = response.getStatusLine().getStatusCode();
	 			    System.out.println("OK" +statusCode);
	 		        
	 		//	String m;
				return m = IOUtils.toString(response.getEntity().getContent());
			
	 	}
	
} // class close

