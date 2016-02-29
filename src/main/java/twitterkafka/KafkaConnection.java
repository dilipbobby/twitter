package twitterkafka;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONTokener;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

public class KafkaConnection{

	 static OAuthConsumer consumer;
	 static HttpResponse response;
	 static String line;
	 static String m;
	 //static int requestcount=0;
	 static int requestcount=0;
	 static String old="";
	 static Set mySet =new HashSet();
	 static Set mySet1=new HashSet();
	
	  private static final int BATCH_SIZE = 1;

	 			static	String ConsumerKey="El8589UFvPyT8MMxginumS8QO";
				static	String ConsumerSecret="CS71lglHYZj32kDqyndYBsV5JOq0W9VHEF8zPOBHcOO459BkWH";
				static	String AccessToken="3492111673-JRnQEAKF3ShUlYa8vLAy5IQwYZomaABxcFY0cCF";
				static	String AccessSecret="iCYUB23nFQ9NtuFXHZhWDYAfzQSZzn4bOfVbMkfKprmwz";

		public void r(String line) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, HttpException, IOException{
			
		     Properties props = new Properties();

	   	     props.put("metadata.broker.list", "localhost:9092");

	   	     props.put("serializer.class", "kafka.serializer.StringEncoder");

	   	     props.put("partitioner.class", "twitterkafka.SimplePartitioner");

	   	     props.put("request.required.acks", "1");

	   	     props.put("retry.backoff.ms", "150");

	   	     props.put("message.send.max.retries","10");

	   	     props.put("topic.metadata.refresh.interval.ms","0");
		
		  //   props.put("offsets.topic.num.partitions","2");

	   	     ProducerConfig config = new ProducerConfig(props); 
		  
			  
		
    	 	     OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey,ConsumerSecret);
    	 	     consumer.setTokenWithSecret(AccessToken,AccessSecret);
    	 				
