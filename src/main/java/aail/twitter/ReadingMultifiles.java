package aail.twitter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monkeylearn.MonkeyLearnException;

import monkeylearn.MonLearn;
import sentiment.nepsnu.TweetManager;

import java.io.File;

public class ReadingMultifiles {

public static void main(String[] args) throws IOException, MonkeyLearnException {
	        String target_dir = "/home/storm/Documents/datasetsTopics/";
	        int count =0;
	        int totalcalls=48801;
	        File dir = new File(target_dir);
	        File[] files = dir.listFiles();
	        for (File f : files) {
	            if(f.isFile()) {
	                BufferedReader inputStream = null;

	                try {
	                    inputStream = new BufferedReader(
	                                    new FileReader(f));
	                    String line;

	                    while ((line = inputStream.readLine()) != null) {
	                        System.out.println(line);
	                        String name=line;
	       		         ArrayList<String> tweets = TweetManager.getTweets(name);
	       		      for(String tweet : tweets){
	       		    	System.out.println("Tweet-text "+tweet);
			        	if(count <= totalcalls){
	       		    	//GetSentiment of the tweet_txt 
			        	MonLearn m=new MonLearn();
			        	 String sentimentarry =m.monklearn(tweet);
			        	 System.out.println(sentimentarry);
			        	 JSONArray jarry=new JSONArray(sentimentarry);
			        	 JSONArray jarryx=jarry.getJSONArray(0);
			        	 JSONObject jobj=jarryx.getJSONObject(0);
			        	 String tweetsentiment=jobj.getString("label");
			        	 String probability=jobj.getString("probability");
			        	 count++;
			        	 ///INSERT INTO DB
			        	 postgersInsert psqlInsert=new postgersInsert();
			        	// psqlInsert.psqlInserts(tweet, probability, tweetsentiment);
			        	}//if close 
	       		      }//LINE READ FOR CLOSE
	       		         
	                    }//READ LINE while CLOSE
	                }
	                finally {
	                    if (inputStream != null) {
	                        inputStream.close();
	                    }
	                }
	            }
	        }
	
}
}

