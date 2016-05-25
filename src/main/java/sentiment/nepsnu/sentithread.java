package sentiment.nepsnu;

import java.util.Map;

public class sentithread extends Thread{
	Map<String, Integer> NLPcount;
    public sentithread(Map<String, Integer> NLPcount){
    	   this.NLPcount = NLPcount;
    }
    public void run(){
        System.out.println(calculate.calculateMood(NLPcount));
    }
    public Map<String, Object> getValue() {
        return calculate.calculateMood(NLPcount);
    }
}