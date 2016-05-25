package googlesearch;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

////refernce ://http://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api


public class searcd {
	public static void main(String[] args) throws IOException {

	String google = "http://www.google.com/search?q=";
	//String search = "stackoverflow";
	String search = "hortonworks twitter offical account";
	String charset = "UTF-8";
	String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

	Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");

	for (Element link : links) {
	    String title = link.text();
	    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
	    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

	    if (!url.startsWith("http")) {
	        continue; // Ads/news/etc.
	    }

	
/*if(title.matches((match))){
System.out.println("Done the greate ");
}*/
System.out.println("Title: " + title);
System.out.println("URL: " + url);
	}
	}
}
