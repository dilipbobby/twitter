package sentiment.nepsnu;

import java.util.HashMap;
import java.util.Map;

class calculate{
 
	
	public static Map<String, Object> calculateMood(Map<String, Integer> counts) {
    if(counts.isEmpty()) {
        return null;
    }

    float moodValue = 0;
    int total = 0;
    if(counts.containsKey("VeryNegative")) {
        moodValue -= 2 * counts.get("VeryNegative");
        total += counts.get("VeryNegative");
    }
    if(counts.containsKey("Negative")) {
        moodValue -= counts.get("Negative");
        total += counts.get("Negative");
    }
    if(counts.containsKey("Neutral")) {
        total += counts.get("Neutral");
    }
    if(counts.containsKey("Positive")) {
        moodValue += counts.get("Positive");
        total += counts.get("Positive");
    }
    if(counts.containsKey("VeryPositive")) {
        moodValue += 2 * counts.get("VeryPositive");
        total += counts.get("VeryPositive");
    }

    if(total > 0) {
        moodValue /= total;
    }

    String moodClass;
    if(moodValue > 0.05) {
        moodClass = new String("pos");
    }
    else if(moodValue < -0.05) {
        moodClass = new String("neg");
    }
    else {
        moodClass = new String("neu");
    }

    Map<String, Object> mood = new HashMap<String, Object>();
    mood.put("moodValue", moodValue);
    mood.put("moodClass", moodClass);
    return mood;
}
}

