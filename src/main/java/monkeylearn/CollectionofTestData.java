package monkeylearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monkeylearn.MonkeyLearnException;

import aail.twitter.postgersInsert;
import sentiment.nepsnu.TweetManager;

public class CollectionofTestData {
	
	public static void main(String args[]) throws MonkeyLearnException{
		int count =0;
        int totalcalls=48801;
		//Reading topic names from file
		
		try (BufferedReader br = new BufferedReader(new FileReader("/home/storm/Documents/datasetsTopics/newstopics")))
		{
			String Line;
			// String sentimentarry;
			while ((Line = br.readLine()) != null) {
				System.out.println(Line);//print line
				String name=Line;
		         ArrayList<String> tweets = TweetManager.getTweets(name);
		         for(String tweet : tweets){
		        	 System.out.println("Tweet-text "+tweet);
			        	if(count <= totalcalls){

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
		        	 psqlInsert.psqlInsertsNews(tweet, probability, tweetsentiment);
			        	
		        	 }//if close
		         }//for loop

				
			}//while close

		} catch (IOException e) {
			e.printStackTrace();
		} //first file exception 
		
		
	}//main method

}//main class
