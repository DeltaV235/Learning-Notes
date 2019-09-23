package Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class menu {
    public static void main(String[] args) throws SQLException {
        showMenu();
    }

    private static void showMenu() throws SQLException {
        while (true) {
            System.out.println("\n\n\n\n\n\n\t\t\t学生考勤管理系统");
            System.out.println("\t\t1.创建学生信息");
            System.out.println("\t\t2.查看所有学生信息");
            System.out.println("\t\t3.修改学生信息");
            System.out.println("\t\t0.退出");
            System.out.print("\n\t\t请选择操作: ");
            HandleStudent handleStudentImpl = new HandleStudentImpl();
            Scanner scanner = new Scanner(System.in);
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1:
                    if (handleStudentImpl.addStudent())
                        System.out.println("\n\t\t创建成功\n");
                    else
                        System.out.println("\n\t\t创建失败");
                    scanner.nextLine();
                    break;
                case 2:
                    handleStudentImpl.queStudent();
                    scanner.nextLine();
                    break;
                case 3:
                    handleStudentImpl.modStudent();
                    scanner.nextLine();
                    break;
                case 0:
                    exit(0);
            }
        }
    }
}
