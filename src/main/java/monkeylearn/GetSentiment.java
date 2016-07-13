package monkeylearn;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.monkeylearn.MonkeyLearnException;

public class GetSentiment {
	
public static void main(String args[]) throws FileNotFoundException, MonkeyLearnException, IOException{
		
		
		WriteToFile news=new WriteToFile();
		
		String newsip="";
		String newsop="";
		
		String personsip="";
		String presonsop="";
		
		String companyip="";
		String companyop="";
		
		String politicsip="";
		String Politicsop="";
		
		news.SetPathforSentiment(newsip, newsop);
		
	}

}
