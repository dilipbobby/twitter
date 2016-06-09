package sentiment;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finethe01 {
 
    public static void main(String[] args) throws Exception {
    	BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("/home/storm/Downloads/-.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String pattern = "^[0]";
				String pattern1 = "^[1]";

			      // Create a Pattern object
			      Pattern r = Pattern.compile(pattern);
			      Matcher m = r.matcher(sCurrentLine);
			      Pattern r1 = Pattern.compile(pattern1);
			      Matcher m1 = r1.matcher(sCurrentLine);
			     if (m.find( )) {
			   // System.out.println("Found value: " + sCurrentLine);
			    	 }
			      
			     if(m1.find())
			      {
			    	   System.out.println("Found value after else: " + sCurrentLine);}
			} 

		} catch (IOException e) {
			e.printStackTrace();
    }
}
}
