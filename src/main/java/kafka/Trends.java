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


public class Trends {
	static OAuthConsumer consumer;
	static HttpResponse response;
	static String m;
	static int requestcount = 0;
	// giving the access twitter permissions

	static String ConsumerKey = "61XYsCg4vbMVFMGzUq8jQucFD";
	static String ConsumerSecret = "KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U";
	static String AccessToken = "3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY";
	static String AccessSecret = "Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR";

	public static void main(String[] args) throws Exception {
		
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
		consumer.setTokenWithSecret(AccessToken, AccessSecret);
		HttpGet request = new HttpGet(
				"https://api.twitter.com/1.1/trends/place.json?id=2487956");

		consumer.sign(request);

		HttpClient client = new DefaultHttpClient();

		HttpResponse response = client.execute(request);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			requestcount++;
			System.out.println("requestcount:" + requestcount);

		}
		String m = IOUtils.toString(response.getEntity().getContent());
 System.out.println(m);

	}

} // class close

