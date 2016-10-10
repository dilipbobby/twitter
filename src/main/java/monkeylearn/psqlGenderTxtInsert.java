package monkeylearn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class psqlGenderTxtInsert {
	
	public static void psqMaleinsertdb (String tablename,String text,String gender) throws SQLException{
		
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
	        		// String sql = "INSERT INTO "+tablename+" (paragraph_text,obj,filename)"	
	        				 String sql = "INSERT INTO "+tablename+" (text,gender)"	
	       //  + "VALUES (tweet_text,tweetsentiment);";
	        	// +" VALUES"+" ("+tweet+","+sentiment+");";
	        		// +" VALUES"+" (?,?,?);";object
	        				 +" VALUES"+" (?,?);";
	    	        		 
	        		 
	        		 //System.out.println(sql);
	        		 pstmt = c.prepareStatement(sql);
	                 pstmt.setString(1, text);
	                 pstmt.setString(2, gender);
	        	    // pstmt.setString(2, obj);
	        	     //pstmt.setString(3, filename);
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

