package monkeylearn;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import sentiment.nepsnu.TweetManager;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import com.monkeylearn.MonkeyLearnException;
 
public class CollectionNeu {
    public static void main( String[] args ) throws MonkeyLearnException, FileNotFoundException, JSONException {
 System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("/home/storm/Videos/sentiNetralNamo.txt")), true));

    	 String topic = "@FoxNews";
         ArrayList<String> tweets = TweetManager.getTweets(topic);
         CollectionNeu.Sentiment(tweets); //#Midyat
        }//for close
   
public static void Sentiment(ArrayList<String> tweets) throws MonkeyLearnException, JSONException{
	
	   for(String tweet : tweets){
      	 //tweet = tweet.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");
      	// System.out.println();
      	 //System.out.println(tweet);
      MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
      String moduleId = "cl_qkjxv9Ly";
      String[] textList = {tweet};
      MonkeyLearnResponse res = ml.classifiers.classify(moduleId, textList, true);
      System.out.println();
    //  System.out.println(res.arrayResult.get(0).toString()+" "+tweet);
      twitter4j.JSONArray sam=new twitter4j.JSONArray(res.arrayResult.get(0).toString());
   //   System.out.println(sam);
      twitter4j.JSONObject obj=sam.getJSONObject(0);
      String sentimentt=obj.getString("label");
      if(sentimentt.equals("neutral")){
      System.out.println(sentimentt+" "+tweet);
      }
}
	//return null;
}
}

