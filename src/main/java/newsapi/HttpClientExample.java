package newsapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientExample {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		/*HttpClientExample http = new HttpClientExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();*/

	//}

	// HTTP GET request
	///private void sendGet() throws Exception {
String url="http://ps.moreover.com/page?query=hillaryclinton&n=200&client_id=%20aadhyanalytics%20_set_mi26";
	//String url = "http://news.google.com/news?q=india&output=rss";
		//String url = "https://search.yahoo.com/search;?p=apple&fr2=sb-top-search&fr=sfp-tts-s";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}

	

}