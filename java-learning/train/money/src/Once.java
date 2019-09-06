public class Once {
    private double money;
    private double rate;
    private int days;

    private Once(double money, double rate, int days) {
        this.money = money;
        this.rate = rate;
        this.days = days;
    }

    private double calcAnnInterestRate() {
        return Math.pow((1 + rate / 100 / (365.0 / days)), (365.0 / days)) - 1;
    }

    public static void main(String[] args) {
        Once once = new Once(120000, 16.00, 30);
        System.out.println("AnnRate = " + once.calcAnnInterestRate() * 100 + "%");
        int days = 365;
        Once once2 = new Once(120000, 3.95, 365);
        double targetRate = once2.calcAnnInterestRate();
        System.out.println("Target Rate = " + targetRate * 100 + "%");
        while (true) {
            Once once1 = new Once(120000, 3.90, days);
            if (once1.calcAnnInterestRate() > targetRate) {
                System.out.println("days = " + days + "\nAnnRate = " + once1.calcAnnInterestRate() * 100 + "%");
                break;
            } else if (days >= 1)
                days--;
            else {
                System.out.println("Cant reach the high AnnRate!");
                break;
            }
        }
    }
}
