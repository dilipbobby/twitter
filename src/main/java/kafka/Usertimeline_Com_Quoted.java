package kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


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

public class Usertimeline_Com_Quoted {
	static OAuthConsumer consumer;
	 static HttpResponse response;
	 static String m;
	//giving the access twitter permissions 
	  private static final int BATCH_SIZE = 1;
	  	//static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	 	//static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	 	//static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	 	//static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";
	 	static	String ConsumerKey="El8589UFvPyT8MMxginumS8QO";
		static	String ConsumerSecret="CS71lglHYZj32kDqyndYBsV5JOq0W9VHEF8zPOBHcOO459BkWH";
		static	String AccessToken="3492111673-JRnQEAKF3ShUlYa8vLAy5IQwYZomaABxcFY0cCF";
		static	String AccessSecret="iCYUB23nFQ9NtuFXHZhWDYAfzQSZzn4bOfVbMkfKprmwz";
		//static JSONObject search_json=null;
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
		
		String search_link =  "https://api.twitter.com/1.1/search/tweets.json?include_entities=true&count=10&q=heymailme143";
		String timeline_link="https://api.twitter.com/1.1/statuses/user_timeline.json?count=10&screen_name=heymailme143";
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
		 
		 
		Usertimeline_Com_Quoted u = new Usertimeline_Com_Quoted();
		
		Integer doesntmatchcount=0;
		
		String a = u.combine(search_link);
		
		
		//KeyedMessage<String, String> data = new KeyedMessage<String, String>("twitter",a);
		System.out.println(a);
		
		JSONObject  search_json = new JSONObject(a);
		System.out.println("SEARCH +++++++++++++++++++"+search_json);
		
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
		String json=search_json.toString();
		//JSONObject jsonobject = new JSONObject(json);
        System.out.println(  "shoould work "+json);
       
		//************************************ split method on search_json ********************************************************
		
		

			    int outputIndex = 0;
			    List<Object> jsonObjects = new ArrayList<>();
	            JSONObject jsonobject= new JSONObject(json);
	            try{
	            System.out.println("PPPPPPPPPPPPPPPPPPPPPP"+jsonobject);
	            System.out.println("finaltest%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+search_json);
	            for(int z=0;z<jsonobject.length();z++){
	            	JSONArray splitdata;
	            splitdata=	jsonobject.getJSONArray("statuses");
	            
	            	for (int i = 0; i < splitdata.length(); i++) {
	                jsonObjects.add(splitdata.get(i));
	                System.out.println("The Man sep"+splitdata);
	                // Flush if max objects per file has been reached.
	                if (jsonObjects.size() == BATCH_SIZE) {
	                    String filesplit=flushFile(jsonObjects, outputIndex);
	                    
	                    //***********************************KAFKA SEND STATEMENTS ***************************************************
	                    
	                 
	                    
	                    
	                    
	                    //********************************************************************************************
	                    System.out.println("OUT OF EACH FILE "+filesplit);
	                    jsonObjects.clear();
	                    outputIndex++;
	                }
	                }
	            
		if (!jsonObjects.isEmpty()) {

            flushFile(jsonObjects, outputIndex);

        }//if lose
		}//for json object close
           

	            }//try close for object method 
	            catch(Exception e ){ 
	            	
	            	e.printStackTrace();
	            	}
		//*************************************************************************************************************************

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
	 	 public static String flushFile(List<Object> objects, int d) throws Exception {
		    	
		    	String output1=objects.toString();
		        return output1;

}

	
} // class close

