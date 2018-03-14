package DatabaseOperating;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 *
 * @author allwi
 */
public class DatabaseReader {

    /**
     * Read the database into a treemap <words, appearance>.
     *
     *
     * @param txt_address address of the database
     * @return a defined construction word trie
     * @throws IOException if the address is not valid
     */
    public static TreeMap<String, Integer> featureReader(String txt_address) throws IOException {
        TreeMap feature = new TreeMap<>();
        try (final FileInputStream input_TXT = new FileInputStream(txt_address);
                final BufferedReader reader = new BufferedReader(new InputStreamReader(input_TXT, "utf-8"), 5 * 1024 * 1024)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\r");
                for (String s : words) {
                    String[] sa = s.split("\\s+");
                    feature.put(sa[0], Integer.parseInt(sa[1]));
                }
            }
        }
        return feature;
    }

}
