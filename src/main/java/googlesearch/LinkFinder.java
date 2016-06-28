package googlesearch;
import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class LinkFinder {
	
	public static void processPage(String URL) throws SQLException, IOException{
		
 
			//get useful information
			Document doc = Jsoup.connect("http://www.mit.edu/").get();
 
			if(doc.text().contains("research")){
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("mit.edu"))
					processPage(link.attr("abs:href"));
				
			}
		}
	
	public static void main(String[] args) throws SQLException, IOException {
		
		processPage("http://www.mit.edu");
	}
	}


	
