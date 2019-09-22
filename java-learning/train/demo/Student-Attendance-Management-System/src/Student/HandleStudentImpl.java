package Student;

import java.util.ArrayList;
import java.util.Scanner;

public class HandleStudentImpl implements HandleStudent {
    private static int id = 1;
    private static ArrayList<Student> students = new ArrayList<Student>();

    private Object[] inputStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t请输入学生姓名: ");
        String name = scanner.next();
        System.out.print("\t请输入学生性别(M/F): ");
        String sex = scanner.next();
        System.out.print("\t请输入学生年龄: ");
        int age = scanner.nextInt();
        System.out.print("\t请输入学生班级号: ");
        int classNum = scanner.nextInt();
        System.out.print("\t请输入学生专业: ");
        String dept = scanner.next();
        System.out.print("\t请输入学生居住地: ");
        String address = scanner.next();
        return new Object[]{name, sex, age, classNum, dept, address};
    }

    private void outputStudentInfo(ArrayList<Student> student) {
        if (student.size() == 0)
            System.out.println("\n\t无学生信息，请先创建\n");
        else {
//            System.out.printf("\n%-10s%-4s%-4s%-10s%-8s%-8s\n", "姓名", "性别", "年龄", "班级号", "专业", "居住地");
            System.out.printf("\n%-8s%-10s%-6s%-6s%-12s%-8s%-8s\n", "id", "name", "sex", "age", "classNum", "dept", "address");
            for (Student s : student) {
                System.out.printf("%-8d%-10s%-4s%-4d%-10d%-8s%-8s\n", s.getId(), s.getName(), s.getSex(), s.getAge(),
                        s.getClassNum(), s.getDept(), s.getAddress());
            }
        }
    }

    @Override
    public boolean addStudent() {
        Object[] student = inputStudentInfo();
        int arrayListSize = students.size();
        students.add(new Student((String) student[0], (String) student[1], (int) student[2], HandleStudentImpl.id,
                (int) student[3], (String) student[4], (String) student[5]));
        id++;
        return students.size() > arrayListSize;
    }

    @Override
    public boolean modStudent() {
        System.out.println("请输入需要修改的学生ID: ");
        return false;
    }

    @Override
    public boolean delStudent() {
        return false;
    }

    public void queStudent() {
        outputStudentInfo(students);
    }

    @Override
    public ArrayList<Student> queStudent(int id) {
        return null;
    }
}
