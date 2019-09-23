package Student;

class Student extends Person {
    private int id;
    private int classNum;
    private String dept;
    private String address;

    Student(String name, String sex, int age, int id, int classNum, String dept, String address) {
        super(name, sex, age);
        setId(id);
        setClassNum(classNum);
        setDept(dept);
        setAddress(address);
    }

    int getId() {
        return id;
    }

    int getClassNum() {
        return classNum;
    }

    String getDept() {
        return dept;
    }

    String getAddress() {
        return address;
    }

    void setId(int id) {
        this.id = id;
    }

    void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    void setDept(String dept) {
        this.dept = dept;
    }

    void setAddress(String address) {
        this.address = address;
    }
}
