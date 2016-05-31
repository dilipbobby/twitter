package sentiment.nepsnu;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class DBselect {

	public static void main(String args[]) throws FileNotFoundException {

		// System.setOut(new PrintStream(new BufferedOutputStream(new
		// FileOutputStream("/home/storm/Videos/senti.txt")), true));

		Connection c = null;
		Statement stmt = null;
		String text = "";
		int textcount = 0;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.0.14.187:5432/sm_livefeed?user=postgres");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
		//	ResultSet rs = stmt.executeQuery("select text from texts where text IS NOT NULL;");
		ResultSet rs = stmt.executeQuery("select tweet_text from twitter_tweets where tweet_text IS NOT NULL;");
			while (rs.next()) {
				text = rs.getString("tweet_text");
				text = text.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");				//text.trim();
				
				System.out.println("DATABAST TEXT -->" + text);
				
			if (textcount < 1001) {
					System.out.println("IF CONDITOIN TEXT -->"+text);
                   //System.out.println("METHOD TEXT   "+ DBselect.Screen(text));
					
					File file = new File("/home/storm/Videos/erroroutput.txt");
					FileOutputStream fOut = new FileOutputStream(file, true);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);
					osw.write(text);
					osw.flush();
					osw.close();
					textcount++;
					
					//System.out.println("TextCount" + textcount);
				}else{break;}

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	public static String Screen(String text){
	
		System.out.println(text);
		return text;
	}//quiere erradicar a los hackers: 


	//Los hackers son una de las grandes preocupaciones de muchos usuarios de... https://t.co/Uv94cNlZJZ
}