package newsapi;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.lang.*;
import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import java.net.HttpURLConnection;
import org.json.XML;

import com.google.gson.JsonObject;

public class GoogleFeed {
	private static final int BATCH_SIZE = 1;

	// public void GoogleRssSearch(String name) throws URISyntaxException,
	// HttpException, IOException{
	public static void main(String[] args) throws IOException, InterruptedException {

		JSONObject xmlJSONObj = null;
		while (true) {
			String[] name = { "BREXIT" };

			for (int k = 0; k < name.length; k++) {

				System.out.println(name[k]);
				String httpsURL = "http://news.google.com/news?pz=all&output=rss&num=30&q=" + name[k];
				URL myurl = new URL(null, httpsURL, new sun.net.www.protocol.https.Handler());
				HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
				InputStream ins = con.getInputStream();
				InputStreamReader isr = new InputStreamReader(ins);
				BufferedReader in = new BufferedReader(isr);
				 Thread.sleep(10000);
				System.out.println("waiting for 10 secs");
				String inputLine = null;
				while ((inputLine = in.readLine()) != null) {
					 //System.out.println("XML OUTPUT "+inputLine);
					try {
						int outputIndex = 0;
						xmlJSONObj = XML.toJSONObject(inputLine);
						//System.out.println(xmlJSONObj);
						List<Object> jsonObjects = new ArrayList<Object>();
						JSONObject jsonobject=xmlJSONObj;
						JSONObject RSSobj=null;
						JSONObject Channel=null;
						System.out.println("json object "+jsonobject);
						for (int z = 0; z < jsonobject.length(); z++) {
							JSONArray splitdata;
							RSSobj=jsonobject.getJSONObject("rss");
							Channel=RSSobj.getJSONObject("channel");
							splitdata = Channel.getJSONArray("item");
							System.out.println("SPILT DATA "+splitdata);
							for (int i = 0; i < splitdata.length(); i++) {
								jsonObjects.add(splitdata.get(i));
								// Flush if max objects per file has been
								// reached.
								if (jsonObjects.size() == BATCH_SIZE) {
									String filesplit = flushFile(jsonObjects, outputIndex);
									System.out.println("Single file split "+filesplit);
									jsonObjects.clear();
									outputIndex++;
								}
							} // json split 2 for close
							if (!jsonObjects.isEmpty()) {
								flushFile(jsonObjects, outputIndex);
							}

						} // json-split for close

					} catch (Exception e) {
						System.out.println(e);
					}

				} // while close

			} // for close
			//System.out.println("complete Google News Json Field" + xmlJSONObj);

		} /// while true close
	}// main close
	 public static String flushFile(List<Object> objects, int d) throws Exception {
	    	String output1=objects.toString();
	        return output1;

}
}// class close
