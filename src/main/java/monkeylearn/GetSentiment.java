package monkeylearn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.monkeylearn.MonkeyLearnException;

public class GetSentiment {
	

	public static void main(String args[]) throws FileNotFoundException, MonkeyLearnException, IOException, SQLException, InterruptedException{
		int ccount=100000000;
		//while(true){
		for( int i=0; i < ccount; i++ ) {
		WriteToFile topic=new WriteToFile();
		int count=0;
		
		File file = new File("/home/storm/Documents/datasetsTopics/count.txt");
	      // creates the file
	      file.createNewFile();
	      // creates a FileWriter Object
	      FileWriter writer = new FileWriter(file); 
	      // Writes the content to the file
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Date date = new Date();
	     //TweetSentimentNewsT
		 //System.out.println("Reading news Topic");
	    
	     
	      //over
	    /*  String SocialNetworks="/home/storm/Documents/datasetsTopics/socialNetworks.csv";
	   
	      String twitterscnetworkstopic="Socialnetwork";
		 try{
	      topic.SetPathforSentiment(SocialNetworks, twitterscnetworkstopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
			// System.out.println("Exception code  " +e);
		 }*/
	      
	      
	      
	     //over 
	/*	 String movies="/home/storm/Documents/datasetsTopics/moviesT.csv";
		   
	      String twitterMoviestopic="Movies";
		 try{
	      topic.SetPathforSentiment(movies, twitterMoviestopic);
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      
	      
	      //over 8k tweets
	     /* String softwarecmpys="/home/storm/Documents/datasetsTopics/swcomys.csv";
		   
	      String twittersoftwarecmpystopic="Softwarecmpy";
		 try{
	      topic.SetPathforSentiment(softwarecmpys, twittersoftwarecmpystopic);
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      //over 
	  /*    String motorcmpys="/home/storm/Documents/datasetsTopics/motorcmpy.csv";
		   
	      String twittermotorcmpysstopic="motorcompany";
		 try{
	      topic.SetPathforSentiment(motorcmpys, twittermotorcmpysstopic);
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }
	      */
	      //over
		/* String manucmpys="/home/storm/Documents/datasetsTopics/manufaucture.csv";
		   
	      String twittermanutopic="manufaccompany";
		 try{
	      topic.SetPathforSentiment(manucmpys, twittermanutopic);
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      //over
	 /*   String Indus="/home/storm/Documents/datasetsTopics/Industries.csv";
		   
	      String twitterIndustopic="Industries";
		 try{
	      topic.SetPathforSentiment(Indus, twitterIndustopic);
		 }
		 catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
		 
	      
	      String weeks="/home/storm/Documents/datasetsTopics/weekdays/weeks";
		   
	      String twitterweekstopic="weeks";
		 try{
	      topic.SetPathforSentiment(weeks, twitterweekstopic);
		 }
		 catch(Exception e){
		
			 System.err.println("An exception was thrown"+e);
		 }
	      
		 
		 //over
		 /*String socks="/home/storm/Documents/datasetsTopics/symbolsb.csv";
		   
	      String twitterstockstopic="stcoks";
		 try{
	      topic.SetPathforSentiment(socks, twitterstockstopic);
		 }
		 catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }
	     * /
	   
	      //over
	      /*System.out.println("Reading Sports Topic");
	      String Sports="/home/storm/Documents/datasetsTopics/sportst.csv";
	     String twittersportstopic="twittersportstopic";
		 try{
	      topic.SetPathforSentiment(Sports, twittersportstopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
			// System.out.println("Exception code  " +e);
		 }*/
	      
	      //over
	   /*   String Insurance="/home/storm/Documents/datasetsTopics/Insurencest.csv";
	      String twitterInsurancetopic="tweetsentimentinsurances";
		 try{
	      topic.SetPathforSentiment(Insurance, twitterInsurancetopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
			// System.out.println("Exception code  " +e);
		 }*/
	     
	      //over
/*	String banks="/home/storm/Documents/datasetsTopics/Banks.csv";
	      String twitterbankstopic="tweetsentimentbanks";
		 try{
	      topic.SetPathforSentiment(banks, twitterbankstopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
			// System.out.println("Exception code  " +e);
		 }*/
	  
	      //over 
	/* String finace="/home/storm/Documents/datasetsTopics/finacest.csv";
	      String twitterfinacetopic="TweetSentimentfinance";
		 try{
	      topic.SetPathforSentiment(finace, twitterfinacetopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      //OVER
	    /*String reatail="/home/storm/Documents/datasetsTopics/reatilbank.csv";
	      String twitterretailtopic="TweetSentimentRetail";
		 try{
	      topic.SetPathforSentiment(reatail, twitterretailtopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      //over
	      /*String investment="/home/storm/Documents/datasetsTopics/investmentbank.csv";
	      String twitterinvestmenttopic="twitterinvestmenttopic";
		 try{
	      topic.SetPathforSentiment(investment, twitterinvestmenttopic);
	      //throw new Exception();
		 }catch(Exception e){
			 System.err.println("An exception was thrown"+e);
		 }*/
	      
	      //TEST
	     /* String personsip="/home/storm/Documents/datasetsTopics/persons.csv";
	      String presonstablename="tweetsentimentpersons";
		  try{
	      topic.SetPathforSentiment(personsip, presonstablename);
		  }catch(Exception e){System.out.println(e);}
		  //System.out.println("Reading news companies Topic");
		
		//String companyip="/home/storm/Documents/datasetsTopics/companies";
		//String companyop="tweetsentimentcompany";
		//topic.SetPathforSentiment(companyip, companyop);*/
		
		//String politicsip="/home/storm/Documents/datasetsTopics/politics.csv";
	/*	 String politicsip="/home/storm/Documents/datasetsTopics/politicsUsa.csv";
		 String Politicstablename="Politics";
		try{
		topic.SetPathforSentiment(politicsip, Politicstablename);
		}catch(Exception e){System.out.println(e);}*/
			/*System.out.println("Reading news Topic");
			
			String newsip="/home/storm/Documents/datasetsTopics/newst.csv";
		String newstablename="TweetSentimentNewsT";
		try{
		topic.SetPathforSentiment(newsip, newstablename);
		}catch(Exception e){System.out.println(e);} TEST */
	 count++;
	 System.out.println("Date and time of update" +date+ "with the count" +count);
	 writer.write( "Date and time of update" +date+ "with the count" +count); 
     writer.flush();
     writer.close();
     //sleepMinutes(120);// DO SOMETHING EVERY HOUR
	//}
		}
}
private static void sleepMinutes(int minutes) {
    try {
        System.out.println("Sleeping for " + minutes);
        Thread.sleep(minutes * 1000*60);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}
