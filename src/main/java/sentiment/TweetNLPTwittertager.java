package sentiment;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class TweetNLPTwittertager {

    public static void main(String[] args) {
    //    String text = "My sister won't tell me where she hid my food. She's fueling my anorexia. #bestsisteraward #not 😭💀";
String text="Let's see those tax returns @realDonaldTrump  #weakdonald";
        MaxentTagger tagger = new MaxentTagger("/home/storm/datasets/twitie-tagger/models/gate-EN-twitter.model");

        String taggedText = tagger.tagString(text);
        System.out.println(taggedText);
    }
}
