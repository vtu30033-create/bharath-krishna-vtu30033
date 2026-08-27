import java.io.*;
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

public class Solution {

    public static PerformOperation isOdd() {
        return (int n) -> n % 2 != 0;
    }

    public static PerformOperation isPrime() {
        return (int n) -> {
            if (n < 2) {
                return false;
            }

            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }

            return true;
        };
    }

    public static PerformOperation isPalindrome() {
        return (int n) -> {
            int original = n;
            int reverse = 0;

            while (n > 0) {
                int digit = n % 10;
                reverse = reverse * 10 + digit;
                n = n / 10;
            }

            return original == reverse;
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            int condition = sc.nextInt();
            int number = sc.nextInt();

            boolean result;

            if (condition == 1) {
                result = isOdd().check(number);

                if (result) {
                    System.out.println("ODD");
                } else {
                    System.out.println("EVEN");
                }

            } else if (condition == 2) {
                result = isPrime().check(number);

                if (result) {
                    System.out.println("PRIME");
                } else {
                    System.out.println("COMPOSITE");
                }

            } else if (condition == 3) {
                result = isPalindrome().check(number);

                if (result) {
                    System.out.println("PALINDROME");
                } else {
                    System.out.println("NOT PALINDROME");
                }
            }
        }

        sc.close();
    }
}
