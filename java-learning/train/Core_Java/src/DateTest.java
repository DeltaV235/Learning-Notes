import java.time.LocalDate;

public class DateTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println((localDate.getYear()) + "/" + localDate.getMonthValue() + "/" + localDate.getDayOfMonth());
        LocalDate newDay = localDate.plusDays(31);
        System.out.println((newDay.getYear()) + "/" + newDay.getMonthValue() + "/" + newDay.getDayOfMonth());
    }
}
