import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        // Count characters in p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int windowSize = p.length();

        // Create the first window
        for (int i = 0; i < windowSize; i++) {
            windowCount[s.charAt(i) - 'a']++;
        }

        // Check first window
        if (Arrays.equals(pCount, windowCount)) {
            result.add(0);
        }

        // Slide the window
        for (int i = windowSize; i < s.length(); i++) {

            // Add new character
            windowCount[s.charAt(i) - 'a']++;

            // Remove old character
            windowCount[s.charAt(i - windowSize) - 'a']--;

            // Check if anagram
            if (Arrays.equals(pCount, windowCount)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }
}
