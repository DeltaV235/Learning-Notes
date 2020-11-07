package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 * @className RearrangeTest
 * @description
 * @date 2020/10/11 23:19
 */
public class RearrangeTest {

    @Override
    public String toString() {
        return "RearrangeTest{" +
                "var5='" + var5 + '\'' +
                ", var6=" + var6 +
                ", var8=" + var8 +
                ", var7=" + var7 +
                '}';
    }

    public int getField1() {
        return field1;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public void setField3(int field3) {
        this.field3 = field3;
    }

    public int getField2() {
        return field2;
    }

    public int getField3() {
        return field3;
    }

    class TestClass {}

    static class TestStaticClass {}

    interface TestInterface {}

    enum TestEnum {}

    private void testMethod() {}

    void testMethod2() {}

    protected void testMethod3() {}

    private void testMethod4() {}

    static void testMethod5() {}

    public RearrangeTest() {
    }

    {
        this.field = 3;
    }

    private int field1 = 2;
    int field = 1;
    protected int field2 = 3;
    public int field3 = 4;

    private final int field4 = 2;
    final int field5 = 1;
    protected final int field6 = 3;
    public final int field7 = 4;

    static {
    }

    private static int field8 = 1;
    static int field9 = 2;
    protected static int field10 = 3;
    public static int field11 = 4;

    private static final String var1 = "var1";
    static final String var2 = "var2";
    protected static final String var3 = "var3";
    public static final String var4 = "var4";
}
