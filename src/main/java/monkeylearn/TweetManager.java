package monkeylearn;

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
        cb.setOAuthConsumerKey("5qnlxXHZsBwO8atEReZ11AmHB");// //61XYsCg4vbMVFMGzUq8jQucFD
        cb.setOAuthConsumerSecret("T3qvLtQlp4flhKUxi1Tb5scRZcZ8k4fyy1gNybOWUMOI80KZLL");//KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U
        cb.setOAuthAccessToken("2963107422-CtkWQUG7HRMqkJZfzP7oCv5uP0OuIESBdjQw2Bn");//3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY
        cb.setOAuthAccessTokenSecret("I43ctKptnPLkxettBY0qDdVD1gMnBbOkMIdODBwz6lf7a");//Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
    	//Twitter twitter = new TwitterFactory().getInstance();
        
        ArrayList<String> tweetList = new ArrayList<String>();
        try {
            Query query = new Query(topic);
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
