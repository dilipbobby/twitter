package sentiment;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class sentiment {
	
	public static void main(String[] args) throws IOException {
        //String text = "I am feeling very sad and frustrated";
       // String text = "Is a good boy";
        //String text = "Is a worst boy";
		//String text = "tension free";
		//String text="the quick fox jumps over the lazy dog";
		//String text="Seeing your face makes me reconsider living on this planet.";
//String text="We got my dad an iPhone 6 Father’s Day and he doesn’t know what he’s going and it’s so funny";//Sentiment analysis has never been good.
		//Sentiment analysis has never been this good //This is shit //This is the shit
	//String text="I studied at Harvard";//I studied at Stanford
	//String text="white blood cells destroying an infection";
	//	String text="an infection destroying white blood cells";
		//String text="IT was very fantastic experience. it was a pathetice experience";
		//String text="A picture of new india...Am moved by the love and emotions and exhilarated about the honesty in their expression";
	   // String text="I appeal to everyone to stay safe and indoors, ahead of a Cyclone warning. Do not venture onto the coast. Stock up water & food supplies.";
		//String text="RT @PSBrarOfficial";
		//String text="Professor reveals to students that his assistant was an AI all along";
		String text="Fear for fear. Hate for hate. Bullet for bullet. Where r v heading!? 😞";
	  Properties props = new Properties();
      props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
      //props.put("pos.model", "/home/storm/datasets/twitie-tagger/models/gate-EN-twitter.model");
      ///  props.put("dcoref.score", true);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation annotation = pipeline.process(text);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(sentiment + "\t" + sentence);
        }
    }

}
