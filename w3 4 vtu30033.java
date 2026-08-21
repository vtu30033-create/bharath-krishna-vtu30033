import java.util.*;

class Solution {
    public String frequencySort(String s) {

        // Count frequency of each character
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Put characters into a list
        List<Character> chars = new ArrayList<>(map.keySet());

        // Sort by decreasing frequency
        chars.sort((a, b) -> map.get(b) - map.get(a));

        // Build the result
        StringBuilder result = new StringBuilder();

        for (char c : chars) {
            int count = map.get(c);

            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }
}
