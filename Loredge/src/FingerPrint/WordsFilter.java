package FingerPrint;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 *
 * @author allwi
 */
public class WordsFilter {

    public static boolean isWord(String word, List stop_words) {

        boolean isWord = false;
        if (isNumber(word) == false
                && isTooShort(word) == false
                && isStopWord(word, stop_words) == false) {
            isWord = true;
        }

        return isWord;
    }

    /**
     * determine whether the word is too short or not
     *
     * @param word the word
     * @return true if too short, otherwise false
     */
    public static boolean isTooShort(String word) {

        boolean isTooShort = false;
        if (word.length() > 2) {
        } else {
            isTooShort = true;
        }

        return isTooShort;
    }

    /**
     * determine whether the word is a number or not
     *
     * @param word the word
     * @return true if it is a number, otherwise false
     */
    public static boolean isNumber(String word) {

        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(word).matches();
    }

    /**
     * determine whether the word is a stop word
     *
     * @param word the word
     * @param stop_words the list of stop words
     * @return
     */
    public static boolean isStopWord(String word, List stop_words) {

        boolean isStopWord = false;
        if (stop_words.contains(word)) {
            isStopWord = true;
        }

        return isStopWord;
    }

    public static TreeMap<String, Integer> minApprFilter(int min_appr, Map<String, Integer> top_word) {
        Set top_word_set = top_word.entrySet();
        Iterator top_word_iterator = top_word_set.iterator();
        TreeMap acc = new TreeMap<>();
        for (int i = 0; i < top_word.size(); i++) {
            Map.Entry<String, Integer> top_entry = (Map.Entry) top_word_iterator.next();
            if (top_entry.getValue() > min_appr) {
                acc.put(top_entry.getKey(), top_entry.getValue());
            }
        }
        return acc;
    }
}
