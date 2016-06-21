package monkeylearn;



import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;
 
public class MonkeyLearnt {
    public static void main( String[] args ) throws MonkeyLearnException {
        MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
        String moduleId = "cl_qkjxv9Ly";
        String[] textList = {"RT @ARTEM_KLYUSHIN: Here are some of the highlights from last night's #GOPdebate"};
        MonkeyLearnResponse res = ml.classifiers.classify(moduleId, textList, true);
        System.out.println( res.arrayResult );
    }
}


