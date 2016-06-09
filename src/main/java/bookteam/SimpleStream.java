package bookteam;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleStream {
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("61XYsCg4vbMVFMGzUq8jQucFD");
        cb.setOAuthConsumerSecret("KD1BP4vF0J608uoVCgDQE8PTklIXmJBVb3OfJKxJBhnOmaUG7U");
        cb.setOAuthAccessToken("3494517553-lMyp3YPxhYmoCg4fzGUyjxZauBBuEWbls6xdRfY");
        cb.setOAuthAccessTokenSecret("Wpd6Y1Lka7836odReSTYqU3yQuF3JPkWCztMWcB0YJ8IR");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

           
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

          
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

          
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String url = user.getProfileImageURL();
                
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");
System.out.println(url);
            }

            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }


			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
    
        String keywords[] = {"#kkr","#dd"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);  

    }
}