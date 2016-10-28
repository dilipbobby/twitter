package monkeylearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegXExample {
	public static void main(String args[]){
		String line="<PERSON>Vinay Sharma</PERSON> and three others have appealed against their death sentence in the <ORGANIZATION>Supreme Court</ORGANIZATION>.";
		 String pattern = "<.*?>";
		 Pattern r = Pattern.compile(pattern);
		 Matcher m = r.matcher(line);
		 if (m.find( )) {
	         System.out.println("Found value: " + m );
		 }
	}

}
