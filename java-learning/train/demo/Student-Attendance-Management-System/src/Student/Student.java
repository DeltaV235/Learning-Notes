package Student;

public class Student extends Person {
    private int id;
    private int classNum;
    private String dept;
    private String address;

    public Student(String name, String sex, int age, int id, int classNum, String dept, String address) {
        super(name, sex, age);
        setId(id);
        setClassNum(classNum);
        setDept(dept);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public int getClassNum() {
        return classNum;
    }

    public String getDept() {
        return dept;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
