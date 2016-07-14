package monkeylearn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.monkeylearn.MonkeyLearnException;

public class GetSentiment {
	
public static void main(String args[]) throws FileNotFoundException, MonkeyLearnException, IOException, SQLException, InterruptedException{
		
		
		WriteToFile topic=new WriteToFile();
		int count=0;
		
		File file = new File("/home/storm/Documents/datasetsTopics/count.txt");
	      // creates the file
	      file.createNewFile();
	      // creates a FileWriter Object
	      FileWriter writer = new FileWriter(file); 
	      // Writes the content to the file
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Date date = new Date();
	     
		
		String newsip="/home/storm/Documents/datasetsTopics/news";
		String newstablename="TweetSentimentNews";
		
		String personsip="/home/storm/Documents/datasetsTopics/persons";
		String presonstablename="tweetsentimentpersons";
		
		String companyip="/home/storm/Documents/datasetsTopics/companies";
		String companyop="tweetsentimentcompany";
		
		String politicsip="/home/storm/Documents/datasetsTopics/politics";
		String Politicstablename="tweetsentimentpolitics";
		
		topic.SetPathforSentiment(newsip, newstablename);
		topic.SetPathforSentiment(personsip, presonstablename);
		topic.SetPathforSentiment(companyip, companyop);
		topic.SetPathforSentiment(politicsip, Politicstablename);
		
	 count++;
	 writer.write( "Date and time of update" +date+ "with the count" +count); 
     writer.flush();
     writer.close();
     sleepMinutes(120);// DO SOMETHING EVERY HOUR
    
}
private static void sleepMinutes(int minutes) {
    try {
        System.out.println("Sleeping for " + minutes);
        Thread.sleep(minutes * 1000*60);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}
