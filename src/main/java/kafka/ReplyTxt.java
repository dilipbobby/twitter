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
import twitter4j.JSONException;
import twitter4j.JSONObject;
//import twitter4j.JSONObjectType;

public class ReplyTxt {
	static OAuthConsumer consumer;
	static HttpResponse response;
	static String m;
	// giving the access twitter permissions

	static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";

	public static void main(String[] args) throws Exception {

		// kafka connection establishment

		/*
		 * Properties props = new Properties();
		 * props.put("metadata.broker.list", "localhost:9092");
		 * props.put("serializer.class", "kafka.serializer.StringEncoder");
		 * props.put("partitioner.class", "com.aail.kafka.SimplePartitioner");
		 * props.put("request.required.acks", "1");
		 * props.put("retry.backoff.ms", "150");
		 * props.put("message.send.max.retries","1");
		 * props.put("topic.metadata.refresh.interval.ms","0");
		 * 
		 * ProducerConfig config = new ProducerConfig(props);
		 * 
		 * 
		 * final Producer<String, String> producer = new Producer<String,
		 * String>(config);
		 */

		String search_link = "https://api.twitter.com/1.1/search/tweets.json?include_entities=true&count=1&q=heymailme143";
		String timeline_link = "https://api.twitter.com/1.1/statuses/user_timeline.json?count=4&screen_name=heymailme143";
		// String
		// search_link="https://api.twitter.com/1.1/statuses/user_timeline.json?count=4&screen_name=heymailme143";
		String Screen_name = null;
		String search_tweet_text = null;
		String replyuser = null;
		String replyid = null;
		JSONObject statuses_obj = null;
		JSONObject user_entities = null;
		JSONObject search_entities = null;
		JSONObject mentionuserobj = null;
		JSONArray media = null;
		JSONObject mediobj = null;
		JSONObject mentionuserobjq = null;
		JSONObject mentionuserobjs = null;

		ReplyTxt u = new ReplyTxt();

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
		for (int i = 0; i < mainstatus.length(); i++) {
			JSONObject tweetobj = mainstatus.getJSONObject(i);
			String reply_string_id = tweetobj.getString("in_reply_to_status_id_str");
			String reply_string_scname = tweetobj.getString("in_reply_to_screen_name"); // in_reply_to_screen_name

			System.out.println(reply_string_id);
			System.out.println(reply_string_scname);

			String usertimeline_link = "https://api.twitter.com/1.1/statuses/user_timeline.json?count=200&screen_name="
					+ reply_string_scname;

			if (!reply_string_id.isEmpty() && !reply_string_id.equals("null"))
				;
			{
				String replyuser_obj = u.combine(usertimeline_link);

				// System.out.println(replyuser_obj);
				String replytxt = u.Getreplytxt(replyuser_obj, reply_string_id);
				System.out.println("Great day");
				System.out.println("reply txt" + replytxt);
			}

		} // for close

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

			} // if close

		} // for close

		return replytxt;
	}

} // class close
