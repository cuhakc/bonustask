# KMP String Matching Algorithm Implementation

## Overview

The Knuth–Morris–Pratt (KMP) algorithm efficiently finds all occurrences of a "pattern" string within a "text" string by preprocessing the pattern to create a longest prefix suffix (LPS) array. On a mismatch, it avoids unnecessary re-evaluation by leveraging the LPS.

## Implementation Details

- **LPS Computation:**  
  Computes for each prefix of the pattern the length of the longest proper prefix which is also a suffix (LPS). This allows the algorithm to know how far to "jump" in the pattern when a mismatch occurs.
- **Pattern Searching:**  
  Scans the text using the pattern. When there's a match, both indexes (text and pattern) move forward. On mismatch, the pattern index is reset according to the LPS array, minimizing redundant checks.

## Time and Space Complexity Analysis

- **Time Complexity:** O(n + m)  
  - `n`: length of the text  
  - `m`: length of the pattern  
  LPS construction is O(m); the text scan is O(n).
- **Space Complexity:** O(m)  
  The only additional space used is for the LPS array.

## Test Results

We tested the implementation on the following cases:

**Test Case 1:**  
Text: `abacababc`  
Pattern: `ab`  
Match Indices: `[0, 4, 6]`

**Test Case 2:**  
Text: `the quick brown fox jumps over the lazy dog`  
Pattern: `the`  
Match Indices: `[0, 31]`

**Test Case 3:**  
Text: `abaababaabaababaababaabaababaaabaababa`  
Pattern: `aab`  
Match Indices: `[2, 7, 10, 15, 20, 23, 29, 32]`

## Conclusion

KMP consistently and efficiently finds all match positions for a pattern in text, regardless of input sizes, and uses only linear additional space. It's a powerful tool for string search needs in real-world applications.
