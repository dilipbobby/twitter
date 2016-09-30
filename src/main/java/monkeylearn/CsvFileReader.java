package monkeylearn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

public class CsvFileReader {

    public static void main(String[] args) throws SQLException {

        String csvFile = "/home/storm/Desktop/mldatasets/Languages/LangSentences/commalangs.csv";
        BufferedReader br = null;
        int count=0;
        String line = "";
        String cvsSplitBy = ",";
        HashSet<String> lines = new HashSet<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                //System.out.println("Country [code= " + country[0] + " , name=" + country[1] + "]");

                String lang=country[0];
                lines.add(lang);
                System.out.println("Language   "+lang);
                String sentence=country[1];
                System.out.println("Language   "+sentence);
                String tablename="languages";
               // LanguageDBInsert.languageDBInsert(tablename, lang, sentence);
                count++;
                System.out.println("No of sentences inserted "+count);
            }
//5081176
            System.out.println("Languages in the file "+lines.toString()+""+lines.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR "+e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
