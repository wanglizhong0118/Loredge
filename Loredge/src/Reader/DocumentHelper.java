package Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allwi
 */
public class DocumentHelper {

    public static String[] getFileName(String path) {

        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File[] fi = file.listFiles();
        String[] fileName = new String[fi.length];

        for (int i = 0; i < fi.length; i++) {
            if (fi[i].isDirectory()) {
                //System.out.println(fi[i].getName() + " [directory]");
            } else {
                //System.out.println(fi[i].getName());
                fileName[i] = fi[i].getName();
            }
        }

        return fileName;
    }

    public static List<String> getStopWord() throws FileNotFoundException, IOException {

        String stop_word_address = "C://Users/allwi/Documents/Loredge/stop_words.txt";

        List<String> stop_word_list = new ArrayList();

        try (FileInputStream input_TXT = new FileInputStream(stop_word_address)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input_TXT, "utf-8"), 5 * 1024 * 1024);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stop_words = line.split("\\s+");

                for (String stop_word : stop_words) {
                    stop_word_list.add(stop_word);
                }
            }
        }
        return stop_word_list;
    }

}
