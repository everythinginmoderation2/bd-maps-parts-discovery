package com.amazon.ata.maps.partsdiscovery;

import java.util.*;

/**
 * Helps expose key words from new editions of part catalogs.
 */
public class DevicePartDiscovery {

    // --- Part A ---
    /**
     * Calculate how often each word appears in a Catalog.
     * @param catalog The catalog to calculate word frequencies for.
     * @return A Map of words that appear in the catalog to the number of times they appear.
     */
    public Map<String, Integer> calculateWordCounts(PartCatalog catalog) {
        //Key = word, Value = word count
        Map<String, Integer> returnMap = new HashMap<>();

        //Go through partCatalog list of words and look at each word
        for (String word : catalog.getCatalogWords()) {
            //Check to see if the word is already in our map
            if (returnMap.containsKey(word)) {
                int currentCount = returnMap.get(word);
                currentCount++;
                returnMap.put(word, currentCount);
                //if it is, retrieve its current count, increment its count, store new count
                //if it is not, add the word to the map with the count of 1
            } else {
                returnMap.put(word, 1);
            }
        }
        return returnMap;
    }

    // --- Part B ---
    /**
     * Removes a word from the provided word count map.
     * @param word the word to be removed
     * @param wordCounts the map to remove the word from
     */
    public void removeWord(String word, Map<String, Integer> wordCounts) {
        wordCounts.remove(word);
    }

    // --- Part C ---
    /**
     * Find the word that appears most frequently based on the word counts from a catalog.
     * @param wordCounts an association between a word and the total number of times it appeared in a catalog
     * @return The word that appears most frequently in the catalog to the number of times they appear.
     */
    public String getMostFrequentWord(Map<String, Integer> wordCounts) {
        String mostFrequentWord = null; //Used to store the current most frequent word
        int highestCountSoFar = -1; //Used to store its count
        for (Map.Entry<String, Integer> anEntry : wordCounts.entrySet()) {
            if (anEntry.getValue() > highestCountSoFar) {
                mostFrequentWord = anEntry.getKey();
                highestCountSoFar = anEntry.getValue();
            }
        }
        return mostFrequentWord;
    }

    // --- Part D ---
    /**
     * Calculates the TF-IDF score for each word in a catalog. The TF-IDF score for a word
     * is equal to the count * idf score. You can assume there will be an idfScore for each word
     * in wordCounts.
     * @param wordCounts - associates a count for each word from a catalog
     * @param idfScores - associates an IDF score for each word in the catalog
     * @return a map associating each word with its TF-IDF score.
     */
    public Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
        //Instantiate the return object
        Map<String, Double> tfIdfScores = new TreeMap<>();
        for (Map.Entry<String, Integer> anEntry : wordCounts.entrySet()) {
            tfIdfScores.put(anEntry.getKey(), anEntry.getValue() * idfScores.get(anEntry.getKey()));
        }
        return tfIdfScores;
    }
}
