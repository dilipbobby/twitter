package monkeylearn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public class XMLToJson {
	
	public static void main(String args[]) throws IOException, SQLException {
	       String dirPath = "/home/storm/Desktop/mldatasets/topics/femaleblogs";
	        File dir = new File(dirPath);
	        int count=0;
	        String tablename="femaleblogtxt";
	        String gender="female";
	        String[] files = dir.list();
	        if (files.length == 0) {
	            System.out.println("The directory is empty");
	        } else {
	            for (String aFile : files) {
	                //System.out.println(aFile);
	                //System.out.println(XML.toJSONObject(aFile).toString());
	                try{    
	                	
	                	 StringBuilder sb = new StringBuilder();
	                     //  BufferedReader br = new BufferedReader(new FileReader("/home/storm/Desktop/mldatasets/topics/subjectivity_html/obj/2002/Aankhen.html"));
	                      BufferedReader br = new BufferedReader(new FileReader(dirPath+"/"+aFile));
	                       String line;
	                       while ( (line=br.readLine()) != null) {
	                         sb.append(line);
	                       }//while close
	                       String xml = sb.toString();
	                    // System.out.println(xml);
	                    try{
	                       String json=XML.toJSONObject(xml).toString();
	                       JSONObject jsonmain=new JSONObject(json);
	                       JSONObject jsonobj=jsonmain.getJSONObject("Blog");
	                       JSONArray postarray=jsonobj.getJSONArray("post");
	                      // System.out.println(postarray);
	                       for(int i=0;i<postarray.length();i++){
	                    	   String text=postarray.getString(i);
	                    	   System.out.println("This is the text " +text);
	                    	   psqlGenderTxtInsert g=new psqlGenderTxtInsert();
	                    	   g.psqMaleinsertdb(tablename, text, gender);
	                    	   System.out.println("INSERT DONE ");
	                       
	                       }
	                     //  System.out.println(json);
	                    }catch(Exception e){
	                    	//System.out.println(e);
	                    	count++;
	                    	System.out.println("Im in for count" +count);
	                    	
	                    }
	                }//try close
	                catch(Exception e){System.out.println("FILE NOT processed"+e);
}//catch close
	}
}//else close
	}//main close
}//class close