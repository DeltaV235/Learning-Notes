package com.deltav;

/**
 * Test efficiency Interpreter and Compiler mode of JVM.
 * <p>
 * -Xint: 6048 ms
 * -Xcomp: 1241 ms
 * - Xmixed: 1375 ms
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/3 13:56
 */
public class IntCompTest {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));

    }

    public static void testPrimeNumber(int count) {
        for (int i = 0; i < count; i++) {
            //计算100以内的质数
            label:
            for (int j = 2; j <= 100; j++) {
                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if (j % k == 0) {
                        continue label;
                    }
                }
                //System.out.println(j);
            }

        }
    }
}
