package sentiment;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finethe01 {
 
    public static void main(String[] args) throws Exception {
    	BufferedReader br = null;
    	 //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("/home/storm/Videos/sentiNetralNamo.txt")), true));

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("/home/storm/datasets/monkeylearn/apple.csv"));

			while ((sCurrentLine = br.readLine()) != null) {
				//String pattern = "^[0]";
				//String pattern1 = "^[1]";
				String pattern = "negative$";
				String patternc= ".*[^negative][^positive][^neutral]$";
				//String patternc=".*(?<!a)$";
	String e="@OneRepublic @Apple Hahha All Apple Users have the Same Problem /"
			+ "Low Batteries..";
//negative
	System.out.println(e.trim());
				 Pattern rc = Pattern.compile(patternc);
			      Matcher mc = rc.matcher(sCurrentLine);
			      // Create a Pattern object
			      Pattern r = Pattern.compile(pattern);
			      Matcher m = r.matcher(sCurrentLine);
			     // Pattern r1 = Pattern.compile(pattern1);
			      //Matcher m1 = r1.matcher(sCurrentLine);
			      if(mc.find()){//System.out.println(/*"Found previous line value: " + */sCurrentLine); }
			      }
			     if (m.find( )) {
			  //System.out.println("" + sCurrentLine);
			    	 }
			      
			     //if(m1.find())
			      //{
			    //	   System.out.println("Found value after else: " + sCurrentLine);}
			} 

		} catch (IOException e) {
			e.printStackTrace();
    }
}
}
