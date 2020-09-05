public class ex4_10 {
    public static void main(String[] args) {
        double meter = 3000;
        int day = 0;
        while (meter >= 5) {
            meter /= 2;
            day++;
        }
        System.out.println("day: " + day);
    }
}
