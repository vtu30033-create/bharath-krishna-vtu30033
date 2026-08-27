import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Deque<Integer> deque = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;

        for (int i = 0; i < n; i++) {

            int num = sc.nextInt();

            // Add new element
            deque.addLast(num);
            map.put(num, map.getOrDefault(num, 0) + 1);

            // Remove element if window size exceeds m
            if (deque.size() > m) {

                int removed = deque.removeFirst();

                int count = map.get(removed);

                if (count == 1) {
                    map.remove(removed);
                } else {
                    map.put(removed, count - 1);
                }
            }

            // Check unique elements
            if (deque.size() == m) {
                max = Math.max(max, map.size());
            }
        }

        System.out.println(max);

        sc.close();
    }
}
