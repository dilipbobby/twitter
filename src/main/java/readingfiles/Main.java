package readingfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//http://www.vogella.com/tutorials/JavaIO/article.html
public class Main {
  public static void main(String[] args) throws IOException {
    String input = Main.readTextFile("/home/storm/Documents/datasetsTopics/Industries.csv");
    System.out.println(input);
  }
  
  
	  public static String readTextFile(String fileName) throws IOException {
	    String content = new String(Files.readAllBytes(Paths.get(fileName)));
	    return content;
} 
}
