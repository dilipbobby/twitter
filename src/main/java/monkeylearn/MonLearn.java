package monkeylearn;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

public class MonLearn {
	public String monklearn(String tweetText) throws MonkeyLearnException{
	 MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
	 // 7d5619e0c6d260d2a9aabf4adf3d6e8c8c9031e8//jameer
	 //60534269bf9e70cf56218e56a960708e1aacd9f8//me
	 //3f2343c8128db976cb6c265c91e6da3130f5a4f5 //jaya
	 //58b0c99e34d4d9def600358cad3e4fd43d6badec//dilip-aadhya
	 //dc1458466045d2681d9decd8474b9edd50e7cdf1//imailyou
	 //1dad2b4f7f8b11ce91c46c66f7fec9f1b9c13d1d //heymaile143
	 //130688883b90a3a803f91b1b6ce4302457f53a30/kerthi
	 //432ddfc8740cbd780ce05a222811df62f73bdd69//KISHORE
     String moduleId = "cl_qkjxv9Ly";
     String[] textList = {tweetText};
     MonkeyLearnResponse res = ml.classifiers.classify(moduleId, textList, true);
    // System.out.println( res.arrayResult );
	return res.arrayResult.toJSONString();
	}

}