				 {
//HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?count=200&include_rts=true&contributors_details=true&screen_name="+line);
     
HttpGet request = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?include_entities=true&count=100&q="+line);
       	 	
	             consumer.sign(request);

  		     HttpClient client = new DefaultHttpClient();

  		     HttpResponse response = client.execute(request);
  
  		     int statusCode = response.getStatusLine().getStatusCode();
if(statusCode==200){
               requestcount++; 	  
	  System.out.println("requestcount:"+requestcount);

  }
  		     String m=IOUtils.toString(response.getEntity().getContent());

	    

		     final Producer<String, String> producer = new Producer<String, String>(config);



 	//KeyedMessage<String, String> data = new KeyedMessage<String, String>("twitter500",m);
//

try{

			
			  int outputIndex = 0;
			  List<Object> jsonObjects = new ArrayList<>();
	            JSONObject jsonobject = new JSONObject(m);
	            for(int z=0;z<jsonobject.length();z++){
	            JSONArray splitdata;
	            splitdata=	jsonobject.getJSONArray("statuses");
	            
try{
	            for (int i = 0; i < splitdata.length(); i++) {
	                jsonObjects.add(splitdata.get(i));
	                // Flush if max objects per file has been reached.
	                if (jsonObjects.size() == BATCH_SIZE) {
	                    String filesplit=flushFile(jsonObjects, outputIndex);
	                    JSONArray filesplitarray=new JSONArray(filesplit);
	                    // System.out.println(json_main_array);
	                    JSONObject fileobject=filesplitarray.getJSONObject(0);
	                    //System.out.println(object_tweet);
	                     String tweet_created_at=fileobject.getString("created_at");
			     
				String id_str=fileobject.getString("id_str");
				String p=id_str.concat(tweet_created_at);
	                      //System.out.println(tweet_created_at);
	                       if(mySet.add(p)){
	                       //System.out.println(mySet);
	          	
	                    KeyedMessage<String, String> data = new KeyedMessage<String, String>("tweets",filesplit);
	                   
            	
	                    producer.send(data);
			    System.out.println("myset size: "+mySet.size());
	                    jsonObjects.clear();
	                    outputIndex++;
							//if(mySet.size()%1000==0){
	          					//for(int itr=0;itr<mySet.size();itr++){					
	          					//mySet.remove(itr);
	          					//System.out.println(itr);
	          	//}
	          	//}
	                }
	            }
	            
	                if (!jsonObjects.isEmpty()) {


		                flushFile(jsonObjects, outputIndex);
			

		            }
				
				
		            
				//requestcount++;
				//System.out.println("duplicate request:"+requestcount);		
				
				if(requestcount>=1&&requestcount<=150)
				{	
					ConsumerKey="TPDcb4VOwdKZY74tnRHi8HOqS";
                                        ConsumerSecret="kuj6EbNs7DZ8IngCb4BQKJKyHqwnXKQqO4660hhCNHGs9rLq9M";
                                        AccessToken="3492111673-l1Elx3vLdRlStS43Xw8i0kADgiDgCtK1gFbFqHA";
                                        AccessSecret="HRUPtNwYKHBKhINJ5MDhyajPemxUAGwZtCx5wMR35pZjB";

                
					 
					System.out.println("1st looping");
				}
				if(requestcount>=151&&requestcount<=300){
					ConsumerKey="Fmz3QTJLpvC3kB0IJR4vhY99S";
					ConsumerSecret="fG5tC7BQoiaZeRvnxlEzD56jZ8PPqTCDzxuEUQrSR5qpokLu1u";
					AccessToken="3492219680-w5nfDwMilxcNOp0fxKPSLwhF5CY6XE1WUG8y2bz";
					AccessSecret="aW460z18Zqnfv5ISn3wCv9XfTUAa4bS3ajl5mbWpO7eNC";
	
					System.out.println("2nd looping");
				}
				if(requestcount>301&&requestcount<=450){
					 ConsumerKey = "HJaCxhKJ7D8VGJ3LKAGC39js7";
	 				 ConsumerSecret = "VelXPInJg7BQp6EjSxM0r0wlwuWinYphyVIUJ8bZHqLh3k6gG9";
	 				 AccessToken = "3492111673-ZlOeX5gnEFAHGi7WutBel0UtWKsFHRzLknMkzIP";
	 				 AccessSecret = "OeWTyxzsiHm9hAjXIHBSsGg5ciAb7ZssboF2JmtNGPlIJ";
					
					System.out.println("3rd looping");
				}
				if(requestcount>=451&&requestcount<=600){
					ConsumerKey="xoaClfIEuShb28S1J6WUklgey";
					ConsumerSecret="laXAUiaz0lhQK8aOWLdu7Zy1Hf2g5FvPgLXc0qexebbkngBRQn";
					AccessToken="3492111673-k6ILYTgDmsnLCNQ74LHkWnxH58XDarZSWJu5q7k";
					AccessSecret="FYdc8yXIZKavmlQp6KKQWUe8lP4r6vPqOTSJebeK3diGR";
					
					System.out.println("4th loop");
				}
				if(requestcount>601&&requestcount<=750){
					ConsumerKey="8DTKAAGV0ZiuplfttxiX1E0Fn";
					ConsumerSecret="5bBW80zDzuZQygQ3XHar6rPwvQIUli4wAxqQqUs5F364Tp5oDP";
					AccessToken="3492111673-8NPTBxK0J3dJCekrY78LDHnS8TuCNWl3yXQhLnY";
					AccessSecret="cGCdMENrYa2PbdMQuF9hZApyS8dDisPSN7LTktey6oALX";
				
					System.out.println("5th looping");
				}if(requestcount>=751&&requestcount<=900){
					ConsumerKey="L2oCRIkjHRyz03lKH8ymlL6xT";
                                        ConsumerSecret="uVa66S0jSQs7cuD1soYGInBQNyiEIaI87JSasaLOchOxR3eqoX";
                                        AccessToken="3492219680-txbz1Q3HaBHWQEkiyPMtvlYphbOPfOablJt4GR5";
                                        AccessSecret="lzIdKZM1kn81jKwFBGHXhPRyCCGqCseyO9CAM81JLxN4q";
			
					System.out.println("6th looping");					                               
				}if(requestcount>=901&&requestcount<=1050){
					ConsumerKey="2PF5ZjF71Fj8WhwFMG9RoY0dy";
					ConsumerSecret="MjF26YQUS3It8avHTKeSGpqt5AMvyN9Q95pAVN0TzGCVwda0Po";
					AccessToken="3492111673-mAsUGY7hDsu5VACGACmujRk89ultYaIZICGwqd9";
					AccessSecret="dfoPK0Qko6Domov2pqO7HJ8g7XzwkKihbuH4f2P6YcWxD";

					
					System.out.println("7th looping");
				}if(requestcount>=1051&&requestcount<=1200){

					ConsumerKey="CiD2xSRnnIf1ueie1jEdCGiFn";
					ConsumerSecret="5uOhxD00Kc5QmIe2ukN58LKXT4tMC3BirniGnJ7EmLqTfiqfFt";
					AccessToken="3492111673-xanljCe29v692EX2QqZb7r61ovJ4xsydu81DIHc";
					AccessSecret="NGReh0lh0jC90vThjkmVWAFDDMAOvAaG6YXyBZ72DU3ln";
					
					System.out.println("8th looping");	
										                               
				}if(requestcount>=1201&&requestcount<=1350){

					ConsumerKey="EH5MgE0m1gs6o6DyQ5gCJ6OW9";
					ConsumerSecret="eU8YeGyw6jMti4lU9abrEoi5MK2snV4PBIm1FBfPU4J0lygdG0";
					AccessToken="3492111673-XjGYB3zSPmWPtrR48dP0c8ifbuBBh2basC0Zrt8";
					AccessSecret="FgcXLgy8Q7HZ5a76xtUYBdudHHmQyopEFtcvt5hJySisv";
					System.out.println("9th looping");
											                               
				}if(requestcount>=1351&&requestcount<=1500){

					ConsumerKey="C5ln91ZfuqjNmDrEzAPFmlgkG";
					ConsumerSecret="jztBnxOBPOGAbsnf37dagmJO1HvMjInOqQ7hZTIFAtB3TYiw4B";
					AccessToken="3492111673-Ohe804pjCl1aq8DPQZYZeFcV0PkAG9OcPTzremE";
					AccessSecret="1XAUISNfbbxFXdnZy1DMHBtrci24BvfHYOT4M6RmNPbB1";
					System.out.println("10th looping");
											                               
				}if(requestcount>=1501&&requestcount<=1650){

					ConsumerKey="yCIFsJ2Oje0haveF6Ay6U7jVB";
					ConsumerSecret="86eAvh15IZiMUbcxc1XvR9bOmujl6t4wQLUI8tQar9Ujp8P4mC";
					AccessToken="3492111673-bOlbWHR3un0dE38o5oxzufYXmtLhmW1tn0EIsnm";
					AccessSecret="uPbuoSAU1eMZ4z01Saa42Lc44dyMxrnz4zdh5Q9j0p01q";
					System.out.println("11th looping");
											                               
				}if(requestcount>=1651&&requestcount<=1800){

					ConsumerKey="x0W9cC9t1p9lAs0ntqjyZQDX6";
					ConsumerSecret="5F7WGXdjWUnCKY7w6fWW9HR2XS7dylpkFOpPgCKmyEze85MnX2";
					AccessToken="3492111673-5DjUGuC1fp22OPDzGUKe8Ay2Jw71OjebAVbCgXM";
					AccessSecret="JFgeHDdtAYYauIaR34qgdVzXUh8lQuVM8Hq8X4z5hjeep";
					System.out.println("12th looping");
											                               
				}if(requestcount>=1801&&requestcount<=1950){

					ConsumerKey="ym7Dor7jXzBWN9AyDY5nIFDrZ";
					ConsumerSecret="mWbDmUsfHRSJnX8fKoACf86Uty3iTaAhSnrtYX06zbZeofu7N0";
					AccessToken="3492111673-4zeZ34bmzTR78ic6fpcYhKilAITPas7RzgLxwyf";
					AccessSecret="GT0UnwX6zMmzal3WNTApigH0pqLaoabg1gvw9nZePzEPa";
					System.out.println("13th looping");
											                               
				}if(requestcount>=1951&&requestcount<=2100){

					ConsumerKey="QFURypBHlMdwiwhWC1G9CXVSb";
					ConsumerSecret="ILVV30Ed03OyzTak1RZ4atFNL2x63ht48zfZLnWck1P7b1y6k6";
					AccessToken="3494517553-GwR5HIydE1hxtwyRJILWYwQa8WbMpJB79L12wiV";
					AccessSecret="Dm5DOZYpaj9Ad9Jlz3yrvCPr1ogAGa61oFb3hbfAquLn4";
					System.out.println("14th looping");
											                               
				}if(requestcount>=2101&&requestcount<=2250){

					ConsumerKey="VXIRfI3w7LVSNp17v8Rz7OMhN";
					ConsumerSecret="2egrvWukptofwkvEPrggXoUDFOxhmoMjMUNzcW7R6ROpOPGFDB";
					AccessToken="3494517553-kpEIDtAol2nDiRGSSA4VKgUBex9OQz9xtSJhSS1";
					AccessSecret="0wrvmUEge2zRTp7rfhogNqfmt7217NMCZYpw0adod7zSE";
					System.out.println("15th looping");
											                               
				}if(requestcount>=2251&&requestcount<=2400){

					ConsumerKey="ptwFFTQISDkczxySByohSAvt3";
					ConsumerSecret="6xDXMaoEALPg9aF3iO1cTMtT6RngYm89Qc5Ijg3tsCPugR06Hw";
					AccessToken="3494517553-yGH4LYRONBbjiMDYBfCj4QR8o1qe4sxBGZMPJRt";
					AccessSecret="YsR0fcixWBSxTWCY2h0ygQ69FvdIZPBbjVreXBxIehDwQ";
					System.out.println("16th looping");
											                               
				}if(requestcount>=2401&&requestcount<=2550){

					ConsumerKey="mKRyCS4y4XgBAGjPFY924y8ou";
					ConsumerSecret="g4yOwlOjycc58u63UyFeketT3y2mo7awHZ1DOdVCKMk6cbwBmG";
					AccessToken="3479265073-ONhH3unw7tCsAo4huAumleGRmaFcG2k19YPBcz3";
					AccessSecret="LyAz2r262G9X7gAraOIJZHlelGwWaixnMF4e8e7mz33MA";
					System.out.println("17th looping");
											                               
				}if(requestcount>=2551&&requestcount<=2700){

					ConsumerKey="vK7tus2jKGm96AdAMkLRCmR1k";
					ConsumerSecret="WpWmBdGXUQwwsaxYSd6njPufN3jqylkbUohaqtGJGz7fPhIpuO";
					AccessToken="3479265073-tSMXmJY9NegAueQin0dVhSKullltH7vx4sA6d5z";
					AccessSecret="8QGV9zXn09pAewMlCKzErftMDNPdlrM1puLCUghWGw3sf";
					System.out.println("18th looping");
											                               
				}if(requestcount>=2701&&requestcount<=2850){

					ConsumerKey="gMM6qYUzXvKrZCQVTwffPq9yj";
					ConsumerSecret="a7jNIV9dRddkrnJkjfoBc9jTWjo68F1zesgTn6LJQtWTq1wyut";
					AccessToken="3479265073-G7nZreL4R5uAEap9IXROLXjViwPcgdanmXyavtw";
					AccessSecret="98Ojhz0NdyPBGEFoK1NoEQaZSr4ay4WjfBmCKnqT15Pwy";
					System.out.println("19th looping");
											                               
				}if(requestcount>=2851&&requestcount<=3000){

					ConsumerKey="fOYS3pbTiT9yAHZOSrsyOw2Yo";
					ConsumerSecret="SGSJuGSMsYI3g9VNSGUtVdOGMCRZqt0ulM0TBB8xaBdm3xPDHZ";
					AccessToken="3479265073-PYy8w1i2QN4yuhPeZsTIlRjDNazlBucHrmnsjy1";
					AccessSecret="kJu3v4YSmlurWgHDjHYJRiW9MYwG4XrPsCoGoOKRDkI7c";
					System.out.println("20th looping");
											                               
				}if(requestcount>=3001&&requestcount<=3150){

					ConsumerKey="F7qCBnlqGEP3VAQDE1vPSVTYF";
					ConsumerSecret="oZGbNbrrWUoYS9wFez8yFaFQ7CTbnwuyjlgx83jslRfKcikb7M";
					AccessToken="3479265073-iJgRfmxpnjSH36K3Pz8sPVjAvZKaE26wOESzntp";
					AccessSecret="TGiPVOLrC9tPZRZ8UgIhv0UfWzr6N4cSlhSUERl3ctfQA";
					System.out.println("21st looping");
											                               
				}if(requestcount>=3151&&requestcount<=3300){

					ConsumerKey="vXao3wizoBxMgkNpoI0VqeR9A";
					ConsumerSecret="Q5j0CrzFPcVppQEPE2HMk1qH72h48Rv0FoxNtSCUWXmluv5X39";
					AccessToken="3479265073-dVEwlGuagGZ2YVuituEes44ClA0lHy8FozUqaFB";
					AccessSecret="GBW19Hw0sAWhBMRoQjx14NFrPCsDLQdNBCC1SeAyaYlmo";
					System.out.println("22nd looping");
											                               
				}if(requestcount>=3301&&requestcount<=3450){

					ConsumerKey="ViTqn3w8M7TkzHcdDizZ5XVTT";
					ConsumerSecret="OgBPDu0zCt2qRietpgykPwclI2XSxJWdwELpg4KQnVagApst99";
					AccessToken="3479265073-ecalPxWieo5EQs8g7QLfVJlJX75TlvBzzNIedAk";
					AccessSecret="RPpJSTnyH8GVrLkUUrCrREG9fLIJ2OjFMFCwBnMkiAt8g";
					System.out.println("23rd looping");
											                               
				}if(requestcount>=3451&&requestcount<=3600){

					ConsumerKey="RYKK8aRaAMw2hhtmBx24gsaJ5";
					ConsumerSecret="aq1zSUxXrqvPubzXgg6WhOcUkkpz0TgasLgREXjRd0ksSYsd8m";
					AccessToken="3479265073-1bJXRTyDVNdZRtR7FTUUCGFixTsObpMbb0NrAeG";
					AccessSecret="vtF8kRotZJ2ZuLQHbjohqdjjo3QZH3btlr4ARqwUHM4Oc";
					System.out.println("24th looping");
											                               
				}if(requestcount>=3601&&requestcount<=3750){

					ConsumerKey="pdZMIjckFJHDsrrwckcyoYNiu";
					ConsumerSecret="oxTTM8oGnNQxdLP4wmGAQZEVuF17013mkFjehgi2PC4rZDI2VD";
					AccessToken="3492219680-rHpLqTTq5RbJuXYqNa9YTHJRsuBSHRzMl1WBfJx";
					AccessSecret="NVD412ZciiyZd7mZAHpsGKOAAqPTU5uNAvZI9BQDTBxCp";
					System.out.println("25th looping");
											                               
				}if(requestcount>=3751&&requestcount<=3900){

					ConsumerKey="mS2F7z0HRLtYp5oK3pZI2FFan";
					ConsumerSecret="Y2vpC9Y8ZbGMzxODY7M3JhcENxWcOZlpb30Q0B8VW8rL6UnSGi";
					AccessToken="3492219680-A0lUK2ek6V67k7JWbtKtAcvG856kmHr53CcgKAB";
					AccessSecret="zujpYdZo3HuXuRAvhCIc1tN6iMBkZfMDITHqkmohiAmk7";
					System.out.println("26th looping");
											                               
				}if(requestcount>=3901&&requestcount<=4050){

					ConsumerKey="UG182uLaYJF3zMQT6tAF8RnXj";
					ConsumerSecret="4CkXVJ4oNb5unzsXUnnuWBZusaploq4KgueIPTkbsXaBAP5RZt";
					AccessToken="3492219680-R0qqnUpAtvJkbA6SpNL20cYp7y4zewf19eKUXx8";
					AccessSecret="dF4ixRwUD05bB0vlA1Qh792exlr8zit9do7qoXND1ZRqH";
					System.out.println("27th looping");
											                               
				}if(requestcount>=4051&&requestcount<=4200){

					ConsumerKey="dNrCPTahvKmdWp7yQeqz5OS1d";
					ConsumerSecret="yQ08oRxbAExhxUvJXbrrsvSAMm7cVlvJ6CcuM5iJpRxMvm25da";
					AccessToken="3492219680-UB4n5lENE6zyPr1K9RLUCDem2mv6GpJN5kT5gED";
					AccessSecret="uXHF9h9F2afbPigggib3Sgofy6mpJuxjpTPmJIEs9FU7I";
					System.out.println("28th looping");
											                               
				}if(requestcount>=4201&&requestcount<=4350){
					ConsumerKey="oyZm1dJizdeCjlla5kgWYEXLx";
					ConsumerSecret="vPA1ONmExJq0rlPlzunb3TUpgIeZ95PYIOqZkFc1bGaTroCrtY";
					AccessToken="3492111673-EQKJiZZd6b5AZooyyjxSJ5DPBogu7HI0ND6zUJW";
					AccessSecret="yCKkut3WnBljUdM2r5To11NY3Wpm4lBj35jjYnSyP8EVw";
					
					System.out.println("29th looping");
											                               
				}if(requestcount>=4351&&requestcount<=4500){
					 ConsumerKey ="cAMJN57et668WRy4aOQqvC641"; 
					 ConsumerSecret="RcF0ydUX38jSrtsMtaOujA559AyotMf0B5im827rO4Mbv1p1IJ";
					 AccessToken="3492111673-A9eb5A4D2Y4UzPrRsoCvzqlhukvmhlAegsKOTgk";
					 AccessSecret="80LuD7FMIZWUa6oOGFgw7riv6wsC7gYDrBFtlS6PzGzDM";
					
					System.out.println("30th looping");
					requestcount=0;
			      System.out.println("requestcount value "+requestcount);						                               
				}
	            }
}
	
		
		    
	
						catch(Exception e){
						e.printStackTrace();			
						//continue;
						

			
               	}

	            }
}
    
				catch(Exception e){
		        e.printStackTrace();
				
				
			

				 
               	}
				 }

}
		//flush method
 public static String flushFile(List<Object> objects, int d) throws Exception {
		    	
		    	String output1=objects.toString();
		        return output1;
		 }
}

		  

