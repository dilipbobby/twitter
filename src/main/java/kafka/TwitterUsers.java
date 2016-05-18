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

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
//import oauth.signpost.exception.OAuthCommsunicationException;
//import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
//import twitter4j.JSONObjectType;

public class TwitterUsers {
	static OAuthConsumer consumer;
	static HttpResponse response;
	static String m;
	// giving the access twitter permissions

	static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";

	public static void main(String[] args) throws Exception {


		String search_link = "https://api.twitter.com/1.1/search/tweets.json?include_entities=true&count=1&q=heymailme143";
		String timeline_link = "https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name=vanajapotla";
		// String
		// search_link="https://api.twitter.com/1.1/statuses/user_timeline.json?count=4&screen_name=heymailme143";
		

		TwitterUsers u = new TwitterUsers();

		Integer doesntmatchcount = 0;

		String a = u.combine(search_link);

		// KeyedMessage<String, String> data = new KeyedMessage<String,
		// String>("twitter",a);
		System.out.println(a);

		JSONObject search_json = new JSONObject(a);
		System.out.println("SEARCH +++++++++++++++++++" + a);

		// System.out.println("This is search link: "+search_json);

		JSONObject mainobj = new JSONObject(a);
		JSONArray mainstatus = mainobj.getJSONArray("statuses");
		
			JSONObject tweetobj = mainstatus.getJSONObject(0);
			System.out.println( tweetobj);
			JSONObject tweet_user=tweetobj.getJSONObject("user");
			System.out.println(""+tweet_user);
		
			String TweetUserScrName=tweet_user.getString("screen_name");
			String TweetUseruserName=tweet_user.getString("name");
			String TweetUserId=tweet_user.getString("id_str");//charvar
			String TweetUserLoc=tweet_user.getString("location");
			String TweetUserDescription=tweet_user.getString("description");
			
			

	}// main close

	public String combine(String link) throws OAuthMessageSignerException, OAuthExpectationFailedException,
			OAuthCommunicationException, ClientProtocolException, IOException {

		System.out.println(link);

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
		consumer.setTokenWithSecret(AccessToken, AccessSecret);

		HttpGet request = new HttpGet(link);

		// search

		consumer.sign(request);

		HttpClient client = new DefaultHttpClient();

		HttpResponse response = client.execute(request);

		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("OK" + statusCode);
		return m = IOUtils.toString(response.getEntity().getContent());

	}

	// method
	public String Getreplytxt(String replyobj, String replyid) throws JSONException {

		JSONArray reply = new JSONArray(replyobj);
		String replytxt = null;
		for (int i = 0; i < reply.length(); i++) {
			JSONObject tweetobj = reply.getJSONObject(i);
			String tweetid = tweetobj.getString("id_str");
			System.out.println("Tweetid" + tweetid);
			if (tweetid.equals(replyid)) {

				System.out.println("found the tweet ID of Reply user");
				System.out.println("TWEET ID AND REPLY ID " + replyid + "" + tweetid);
				replytxt = tweetobj.getString("text");
				break;

			} // if close

		} // for close

		return replytxt;
	}

} // class close
