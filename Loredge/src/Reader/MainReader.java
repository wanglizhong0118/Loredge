package Reader;

import FingerPrint.FingerPrint;
import java.io.*;
import java.util.List;
import java.util.TreeMap;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.*;
import FingerPrint.WordsFilter;

/**
 *
 * @author allwi
 */
public class MainReader {

    /**
     * Use Apache PDFBox to read a pdf file into a txt file
     *
     * @param pdf_address address of the input pdf file
     * @param txt_address address of the output txt file
     * @throws IOException if any address is not valid
     */
    public static void articleReader(String pdf_address, String txt_address) throws IOException {

        FileInputStream input_PDF = new FileInputStream(pdf_address);
        FileOutputStream output_TXT = new FileOutputStream(txt_address);

        PDDocument pdfDocument = PDDocument.load(input_PDF);
        Writer writer = new OutputStreamWriter(output_TXT, "utf-8");

        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        stripper.writeText(pdfDocument, writer);

        writer.close();
        pdfDocument.close();
    }

    /**
     * Read in a txt file and use the content to create a word trie
     *
     * @param txt_address address of the input txt file
     * @return a defined construction word trie
     * @throws IOException if address is not valid
     */
    public static FingerPrint constructReader(String txt_address) throws IOException {

        List<String> stop_words = DocumentHelper.getStopWord();
        TreeMap<String, Integer> tree = new TreeMap<>();

        int words_counter_before = 0;
        int words_counter_after = 0;

        try (FileInputStream input_TXT = new FileInputStream(txt_address);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input_TXT, "utf-8"), 5 * 1024 * 1024)) {

            String line;

            String[] heads = new String[2];
            String str;
            for (int i = 0; i < 2; i++) {
                str = reader.readLine();
                heads[i] = str;
            }

            while ((line = reader.readLine()) != null) {
                String h1 = heads[0];
                String h2 = heads[1];
                line = line.replace(h1, " ");
                line = line.replace(h2, " ");

                line = line.replaceAll("[^A-Za-z0-9 &&[^’']]", " ");
                line = line.replaceAll("’", "'");
                line = line.replaceAll("n't", " not");
                line = line.replaceAll("'", " ");

                String[] words = line.split("\\s+");

                for (String s : words) {
                    words_counter_before++;
                    String word = s.toLowerCase();
                    boolean isWord = WordsFilter.isWord(word, stop_words);

                    if (isWord == true) {
                        if (tree.get(word) == null) {
                            tree.put(word, 1);
                        } else {
                            tree.put(word, tree.get(word) + 1);
                        }
                        words_counter_after++;
                    }
                }
            }
        }
        FingerPrint fingerPrint = new FingerPrint(words_counter_before, words_counter_after, tree);
        return fingerPrint;
    }

}
