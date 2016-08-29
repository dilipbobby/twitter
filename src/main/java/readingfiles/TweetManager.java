package readingfiles;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetManager {

    public static ArrayList<String> getTweets(String topic) {

    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("61XYsCg4vbMVFMGzUq8jQucFD");
        cb.setOAuthConsumerSecret("KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U");
        cb.setOAuthAccessToken("3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY");
        cb.setOAuthAccessTokenSecret("Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR");
    	
        // vK7tus2jKGm96AdAMkLRCmR1k
        //WpWmBdGXUQwwsaxYSd6njPufN3jqylkbUohaqtGJGz7fPhIpuO
        // 3479265073-tSMXmJY9NegAueQin0dVhSKullltH7vx4sA6d5z
        // 8QGV9zXn09pAewMlCKzErftMDNPdlrM1puLCUghWGw3sf
    	
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
    	//Twitter twitter = new TwitterFactory().getInstance();
        
        ArrayList<String> tweetList = new ArrayList<String>();
        try {
            Query query = new Query("lang:en AND"+topic);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
               // System.out.println("TWEET ADDED TO THE LIST	"+tweetList);
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }
}
