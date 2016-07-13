package aail.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class postgersInsert {
	   public void psqlInsertsNews(String tweet_text,String probability,String tweetsentiment ) {
	      Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/users",
	            "postgres", "");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO TweetSentimentNews(TweetText,Probability,Sentiment) "
	               + "VALUES (tweet_text,probability, );";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	   }
	   public void psqlInsertsPersons(String tweet_text,String probability,String tweetsentiment ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/users",
		            "postgres", "");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO TweetSentimentPersons (TweetText,Probability,Sentiment) "
		               + "VALUES (tweet_text,probability,tweetsentiment);";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		   }
	   public void psqlInsertsCompany(String tweet_text,String probability,String tweetsentiment ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/users",
		            "postgres", "");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO TweetSentimentCompany(TweetText,Probability,Sentiment) "
		               + "VALUES (tweet_text,probability,tweetsentiment );";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		   }
	   public void psqlInsertsProducts(String tweet_text,String probability,String tweetsentiment ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/users",
		            "postgres", "");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO TweetSentimentProducts (TweetText,Probability,Sentiment) "
		               + "VALUES (tweet_text,probability,tweetsentiment );";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		   }
	   public void psqlInsertspolitics(String tweet_text,String probability,String tweetsentiment ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/users",
		            "postgres", "");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO TweetSentimentProducts (TweetText,Probability,Sentiment) "
		               + "VALUES (tweet_text,probability,tweetsentiment);";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		   }
	   public void psqlInsertsBanks(String tweet_text,String probability,String tweetsentiment ) {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/users",
		            "postgres", "");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO TweetSentimentProducts (TweetText,Probability,Sentiment) "
		               + "VALUES (tweet_text,probability,tweetsentiment );";
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		   }
	}
