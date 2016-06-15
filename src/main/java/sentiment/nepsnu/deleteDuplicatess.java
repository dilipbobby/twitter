package sentiment.nepsnu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

import com.monkeylearn.MonkeyLearnException;

import twitter4j.JSONException;

public class deleteDuplicatess {

    public static void main( String[] args ) throws MonkeyLearnException, JSONException, IOException {
    
    	deleteDuplicatess s= new deleteDuplicatess();
    	File f=new File("/home/storm/eclipse/jee-mars/eclipse/netural.txt");
    	s.deleteDuplicates(f);
    	
    
    }
void deleteDuplicates(File filename) throws IOException{
    @SuppressWarnings("resource")
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    Set<String> lines = new LinkedHashSet<String>();
    String line;
    String delims = " ";
    System.out.println("Read the duplicate contents now and writing to file");
    while((line=reader.readLine())!=null){
        line = line.trim(); 
       // StringTokenizer str = new StringTokenizer(line, delims);
       // while (str.hasMoreElements()) {
           // line = (String) str.nextElement();
            System.out.println(line);
            lines.add(line);
            System.out.println(lines.size());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for(String unique: lines){
                writer.write(unique+"\n");               
            }
           // writer.close();
        //}
    }
    System.out.println(lines);
    System.out.println("Duplicate removal successful");
}


}
