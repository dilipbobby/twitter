package monkeylearn;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;public class MonkeyLearnDBTweets {
  public static void main( String[] args ) throws MonkeyLearnException {
      
      MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
     
      String moduleId = "cl_qkjxv9Ly";
     
      Connection c = null;
      Statement stmt = null;
      String tweet = null;
    try {
        
         System.setOut(new PrintStream(new FileOutputStream("/home/vpotla/Desktop/Monkey_learn.txt")));//writing console output to a file
             Class.forName("org.postgresql.ds.PGSimpleDataSource");
        
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sm_livefeed?user=postgres");
        
        c.setAutoCommit(false);
              
        stmt = c.createStatement();
          
        ResultSet rs = stmt.executeQuery( "select tweet_text from twitter_tweets limit 100;");
        while ( rs.next() ) {
        
        tweet = rs.getString("tweet_text");
          
        String[] textList = {tweet};
        
        System.out.println(tweet);
          
        MonkeyLearnResponse res = ml.classifiers.classify(moduleId, textList, true);
          
       System.out.println( res.arrayResult );
      
       System.out.println();
      
        }
        
    }catch(Exception e)
    {
        System.out.println(e);
        
    }
    
  }
}