package monkeylearn;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monkeylearn.MonkeyLearnException;

import sentiment.nepsnu.TweetManager;

public class WriteToFile {
	
	public static void SetPathforSentiment(String ipfilepath,String tablename) throws MonkeyLearnException, FileNotFoundException, IOException, SQLException {
	//	String path=opfilepath;
		//File file = new File(opfilepath);
		//System.out.println(file);
		
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
				
			        Set<String> uniqueSet = new HashSet<String>();
			        Date date = new Date();
			        System.out.println("Time "+date.toString()+"Total tweets "+tweetsoftopic);
			        File file = new File("/home/storm/Documents/datasetsTopics/count.txt");
					FileWriter fileWriter = new FileWriter(file);
					PrintWriter printWriter = new PrintWriter(fileWriter);
					printWriter.print("Time "+date.toString()+"Total tweets "+tweetsoftopic);
					
			        for(String tweet : tweets){
			        	uniqueSet.add(tweet);
			        	if(uniqueSet.contains(tweet)){
			        		MonLearn m=new MonLearn();
				        	 String sentimentarry =m.monklearn(tweet);
				        	// System.out.println(sentimentarry);
				        	 JSONArray jarry=new JSONArray(sentimentarry);
				        	 JSONArray jarryx=jarry.getJSONArray(0);
			        		 JSONObject jobj=jarryx.getJSONObject(0);
				        	 String tweetsentiment=jobj.getString("label");	
					         System.out.println(tweet+","+tweetsentiment);
					         psqlInsertfields.insertdb(tablename, "'"+tweet+"'", "'"+tweetsentiment+"'");
							 System.out.println("Done");
					        //TweetWriter.SentiWriter(file, tweet, tweetsentiment);
							 //break;
				        }//if close
			        	else{System.out.println("MISSED OUT THE TWEET");}
			        }//for close
			        Date date2 = new Date();
			        System.out.println("Time "+date2.toString()+"Total tweets "+tweetsoftopic);
			        printWriter.print("Time "+date2.toString()+"Total tweets "+tweetsoftopic);
			        fileWriter.flush();
					fileWriter.close();
			       //bw.close();
				}//while close
} 
		catch (IOException e) {
			
		e.printStackTrace();
	}//catch s 
	/*String Personspath="/home/storm/Documents/datasetsTopics/Personspath.txt";
			try (BufferedReader brpersons = new BufferedReader(new FileReader("/home/storm/Documents/datasetsTopics/persons")))
			{
				String PersonLine;
				// String sentimentarry;
				while ((PersonLine = brpersons.readLine()) != null) {
					System.out.println("This is persons "+PersonLine);//print line
					String Personname=PersonLine;
					ArrayList<String> tweets = TweetManager.getTweets(Personname);
			        Set<String> uniqueSetp = new HashSet<String>();
			        for(String tweet : tweets){
			        	uniqueSetp.add(tweet);
			        	if(uniqueSetp.contains(tweet)){
			        		MonLearn m=new MonLearn();
				        	 String sentimentarry =m.monklearn(tweet);
				        	// System.out.println(sentimentarry);
				        	 JSONArray jarry=new JSONArray(sentimentarry);
				        	 JSONArray jarryx=jarry.getJSONArray(0);
			        		 JSONObject jobj=jarryx.getJSONObject(0);
				        	 String tweetsentiment=jobj.getString("label");	
					         System.out.println(tweet+","+tweetsentiment);
					         TweetWriter.SentiWriter(Personspath, tweet, tweetsentiment);
				        }//if close
			        }//for close
			}//person while close
} catch (IOException e) {
	e.printStackTrace();
}//person catch close 
			String compath="/home/storm/Documents/datasetsTopics/companiespath.txt";
			try (BufferedReader brpersons = new BufferedReader(new FileReader("/home/storm/Documents/datasetsTopics/compnies")))
			{
				String comLine;
				// String sentimentarry;
				while ((comLine = brpersons.readLine()) != null) {
					System.out.println("This is persons "+comLine);//print line
					String Personname=comLine;
					ArrayList<String> tweets = TweetManager.getTweets(Personname);
			        Set<String> uniqueSetc = new HashSet<String>();
			        for(String tweet : tweets){
			        	uniqueSetc.add(tweet);
			        	if(uniqueSetc.contains(tweet)){
			        		MonLearn m=new MonLearn();
				        	 String sentimentarry =m.monklearn(tweet);
				        	// System.out.println(sentimentarry);
				        	 JSONArray jarry=new JSONArray(sentimentarry);
				        	 JSONArray jarryx=jarry.getJSONArray(0);
			        		 JSONObject jobj=jarryx.getJSONObject(0);
				        	 String tweetsentiment=jobj.getString("label");	
					         System.out.println(tweet+","+tweetsentiment);
					         TweetWriter.SentiWriter(compath, tweet, tweetsentiment);
				        }//if close
			        }//for close
			}//person while close
} catch (IOException e) {
	e.printStackTrace();
}//person catch close */
			
			
//System.out.println("Done");
	}//main close
	
}//class close 


