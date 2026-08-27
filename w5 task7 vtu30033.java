class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;

        int[] lastIndex = new int[128];

        // Initialize with -1
        for (int i = 0; i < 128; i++) {
            lastIndex[i] = -1;
        }

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // If character was already seen inside the current window
            if (lastIndex[current] >= left) {
                left = lastIndex[current] + 1;
            }

            lastIndex[current] = right;

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
