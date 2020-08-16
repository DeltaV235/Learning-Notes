package interfacePackage.inner;

import java.util.Date;

public class InnerClassTest {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass(100, new Date());
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.run();
    }
}

class OuterClass {
    private int value;
    private Date date;

    public OuterClass(int value, Date date) {
        this.value = value;
        this.date = date;
    }

    public void start() {
        OuterClass.InnerClass inner = this.new InnerClass();
        inner.run();
    }

    public class InnerClass {
        public void run() {
            System.out.println(OuterClass.this.value);
        }
    }
}
