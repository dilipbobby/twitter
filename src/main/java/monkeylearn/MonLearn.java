package monkeylearn;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

public class MonLearn {
	public String monklearn(String tweetText) throws MonkeyLearnException{
	 MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
     String moduleId = "cl_qkjxv9Ly";
     String[] textList = {tweetText};
     MonkeyLearnResponse res = ml.classifiers.classify(moduleId, textList, true);
    // System.out.println( res.arrayResult );
	return res.arrayResult.toJSONString();
	}

}
