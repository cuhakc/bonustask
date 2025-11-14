package org.example;/*
 * Knuth-Morris-Pratt (KMP) Pattern Matching Algorithm
 * This class implements KMP to find all occurrences of a pattern in a text.
 */

import java.util.*;

public class KMPAlgorithm {
    // Builds the LPS (Longest Prefix Suffix) array for the pattern
    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // length of the previous longest prefix suffix
        lps[0] = 0;

        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // Searches for all occurrences of the pattern in text using KMP
    public static List<Integer> KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> result = new ArrayList<>();
        int[] lps = computeLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                result.add(i - j); // Match found at index (i - j)
                j = lps[j - 1]; // Continue searching for next match
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return result;
    }

    // Main method to demonstrate with input test cases
    public static void main(String[] args) {
        String[][] testCases = {
                // {text, pattern}
                {"abacababc", "ab"},         // Short String
                {"the quick brown fox jumps over the lazy dog", "the"}, // Medium String
                {"abaababaabaababaababaabaababaaabaababa", "aab"},      // Long String
        };

        for (int i = 0; i < testCases.length; i++) {
            String text = testCases[i][0];
            String pattern = testCases[i][1];
            List<Integer> matches = KMPSearch(text, pattern);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Text:    " + text);
            System.out.println("Pattern: " + pattern);
            System.out.println("Match Indices: " + matches);
            System.out.println();
        }
    }
}