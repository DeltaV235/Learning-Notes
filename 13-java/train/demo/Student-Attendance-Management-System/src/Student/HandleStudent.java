package Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HandleStudent {
    Object[] inputStudentInfo();
    void outputStudentInfo(ArrayList<Student> student);
    boolean addStudent() throws SQLException;
    boolean modStudent() throws SQLException;
    boolean delStudent();
    void queStudent() throws SQLException;
    ArrayList<Student> queStudent(int id);
}
