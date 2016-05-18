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
//String text="The movie was TOO  good";//Sentiment analysis has never been good.
		//Sentiment analysis has never been this good //This is shit //This is the shit
		String text="This is the shit";
		Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation annotation = pipeline.process(text);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(sentiment + "\t" + sentence);
        }
    }

}
