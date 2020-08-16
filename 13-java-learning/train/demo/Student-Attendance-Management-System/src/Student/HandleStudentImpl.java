package Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleStudentImpl implements HandleStudent {
    private static ArrayList<Student> students = new ArrayList<Student>();
    private final static String dataBaseUrl = "jdbc:mysql://127.0.0.1:3306/SAMS";
    private final static String userName = "deltav";
    private final static String password = "testpass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object[] inputStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t请输入学生姓名: ");
        String name = scanner.nextLine();
        if (name == "")
            name = null;
        System.out.print("\t请输入学生性别(M/F): ");
        String sex = scanner.nextLine();
        if (sex == "")
            sex = null;
        System.out.print("\t请输入学生年龄: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            age=0;
        }
        System.out.print("\t请输入学生班级号: ");
        int classNum = Integer.parseInt(scanner.nextLine());
        System.out.print("\t请输入学生专业: ");
        String dept = scanner.nextLine();
        System.out.print("\t请输入学生居住地: ");
        String address = scanner.nextLine();
        return new Object[]{name, sex, age, classNum, dept, address};
    }

    public void outputStudentInfo(ArrayList<Student> student) {
        if (student.size() == 0)
            System.out.println("\n\t无学生信息，请先创建\n");
        else {
//            System.out.printf("\n%-10s%-4s%-4s%-10s%-8s%-8s\n", "姓名", "性别", "年龄", "班级号", "专业", "居住地");
            System.out.printf("\n%-8s%-16s%-6s%-6s%-12s%-8s%-8s\n\n", "id", "name", "sex", "age", "classNum", "dept", "address");
            for (Student s : student) {
                System.out.printf("%-8d%-16s%-6s%-6d%-12d%-8s%-8s\n", s.getId(), s.getName(), s.getSex(), s.getAge(),
                        s.getClassNum(), s.getDept(), s.getAddress());
            }
        }
    }

    @Override
    public boolean addStudent() {
        Object[] student = inputStudentInfo();
        Student temp = new Student((String) student[0], (String) student[1], (int) student[2], 0,
                (int) student[3], (String) student[4], (String) student[5]);
        Connection connection = null;
        Statement statement = null;
        boolean isInsert = false;
        try {
            connection = DriverManager.getConnection(dataBaseUrl, userName, password);
            statement = connection.createStatement();
            String sql = "insert into Student values( null,\"" + temp.getName() + "\",\"" + temp.getSex() +
                    "\"," + temp.getAge() + "," + temp.getClassNum() + ",\"" + temp.getDept() + "\",\"" + temp.getAddress() +
                    "\");";
            isInsert = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return isInsert;
    }

    @Override
    public boolean modStudent() throws SQLException {
        queStudent();
        if (students.size() == 0) {
            System.out.println("\n\t\t请先创建学生信息");
            return false;
        }
        System.out.print("请输入需要修改的学生ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        int count = 0;
        for (Student student : students) {
            if (student.getId() == id) {
                Object[] modStudent = inputStudentInfo();
                student.setName((String) modStudent[0]);
                student.setSex((String) modStudent[1]);
                student.setAge((int) modStudent[2]);
                student.setClassNum((int) modStudent[3]);
                student.setDept((String) modStudent[4]);
                student.setAddress((String) modStudent[5]);
                try (Connection connection = DriverManager.getConnection(dataBaseUrl, userName, password);
                     Statement statement = connection.createStatement()) {
                    String sql = "update Student set name=\"" + student.getName() + "\",sex=\"" + student.getSex() +
                            "\",age=" + student.getAge() + ",classNum=" + student.getClassNum() +
                            ",dept=\"" + student.getDept() + "\",address=\"" + student.getAddress() +
                            "\" where id=" + student.getId() + ";";
                    statement.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                count++;
                if (count == students.size()) {
                    System.out.println("找不到该学生ID");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delStudent() {
        return false;
    }

    public void queStudent() throws SQLException {
        Student student = null;
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(dataBaseUrl, userName, password);
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("select * from Student;");
            students.clear();
            while (resultSet.next()) {
                student = new Student(resultSet.getString("name"), resultSet.getString("sex"),
                        resultSet.getInt("age"), resultSet.getInt("id"),
                        resultSet.getInt("classNum"), resultSet.getString("dept"),
                        resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        outputStudentInfo(students);
    }

    @Override
    public ArrayList<Student> queStudent(int id) {
        return null;
    }
}
