package readingfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class psqlInsertfields {
	
	public static void insertdb (String tablename,String tweet,String sentiment) throws SQLException{
		
		Connection c = null;
	     Statement stmt = null;
	     PreparedStatement pstmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/twittersentiment",
	            "postgres", "");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	        		 String sql = "INSERT INTO "+tablename+" (tweet_text,tweetsentiment)"	 
	      
	        		 +" VALUES"+" (?,?);";
	        		 
	        		 System.out.println(sql);
	        		 pstmt = c.prepareStatement(sql);
	                 pstmt.setString(1, tweet);
	        	     pstmt.setString(2, sentiment);
	        	    
	        	     pstmt.executeUpdate();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         
		      }

finally {
          pstmt.close();
          c.close();
        }
		
 }//MAIN CLOSE 
	}///CLASS CLOSE