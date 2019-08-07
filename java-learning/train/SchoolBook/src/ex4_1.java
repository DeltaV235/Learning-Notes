import java.util.Scanner;

public class ex4_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your score: ");
        int score = Integer.parseInt(scan.nextLine());
        String level;
        switch (score / 10) {
            case 10:
            case 9:
                level = "优";
                break;
            case 8:
                level = "良";
                break;
            case 7:
                level = "中";
                break;
            case 6:
                level = "及格";
                break;
            default:
                level = "不及格";
        }
        System.out.println("成绩为" + level);
    }
}
