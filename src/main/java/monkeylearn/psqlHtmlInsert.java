package monkeylearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class psqlHtmlInsert {
	
	public static void psqlHtmlinsertdb (String tablename,String paragraph_text,String obj,String filename) throws SQLException{
		
		Connection c = null;
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/subjectivityanalysis",
	            "postgres", "");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         //String sql = "INSERT INTO TweetSentimentPersons (TweetText,Sentiment) "
	        		 String sql = "INSERT INTO "+tablename+" (paragraph_text,obj,filename)"	 
	       //  + "VALUES (tweet_text,tweetsentiment);";
	        	// +" VALUES"+" ("+tweet+","+sentiment+");";
	        		 +" VALUES"+" (?,?,?);";
	        		 
	        		 System.out.println(sql);
	        		 pstmt = c.prepareStatement(sql);
	                 pstmt.setString(1, paragraph_text);
	        	     pstmt.setString(2, obj);
	        	     pstmt.setString(3, filename);
	        	       //pstmt.setString(3, tweet_language);
	        	       //pstmt.setString(4, r.getYPredicted().toString());
	        	     pstmt.executeUpdate();
	         
	        	// stmt.executeUpdate(sql);
		       //  stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         
		         //System.exit(0);
		      }

finally {
          pstmt.close();
          c.close();
        }
		
 }//MAIN CLOSE 
	}///CLASS CLOSE


