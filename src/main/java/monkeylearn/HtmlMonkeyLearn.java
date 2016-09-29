package monkeylearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;
 
public class HtmlMonkeyLearn {
   
public String FiletoParse(String filepath) throws MonkeyLearnException, IOException {
    try{    
	MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
        String moduleId = "ex_RK5ApHnN";
       //String[] textList = {"This is a text to test your classifier", "This is some more text"};
        StringBuilder sb = new StringBuilder();
      //  BufferedReader br = new BufferedReader(new FileReader("/home/storm/Desktop/mldatasets/topics/subjectivity_html/obj/2002/Aankhen.html"));
  
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        while ( (line=br.readLine()) != null) {
          sb.append(line);
        }//while close
        String nohtml = sb.toString();//.replaceAll("\\<.*?>","");
        String[] textList = {nohtml};
        MonkeyLearnResponse res = ml.extractors.extract(moduleId, textList);
        System.out.println( res.arrayResult );
        return res.arrayResult.toString();
   
    }
    catch(Exception e){System.out.println("FILE NOT processed"+e);
    int count=0;
    count++;
    System.out.println("files not processed number "+count);
    }
	return filepath;
}
}

