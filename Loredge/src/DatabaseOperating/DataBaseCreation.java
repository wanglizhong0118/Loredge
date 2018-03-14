package DatabaseOperating;

import Reader.MainReader;
import FingerPrint.FingerPrint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import Reader.DocumentHelper;
import util.MapSorter;
import util.OutPuter;
import FingerPrint.WordsFilter;

/**
 *
 * @author allwi
 */
public class DataBaseCreation {

    public static void ver_Complete(String document_path, String txt_path, String result_path, String info_path) throws IOException {

        String[] all_fileNames = DocumentHelper.getFileName(document_path);

        for (String fileName : all_fileNames) {

            String pdf_path = document_path + fileName;

            long startTime = System.currentTimeMillis();

            MainReader.articleReader(pdf_path, txt_path);
            FingerPrint word_trie = MainReader.constructReader(txt_path);

            Map allwords = word_trie.getTreeMap();

            long stopTime = System.currentTimeMillis();

            long executionTime = stopTime - startTime;

            Map featureMap = MapSorter.sortByValuesDesc(allwords);
            Iterator<Entry<String, Integer>> it = featureMap.entrySet().iterator();

            String feature = "";
            while (it.hasNext()) {
                Entry<String, Integer> e = it.next();
                feature += e.getKey() + " " + e.getValue() + "\r\n";
            }
            String data_path = fileName.substring(0, fileName.length() - 4);
            String result_path_file = result_path + data_path + ".txt";
            OutPuter.text_writer(feature, result_path_file);

            String pdf_info = fileName + " " + word_trie.getOriginWords() + " " + word_trie.getFilterWords() + " " + executionTime + " ms \r\n";
            OutPuter.text_writer(pdf_info, info_path);
        }
    }

    public static void ver_FeatureFull(String document_path, String txt_path, String result_path, String info_path) throws IOException {

        String[] all_fileNames = DocumentHelper.getFileName(document_path);

        for (String fileName : all_fileNames) {

            String pdf_path = document_path + fileName;

            long startTime = System.currentTimeMillis();

            MainReader.articleReader(pdf_path, txt_path);
            FingerPrint word_trie = MainReader.constructReader(txt_path);

            int topWords = word_trie.getTopWords();
            int minApprs = word_trie.getMinApprs();
            Map topWordsMap = word_trie.topWordsMap(topWords);
            Map topWordsMap_condition = WordsFilter.minApprFilter(minApprs, topWordsMap);

            long stopTime = System.currentTimeMillis();

            long executionTime = stopTime - startTime;

            Map featureMap = MapSorter.sortByValuesDesc(topWordsMap_condition);
            Iterator<Entry<String, Integer>> it = featureMap.entrySet().iterator();

            String feature = "";
            while (it.hasNext()) {
                Entry<String, Integer> e = it.next();
                feature += e.getKey() + " " + e.getValue() + "\r\n";
            }
            String data_path = fileName.substring(0, fileName.length() - 4);
            String result_path_file = result_path + data_path + ".txt";
            OutPuter.text_writer(feature, result_path_file);

            String pdf_info = fileName + " " + word_trie.getOriginWords() + " " + word_trie.getFilterWords() + " " + executionTime + " ms \r\n";
            OutPuter.text_writer(pdf_info, info_path);
        }
    }

    public static void ver_FeatureHalf(String document_path, String txt_path, String result_path, String info_path) throws IOException {

        String[] all_fileNames = DocumentHelper.getFileName(document_path);

        for (String fileName : all_fileNames) {

            String pdf_path = document_path + fileName;

            long startTime = System.currentTimeMillis();

            MainReader.articleReader(pdf_path, txt_path);
            FingerPrint word_trie = MainReader.constructReader(txt_path);

            int topWords = word_trie.getTopWords() / 2;
            int minApprs = word_trie.getMinApprs() * 2;
            Map topWordsMap = word_trie.topWordsMap(topWords);
            Map topWordsMap_condition = WordsFilter.minApprFilter(minApprs, topWordsMap);

            long stopTime = System.currentTimeMillis();

            long executionTime = stopTime - startTime;

            Map featureMap = MapSorter.sortByValuesDesc(topWordsMap_condition);
            Iterator<Entry<String, Integer>> it = featureMap.entrySet().iterator();

            String feature = "";
            while (it.hasNext()) {
                Entry<String, Integer> e = it.next();
                feature += e.getKey() + " " + e.getValue() + "\r\n";
            }
            String data_path = fileName.substring(0, fileName.length() - 4);
            String result_path_file = result_path + data_path + ".txt";
            OutPuter.text_writer(feature, result_path_file);

            String pdf_info = fileName + " " + word_trie.getOriginWords() + " " + word_trie.getFilterWords() + " " + executionTime + " ms \r\n";
            OutPuter.text_writer(pdf_info, info_path);
        }
    }

    public static void ver_FeatureQuarter(String document_path, String txt_path, String result_path, String info_path) throws IOException {

        String[] all_fileNames = DocumentHelper.getFileName(document_path);

        for (String fileName : all_fileNames) {

            String pdf_path = document_path + fileName;

            long startTime = System.currentTimeMillis();

            MainReader.articleReader(pdf_path, txt_path);
            FingerPrint word_trie = MainReader.constructReader(txt_path);

            int topWords = word_trie.getTopWords() / 4;
            int minApprs = word_trie.getMinApprs() * 4;
            Map topWordsMap = word_trie.topWordsMap(topWords);
            Map topWordsMap_condition = WordsFilter.minApprFilter(minApprs, topWordsMap);

            long stopTime = System.currentTimeMillis();

            long executionTime = stopTime - startTime;

            Map featureMap = MapSorter.sortByValuesDesc(topWordsMap_condition);
            Iterator<Entry<String, Integer>> it = featureMap.entrySet().iterator();

            String feature = "";
            while (it.hasNext()) {
                Entry<String, Integer> e = it.next();
                feature += e.getKey() + " " + e.getValue() + "\r\n";
            }
            String data_path = fileName.substring(0, fileName.length() - 4);
            String result_path_file = result_path + data_path + ".txt";
            OutPuter.text_writer(feature, result_path_file);

            String pdf_info = fileName + " " + word_trie.getOriginWords() + " " + word_trie.getFilterWords() + " " + executionTime + " ms \r\n";
            OutPuter.text_writer(pdf_info, info_path);
        }
    }
}
