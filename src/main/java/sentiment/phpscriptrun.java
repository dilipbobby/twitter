package sentiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class phpscriptrun {
	  public static void main(String[] args) throws IOException, InterruptedException {
	  
	  phpscriptrun p=new phpscriptrun();
	  String scriptName="/home/storm/Videos/exphp.php";
	  //p.execPHP(scriptName);
	  System.out.println();
	  Process pp = Runtime.getRuntime().exec("php foo.php");

      pp.waitFor();

      String line;

      BufferedReader error = new BufferedReader(new InputStreamReader(pp.getErrorStream()));
      while((line = error.readLine()) != null){
          System.out.println(line);
      }
      error.close();

      BufferedReader input = new BufferedReader(new InputStreamReader(pp.getInputStream()));
      while((line=input.readLine()) != null){
          System.out.println(line);

      }

      input.close();

      OutputStream outputStream = pp.getOutputStream();
      PrintStream printStream = new PrintStream(outputStream);
      printStream.println();
      printStream.flush();
      printStream.close();
	  
	  }
	  
	  
	  
	  public String execPHP(String scriptName/*, String param*/) {
	      StringBuilder output = new StringBuilder();
  
		  try {
		      String line;
		    //  StringBuilder output = new StringBuilder();
		      Process p = Runtime.getRuntime().exec("php " + scriptName/*+ " " + param*/);
		      BufferedReader input =
		        new BufferedReader
		          (new InputStreamReader(p.getInputStream()));
		      while ((line = input.readLine()) != null) {
		          output.append(line);
		      }
		      input.close();
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		    return output.toString();
		  }
}
