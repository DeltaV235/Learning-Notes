package Student;

import java.util.ArrayList;

public interface HandleStudent {
    boolean addStudent();
    boolean modStudent();
    boolean delStudent();
    void queStudent();
    ArrayList<Student> queStudent(int id);
}
