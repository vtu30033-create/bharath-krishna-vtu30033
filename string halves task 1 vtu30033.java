class Solution {

    public static boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;

        int countA = 0;
        int countB = 0;

        String vowels = "aeiouAEIOU";

        // Count vowels in first half
        for (int i = 0; i < mid; i++) {
            if (vowels.indexOf(s.charAt(i)) >= 0) {
                countA++;
            }
        }

        // Count vowels in second half
        for (int i = mid; i < s.length(); i++) {
            if (vowels.indexOf(s.charAt(i)) >= 0) {
                countB++;
            }
        }

        return countA == countB;
    }

    public static void main(String[] args) {
        String s = "book";

        System.out.println(halvesAreAlike(s));
    }
}
