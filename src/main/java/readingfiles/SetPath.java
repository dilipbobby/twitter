package readingfiles;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monkeylearn.MonkeyLearnException;

public class SetPath {
	
	public static void SetPathforSentiment(String ipfilepath,String tablename) throws MonkeyLearnException, FileNotFoundException, IOException, SQLException, URISyntaxException {
	
		try (BufferedReader br = new BufferedReader(new FileReader(ipfilepath)))
			{
				String Line;
				System.out.println("OUTPUT TABLE NAME "+tablename);
				/*FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);*/
				// String sentimentarry;
				while ((Line = br.readLine()) != null) {
					System.out.println(Line);//print line
					String name=Line;
					ArrayList<String> tweets = TweetManager.getTweets(name);
					int tweetsoftopic=tweets.size();
					System.out.println("numbers of tweets of topic "+tweetsoftopic);
				  for(String tweet : tweets){
			        	   // MonLearn m=new MonLearn();
			        	DatumboxTwitterSenti s=new DatumboxTwitterSenti();    
			        	String sentimentarry =s.Sentiment(tweet);
				        	// System.out.println(sentimentarry);
				        	 JSONArray jarry=new JSONArray(sentimentarry);
				        	 JSONArray jarryx=jarry.getJSONArray(0);
			        		 JSONObject jobj=jarryx.getJSONObject(0);
				        	 String tweetsentiment=jobj.getString("label");	
					         System.out.println(tweet+","+tweetsentiment);
					         psqlInsertfields.insertdb(tablename, "'"+tweet+"'", "'"+tweetsentiment+"'");
							 System.out.println("Done");
					  }//for close
			        Date date2 = new Date();
			        System.out.println("Time "+date2.toString()+"Total tweets "+tweetsoftopic);
			        //bw.close();
				}//while close
} 
		catch (IOException e) {
			
		e.printStackTrace();
	}
	}
}
