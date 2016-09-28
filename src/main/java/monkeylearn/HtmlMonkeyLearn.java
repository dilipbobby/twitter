package monkeylearn;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;
 
public class HtmlMonkeyLearn {
    public static void main( String[] args ) throws MonkeyLearnException {
        MonkeyLearn ml = new MonkeyLearn("60534269bf9e70cf56218e56a960708e1aacd9f8");
        String moduleId = "ex_RK5ApHnN";
        String[] textList = {"This is a text to test your classifier", "This is some more text"};
        MonkeyLearnResponse res = ml.extractors.extract(moduleId, textList);
        System.out.println( res.arrayResult );
    }
}

