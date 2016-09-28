package monkeylearn;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class ParseHTMLWithTika {
  public static void main(String args[]) throws Exception {
//NOT WORKING
    InputStream is = null;
    try {

         is = new FileInputStream("/home/storm/Desktop/mldatasets/topics/subjectivity_html/obj/2002/Aankhen.html");
         ContentHandler contenthandler = new BodyContentHandler();
         Metadata metadata = new Metadata();
         Parser parser = new AutoDetectParser();
         parser.parse(is, contenthandler, metadata, new ParseContext());
        
         System.out.println(contenthandler.toString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
        if (is != null) is.close();
    }
  }
}