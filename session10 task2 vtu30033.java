import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Set<String> pairs = new HashSet<>();

        for (int i = 0; i < n; i++) {

            String a = sc.next();
            String b = sc.next();

            String pair = a + " " + b;

            pairs.add(pair);

            System.out.println(pairs.size());
        }

        sc.close();
    }
}
