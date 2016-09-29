package monkeylearn;

import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import org.jsoup.Jsoup;

public class HtmlJsuop {
  private HtmlJsuop() {}

  public static String extractText(Reader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(reader);
    String line;
    while ( (line=br.readLine()) != null) {
      sb.append(line);
    }
    String textOnly = Jsoup.parse(sb.toString()).text();
    return textOnly;
  }

  public final static void main(String[] args) throws Exception{
    FileReader reader = new FileReader
          ("/home/storm/Desktop/mldatasets/topics/subjectivity_html/obj/2002/Aankhen.html");
    System.out.println(HtmlJsuop.extractText(reader));
  }
}

