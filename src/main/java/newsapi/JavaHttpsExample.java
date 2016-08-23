package newsapi;

import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
//import java.net.HttpURLConnection;
 
public class JavaHttpsExample
{
  @SuppressWarnings("restriction")
public static void main(String[] args)
  throws Exception
  {
    String httpsURL = "https://news.google.com/news?q=apple&output=rss";
    //http://ps.moreover.com/page?query=hillaryclinton&n=200&client_id=%20aadhyanalytics%20_set_mi26
    URL myurl = new URL(null,httpsURL, new sun.net.www.protocol.https.Handler());
    HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
    InputStream ins = con.getInputStream();
    InputStreamReader isr = new InputStreamReader(ins);
    BufferedReader in = new BufferedReader(isr);
 
    String inputLine;
 
    while ((inputLine = in.readLine()) != null)
    {
      System.out.println(inputLine);
    }
 
    in.close();
  }
}