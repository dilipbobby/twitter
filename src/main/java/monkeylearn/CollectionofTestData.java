package monkeylearn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monkeylearn.MonkeyLearnException;

import aail.twitter.postgersInsert;
import sentiment.nepsnu.TweetManager;

public class CollectionofTestData {
	
	public static void main(String args[]) throws MonkeyLearnException, IOException{
		int count =0;
        int totalcalls=48801;
		//Reading topic names from file
        File fout = new File("/home/storm/Documents/datasetsTopics/newstopics.txt");
    	FileWriter fos = new FileWriter(fout.getAbsoluteFile());
    	BufferedWriter bw = new BufferedWriter(fos);
		try (BufferedReader br = new BufferedReader(new FileReader("/home/storm/Documents/datasetsTopics/newstopics")))
		{
			String Line;
			// String sentimentarry;
			while ((Line = br.readLine()) != null) {
				System.out.println(Line);//print line
				String name=Line;
		        ArrayList<String> tweets = TweetManager.getTweets(name);
		        Set<String> uniqueSet = new HashSet<String>();
		         for(String tweet : tweets){
		        	// System.out.println("Tweet-text "+tweet);
		        	 uniqueSet.add(tweet);
			        	if(uniqueSet.contains(tweet)){
			        		MonLearn m=new MonLearn();
		        	 String sentimentarry =m.monklearn(tweet);
		        	// System.out.println(sentimentarry);
		        	 JSONArray jarry=new JSONArray(sentimentarry);
		        	 JSONArray jarryx=jarry.getJSONArray(0);
		        	 JSONObject jobj=jarryx.getJSONObject(0);
		        	 String tweetsentiment=jobj.getString("label");
		        	 //String probability=jobj.getString("probability");
		        	 count++;
		        	 bw.write(tweet+" "+" "+tweetsentiment);
		        	 
		        	 System.out.println("Done");
		        	 System.out.println(tweet+" "+" "+tweetsentiment);
			        }//if close
		         }//for loop
		         bw.close();
				}//while close
			} catch (IOException e) {
			e.printStackTrace();
		} //first file exception 
		
		
	}//main method
}//main class
