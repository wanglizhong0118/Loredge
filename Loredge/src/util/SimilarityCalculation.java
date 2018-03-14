package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author allwi
 */
public class SimilarityCalculation {

    /**
     * Extract values of the map to create a vector
     *
     * @param wordMap input map contains kay-value pair
     * @return an integer vector contains all values in input map
     */
    public static int[] createVector(TreeMap<String, Integer> wordMap) {

        int map_size = wordMap.size();
        int[] word_array = new int[map_size];

        Set set = wordMap.entrySet();
        Iterator iterator = set.iterator();

        for (int i = 0; i < map_size; i++) {
            Map.Entry<String, Integer> entry = (Map.Entry) iterator.next();
            word_array[i] = entry.getValue();
        }
        return word_array;
    }

    /**
     * iterate all words in mapA, and use these words as keys to find their
     * values in mapB, and then use the keys and values to create a new map
     *
     * @param mapA input mapA
     * @param mapB target mapB
     * @return map that contians all mapA's keys and their appearance in mapB
     *
     */
    public static TreeMap<String, Integer> extraVector(TreeMap<String, Integer> mapA, TreeMap<String, Integer> mapB) {

        TreeMap<String, Integer> Acc = new TreeMap<>();

        int sizeA = mapA.size();
        Set setA = mapA.entrySet();
        Iterator iteratorA = setA.iterator();

        for (int i = 0; i < sizeA; i++) {
            Map.Entry<String, Integer> entryA = (Map.Entry) iteratorA.next();
            String s = entryA.getKey();
            if (mapB.get(s) == null) {
                Acc.put(s, 0);
            } else {
                Acc.put(s, mapB.get(s));
            }
        }
        return Acc;
    }

    /**
     * Law of cosines. Key Algorithm to find the similarity
     *
     * @param a integer vector a
     * @param b integer vector b
     * @return the cosine value
     */
    public static double cosineAlgorithm(int[] a, int[] b) {

        long up = 0;
        long d1 = 0;
        long d2 = 0;

        for (int i = 0; i < a.length; i++) {
            up += a[i] * b[i];
            d1 += Math.pow(a[i], 2);
            d2 += Math.pow(b[i], 2);
        }

        double sim = up / Math.sqrt(d1 * d2);

        return sim;
    }
}
