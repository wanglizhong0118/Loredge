package FingerPrint;

import util.MapSorter;
import java.util.*;

/**
 * Data structure includes [originWords, filiterWords, treeMap]
 *
 * @author allwi
 */
public class FingerPrint {

    private final int originWords;
    private final int filterWords;
    private final TreeMap<String, Integer> treeMap;

    public FingerPrint(int originWords, int filterWords, TreeMap<String, Integer> treeMap) {
        this.originWords = originWords;
        this.filterWords = filterWords;
        this.treeMap = treeMap;
    }

    public int getOriginWords() {
        return originWords;
    }

    public int getFilterWords() {
        return filterWords;
    }

    public TreeMap<String, Integer> getTreeMap() {
        return treeMap;
    }

    /**
     * put a new word into the tree-map;
     *
     * @param word: the new node needs to be put in the tree
     */
    public void put(String word) {
        if (treeMap.get(word) == null) {
            treeMap.put(word, 1);
        } else {
            treeMap.put(word, treeMap.get(word) + 1);
        }
    }

    /**
     * remove the mapping for this key from the word tree
     *
     * @param word the key
     */
    public void remove(String word) {
        treeMap.remove(word);
    }

    /**
     * get all keys as a set in the tree map
     *
     * @return a set of keys(words)
     */
    public Set keySet() {
        return treeMap.keySet();
    }

    /**
     * sort a tree-map by value in ascending order
     *
     * @return the sorted map
     */
    public Map getAscMap() {
        Map AscMap = MapSorter.sortByValuesAsc(treeMap);
        return AscMap;
    }

    /**
     * sort a tree-map by value in descending order
     *
     * @return the sorted map
     */
    public Map getDescMap() {
        Map DescMap = MapSorter.sortByValuesDesc(treeMap);
        return DescMap;
    }

    /**
     * get a new map of top-words from a descending-order tree-map
     *
     * @param top_number the number of top words;
     * @return a new map of top words in descending order;
     */
    public Map topWordsMap(int top_number) {

        Map sorted_map = getDescMap();
        Set top_set = sorted_map.entrySet();
        Iterator top_iterator = top_set.iterator();

        Map top_map = new TreeMap<>();

        for (int i = 0; i < top_number; i++) {
            Map.Entry top = (Map.Entry) top_iterator.next();
            //System.out.println("<" + top.getKey() + ", " + top.getValue() + ">");
            top_map.put(top.getKey(), top.getValue());
        }
        return MapSorter.sortByValuesDesc(top_map);
    }

    /**
     * get the number of the words in the top list
     *
     * @return the number of words
     */
    public int getTopWords() {
        int top_words = filterWords / 50;
        return top_words;
    }

    /**
     * get the least appearance of the words in the top list
     *
     * @return the least appearance
     */
    public int getMinApprs() {
        int min_appr = filterWords / 500;
        return min_appr;
    }

}
