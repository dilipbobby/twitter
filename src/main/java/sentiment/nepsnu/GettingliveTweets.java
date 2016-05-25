package sentiment.nepsnu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class GettingliveTweets{
	
	static Map<String, Integer> NLPcount = new HashMap<String, Integer>(); 
	static Map<String, Integer> Gatecount = new HashMap<String, Integer>(); 
 //	Map Twittertager=new HashMap();

	public static void main(String[] args) throws InterruptedException  {
        String topic = "#WatchWBTV";
        ArrayList<String> tweets = TweetManager.getTweets(topic);
        NLP.init();
        NLP1.init();
        TwitterTager.init();
        TwitterTagger.init();
        for(String tweet : tweets) {
           
            System.out.println("STANFORD NLP "+tweet + " : " + NLP1.findSentiment(tweet));
            System.out.println("NLP1 OUPUT  "+tweet + " : " + NLP.findSentiment(tweet));
            System.out.println("Twittetager "+tweet + " : " + TwitterTager.findSentiment(tweet));
            System.out.println("Twittetagger "+tweet + " : " + TwitterTagger.findSentiment(tweet));

            if(!TwitterTager.findSentiment(tweet).equals(null) && !(TwitterTagger.findSentiment(tweet)==0)){
            	Gatecount.put(TwitterTager.findSentiment(tweet),TwitterTagger.findSentiment(tweet));
               Thread.sleep(1000);
               sentithread dt = new sentithread(Gatecount);
               dt.start();
               Thread.sleep(1000);
         if(!NLP1.findSentiment(tweet).equals(null) && !(NLP.findSentiment(tweet)==0)){
            NLPcount.put(NLP1.findSentiment(tweet),NLP.findSentiment(tweet));
           Thread.sleep(3000);
           sentithread dt2 = new sentithread(NLPcount);
           dt2.start();
           Map<String, Object> NLPout=dt.getValue();
         //  System.out.println(NLPout);
         }
              
               
           }
        }
         
    }
}
