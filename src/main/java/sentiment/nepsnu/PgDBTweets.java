package sentiment.nepsnu;

/*import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class PgDBTweets{
	
	static Map<String, Integer> NLPcount = new HashMap<String, Integer>(); 
	static Map<String, Integer> Gatecount = new HashMap<String, Integer>(); 
	
   public static void main( String args[] ) throws FileNotFoundException
     {
	   
      //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("/home/storm/Videos/senti.txt")), true));

       Connection c = null;
       Statement stmt = null;
       String tweet="";
       int tweetcount=0;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://10.0.14.187:5432/sm_livefeed?user=postgres");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery("select tweet_text from twitter_tweets where tweet_text IS NOT NULL;");
         while ( rs.next() ) {
        	 
        	 
        	 tweet = rs.getString("tweet_text");
        //	 tweet = tweet.replaceAll("\\r\\n|\\r|\\n", " ").replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");	
             //tweet = tweet.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");
        System.out.println("DATABAST WORD  "+ tweet);

        	 if(tweetcount<1){
        	 System.out.println(tweet);
        //	 System.out.println("STANFORD NLP1 "+tweet + " : " + NLP1.findSentiment(tweet));
        	// System.out.println("NLP1 OUPUT  "+tweet + " : " + NLP.findSentiment(tweet));
        	System.out.println("Twittetager "+tweet + " : " + TwitterTager.findSentiment(tweet));
        	    System.out.println("Twittetagger "+tweet + " : " + TwitterTagger.findSentiment(tweet));

             if(!TwitterTager.findSentiment(tweet).equals("")&& !TwitterTager.findSentiment(tweet).equals(null) && !(TwitterTagger.findSentiment(tweet)==0)){
             	Gatecount.put(TwitterTager.findSentiment(tweet),TwitterTagger.findSentiment(tweet));
              //  Thread.sleep(1000);
                sentithread dt = new sentithread(Gatecount);
                dt.start();
               // Thread.sleep(1000);
             }
         
                if(!NLP1.findSentiment(tweet).equals("") && !NLP1.findSentiment(tweet).equals(null) && !(NLP.findSentiment(tweet)==0)){
             
             	 NLPcount.put(NLP1.findSentiment(tweet),NLP.findSentiment(tweet));
          //  Thread.sleep(3000);
            sentithread dt2 = new sentithread(NLPcount);
            dt2.start();
            tweetcount++;
            System.out.println("TweetCount"+tweetcount);
                }//inner if close for 1000
                }else{System.out.println("!!! close the tweet connections !!!!");}
        	 tweetcount++;
        	 System.out.println("TweetCount"+tweetcount);
        	 }
        	 
}
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }
}*/





import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;public class PgDBTweets{
    
    static Map<String, Integer> NLPcount = new HashMap<String, Integer>();
    static Map<String, Integer> Gatecount = new HashMap<String, Integer>();
//    Map Twittertager=new HashMap();    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException  {
       //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("/home/storm/Videos/senti.txt")), true));        //String topic = "#ThursdayThoughts";
        
        
        Connection c = null;
       Statement stmt = null;
       String tweet = null;
try {
    
  // System.setOut(new PrintStream(new FileOutputStream("/home/vpotla/Desktop/Tweet_analysis.txt")));//writing console output to a file       
	Class.forName("org.postgresql.ds.PGSimpleDataSource");
//c = DriverManager.getConnection("jdbc:postgresql://10.0.14.192:5432/tests?user=postgres");
c = DriverManager.getConnection("jdbc:postgresql://10.0.14.187:5432/sm_livefeed?user=postgres");        c.setAutoCommit(false);
      stmt = c.createStatement();
ResultSet rs = stmt.executeQuery( "select tweet_text from twitter_tweets;");
while ( rs.next() ) {
    
    tweet = rs.getString("tweet_text");
System.out.println(tweet);
        
 //ArrayList<String> tweets = TweetManager.getTweets(topic);
       NLP.init();
       NLP1.init();
       TwitterTager.init();
       TwitterTagger.init();
       //for(String tweet : tweets) {
         
           //System.out.println("STANFORD NLP "+tweet + " : " + NLP1.findSentiment(tweet));
           //System.out.println("NLP1 OUPUT  "+tweet + " : " + NLP.findSentiment(tweet));
           System.out.println("Twittetager "+tweet + " : " + TwitterTager.findSentiment(tweet)+"    "+TwitterTagger.findSentiment(tweet));
           //System.out.println("Twittetagger "+tweet + " : " + TwitterTagger.findSentiment(tweet));      
           /*   if(!TwitterTager.findSentiment(tweet).equals(null) && !(TwitterTagger.findSentiment(tweet)==0)){
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
             
             
             
          }*/
 //}
}
   }catch(Exception e){System.out.println(e+"hello");}
    }
}