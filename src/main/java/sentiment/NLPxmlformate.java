package sentiment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.stanford.nlp.dcoref.Document;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.XMLOutputter;
import nu.xom.Serializer;

public class NLPxmlformate {
	
	public static void main(String[] args) throws IOException {

		 Properties props = new Properties();
		 props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		 StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

//		 // read some text in the text variable
		 String text = "We won the game in India."; // Add your text here!
//
//		 String text="Vinay Sharma and three others have appealed against their death sentence in the Supreme Court.";
	 // create an empty Annotation just with the given text
	 Annotation document = new Annotation(text);

	 // run all Annotators on this text
		 pipeline.annotate(document);
//
		 nu.xom.Document xmldoc = XMLOutputter.annotationToDoc(document, pipeline);
//		
		 
		 // below is a tweaked version of XMLOutputter.writeXml()
		 ByteArrayOutputStream sw = new ByteArrayOutputStream();
		 Serializer ser = new Serializer(sw);
	     ser.setIndent(0);
	     ser.setLineSeparator("\n"); // gonna kill this in a moment
		     ser.write(xmldoc);
	     ser.flush();
	 String xmlstr = sw.toString();
		 xmlstr = xmlstr.replace("\n", "");
	 System.out.println(xmlstr);
	
		/* Properties props = new Properties();
		    props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		    Annotation document = new Annotation("Four score and seven years ago.");
		    pipeline.annotate(document);
		    FileOutputStream os = new FileOutputStream(new File("./target/", "nlp.xml"));
		    pipeline.xmlPrint(document, os);*/
		   
	       
	
	}
	}


