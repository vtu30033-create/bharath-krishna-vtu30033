import java.time.LocalDate;

class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        return LocalDate.of(year, month, day)
                        .getDayOfWeek()
                        .toString()
                        .substring(0, 1)
                + LocalDate.of(year, month, day)
                        .getDayOfWeek()
                        .toString()
                        .substring(1)
                        .toLowerCase();
    }
}
