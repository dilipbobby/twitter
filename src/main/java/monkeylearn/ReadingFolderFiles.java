package monkeylearn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.monkeylearn.MonkeyLearnException;;

public class ReadingFolderFiles {
	
	public static void main(String args[]) throws IOException, MonkeyLearnException, SQLException {
       String dirPath = "/home/storm/Desktop/mldatasets/topics/subjectivity_html/subj/2002";
        File dir = new File(dirPath);
        //String tablename="objective";
        String tablename="subjective";
        //String obj="obj";
        String subj="subj";
        int count=0;
        HashSet<String> lines = new HashSet<>();
        String[] files = dir.list();
        if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {
            for (String aFile : files) {
                System.out.println(aFile);
                HtmlMonkeyLearn m=new HtmlMonkeyLearn();
               String json=m.FiletoParse(dirPath+"/"+aFile);
             System.out.println(json);
             JSONArray jsonmain=new JSONArray(json);
             JSONArray jsonarry=jsonmain.getJSONArray(0);
             for(int i=0;i<jsonarry.length();i++){
            	 lines.add(aFile);
            	 JSONObject jsonobj=jsonarry.getJSONObject(i);
            	 String para=jsonobj.getString("paragraph_text");
            	 System.out.println(para);
            	 psqlHtmlInsert.psqlHtmlinsertdb(tablename,para,subj,aFile);
            	//  count++;
             	  //System.out.println("Total text in db count"+count);
             }
             
             //subjective
             count++;
         //System.out.println("Total files parsed count"+count);
      	 System.out.println("Total files parsed count"+lines.size());
               //obj 2003 floder 
               /* JSONArray jsonmain=new JSONArray(json);
               JSONArray jsonarry=jsonmain.getJSONArray(0);
             //  System.out.println("JSONARRAY & LEGN"+jsonarry+""+jsonarry.length());
               
               for(int i=0;i<jsonarry.length();i++){
            	   JSONObject jsonobj=jsonarry.getJSONObject(i);
            	   System.out.println(jsonobj);
            	   String paragraph_text=jsonobj.getString("paragraph_text");
               System.out.println("paragraph "+paragraph_text);  
               psqlHtmlInsert.psqlHtmlinsertdb(tablename,paragraph_text,obj,aFile);
        	   count++;
        	   System.out.println("Total files parsed count  "+count);
               }*/
             //2002 folder
               /* JSONArray jsonarry=new JSONArray(json);
               //System.out.println(jsonarry);
               JSONArray jsonarrytwo=jsonarry.getJSONArray(0);
               System.out.println(jsonarrytwo);
               for(int i=0;i<jsonarrytwo.length();i++){
            	JSONObject jsonobj=jsonarrytwo.getJSONObject(0);
            	   String para=jsonobj.getString("paragraph_text");*/
               
            	  // System.out.println(para);
            	  // psqlHtmlInsert.psqlHtmlinsertdb(tablename,para,obj,aFile);
            	   //count++;
            	   //System.out.println("Total files parsed count"+count);
              // }//for close
            }
      }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("/home/storm/Desktop/mldatasets/topics/subjectivity_html/obj/2002/About+a+Boy.html"));
        String line;
        while ( (line=br.readLine()) != null) {
          sb.append(line);
        }//while close
        String nohtml = sb.toString();
		//String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(nohtml);
		Elements p= doc.select("p");//.first();
		//String text = doc.body().text(); //some bold text
		String text = p.text(); //some bold text
		System.out.println(text);
		/*Element link = doc.select("p").first();
		String text = doc.body().text();//some bold text
		System.out.println(text);*/

    }

}
