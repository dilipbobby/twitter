package monkeylearn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TweetWriter {
	
	public  static void SentiWriter(String filepath,String tweet,String Sentiment){
		
		try {

			File file = new File(filepath);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(tweet+","+Sentiment);
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
