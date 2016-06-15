package sentiment.nepsnu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractingSentences {
    
    public static void main(String[] args)throws IOException
    {
        
        int count=0;
        
        System.setOut(new PrintStream(new FileOutputStream("/home/storm/eclipse/jee-mars/eclipse/TESTDATA/Productstrain/productstrainNeu.csv")));//writing console output to a file
        
        File i = new File("/home/storm/eclipse/jee-mars/eclipse/TESTDATA/Productstrain/productstrain.csv");//Dumping json file
        
        
        BufferedReader r = new BufferedReader(new FileReader(i));//Reading json file
                
        String x="";
        
        while((x=r.readLine())!=null)// && r.readLine().matches("^[0]*"))
        {
            //Pattern p = Pattern.compile("^[1].*");//positive//negative////neutral
            
            Pattern p = Pattern.compile("(.+?)neutral$");
            
            Matcher m = p.matcher(x);            if (m.find()) {
                
                count++;
                System.out.println(m.group());
            }
        }
        System.out.println(count);
}
}