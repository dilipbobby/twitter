package twittersentiment;

import java.util.ArrayList;

public class Sentiments {

    public static void main(String[] args) {
       String topic="#WorldLargestSolarRooftop";
       int netural=0;
       int Negative=0;
    	//String topic = "ICCT20WC"; //#MakeASongWhiter,#WorldLargestSolarRooftop
        ArrayList<String> tweets = TweetMethod.getTweets(topic);
        NLP.init();
        for(String tweet : tweets) {
            System.out.println(tweet +"Tweets sentiments "+ " : " + NLP.findSentiment(tweet));
        if(NLP.findSentiment(tweet).equals("Neutral")){
        	netural++;
        }else if(NLP.findSentiment(tweet).equals("Negative")){
        	Negative++;
        }
        }
        System.out.println("This the count of netural tweets" +netural);
        System.out.println("This the negative tweets count " +Negative);
    }
}
