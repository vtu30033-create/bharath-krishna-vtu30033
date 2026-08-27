import java.io.*;
import java.util.*;

class Result {

    public static String findDay(int month, int day, int year) {

        Calendar cal = Calendar.getInstance();

        cal.set(year, month - 1, day);

        String dayName = cal.getDisplayName(
            Calendar.DAY_OF_WEEK,
            Calendar.LONG,
            Locale.ENGLISH
        );

        return dayName.toUpperCase();
    }
}

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int month = sc.nextInt();
        int day = sc.nextInt();
        int year = sc.nextInt();

        String result = Result.findDay(month, day, year);

        System.out.println(result);

        sc.close();
    }
}
