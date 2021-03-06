package sentiment.nepsnu;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class TwitterTager {
    static StanfordCoreNLP pipeline;

    public static void init() {
    	Properties props = new Properties();
        props.put("pos.model", "/home/storm/datasets/twitie-tagger/models/gate-EN-twitter.model");
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
      
    }
 public static /*int*/String findSentiment(String tweet) {
             
              tweet = tweet.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");
              int mainSentiment = 0;
              String sentimentS ="";
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(tweet);
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence
                        .get(SentimentAnnotatedTree.class);
               // System.out.println(tree);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                    //System.out.println("**** END OF IF CONDTION IN THE NPL ****");
                }
                 sentimentS = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            }
        }
        System.out.println();
        //return mainSentiment;
        return sentimentS;
    }
}
