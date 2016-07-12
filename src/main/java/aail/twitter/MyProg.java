package aail.twitter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;


public class MyProg {

    public static void main(String[] args) throws IOException {
        String target_dir = "/home/storm/Documents/datasetsTopics";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        int count=0;
        int a=files.length;
        System.out.println(a);

        for (File f : files) {
            if(f.isFile()) {
                BufferedReader inputStream = null;

                try {
                    inputStream = new BufferedReader(
                                    new FileReader(f));
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        System.out.println(line);
                    }

                }
                
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
           }
            }
        count++;
    }
}
