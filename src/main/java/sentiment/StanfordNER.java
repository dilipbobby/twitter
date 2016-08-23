package sentiment;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class StanfordNER {
	/**
	 * identify Name,organization location etc entities and return Map<List>
	 * 
	 * @param text
	 *            -- data
	 * @param model
	 *            - Stanford model names out of the three models
	 * @return
	 */
	public static LinkedHashMap<String, LinkedHashSet<String>> identifyNER(String text, String model) {
		LinkedHashMap<String, LinkedHashSet<String>> map = new<String, LinkedHashSet<String>> LinkedHashMap();
		String serializedClassifier = model;
		System.out.println(serializedClassifier);
		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		List<List<CoreLabel>> classify = classifier.classify(text);
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {

				String word = coreLabel.word();
				String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				if (!"O".equals(category)) {
					if (map.containsKey(category)) {
						// key is already their just insert in arraylist
						map.get(category).add(word);
					} else {
						LinkedHashSet<String> temp = new LinkedHashSet<String>();
						temp.add(word);
						map.put(category, temp);
					}
					System.out.println(word + ":" + category);
				}

			}

		}
		return map;
	}

	public static void main(String args[]) {
		// String content="In 2009, Elliot Turner launched AlchemyAPI to process
		// the written word, with all of its quirks and nuances, and got
		// immediate traction. That first month, the company's eponymous
		// language-analysis API processed 500,000 transactions. Today it's
		// processing three billion transactions a month, or about 1,200 a
		// second. “That's a growth rate of 6,000 times over three years,” touts
		// Turner. “Context is super-important,” he adds. “'I'm dying' is a lot
		// different than 'I'm dying to buy the new iPhone.'” “As we move into
		// new markets, we're going to be making some new hires," +"Turner
		// says."+ "We knocked down some walls and added 2,000 square feet to
		// our office.” “We're providing the ability to translate human language
		// in the form of web pages and documents into actionable data,” Turner
		// says. Clients include Walmart, PR Newswire and numerous publishers
		// and advertising networks. “This allows a news organization to detect
		// what a person likes to read about,” says Turner of publishers and
		// advertisers.";
		String content = "Indian wrestler Narsingh Yadav has been ousted from Olympics as the CAS slapped him with a four-year ban, while hearing into the World Anti-Doping Agency's appeal against the clean chit given to him in a dope scandal by NADA. Narsingh's name appeared in the official schedule released on Thursday after he underwent the regular weigh-in. He was scheduled to take on France's Zelimkhan Khadjiev in the qualification round. ";
		// String content="Good afternoon Rajat Raina, how are you today?";
		// String content="heymailme143,vanaja Potla tension freeeeeeeeee";
		System.out.println(identifyNER(content,
				"/home/storm/Videos/stanford-ner-2014-01-04/2015/edu/stanford/nlp/models/ner/english.muc.7class.distsim.crf.ser.gz")
						.toString());

		System.out.println(identifyNER(content,
				"/home/storm/Videos/stanford-ner-2014-01-04/classifiers/english.conll.4class.distsim.crf.ser.gz")
						.toString());
		System.out.println(identifyNER(content,
				"/home/storm/Videos/stanford-ner-2014-01-04/classifiers/english.muc.7class.distsim.crf.ser.gz")
						.toString());
	}

}