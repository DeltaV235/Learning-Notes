package something.math;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * 计算指定范围内的所有素数
 *
 * @author DeltaV235
 */
public class PrimeNumber implements Runnable {
    private final BigInteger startPos;
    private final BigInteger stopPos;
    private final static Set<BigInteger> result = new TreeSet<>();

    public PrimeNumber() {
        this.startPos = BigInteger.ZERO;
        this.stopPos = BigInteger.ZERO;
    }

    public PrimeNumber(BigInteger startPos, BigInteger stopPos) {
        this.startPos = startPos;
        this.stopPos = stopPos;
    }

    public Set<BigInteger> getResult() {
        return result;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        Set<BigInteger> atomResult = calculator(this.startPos, this.stopPos);
        long endTime = System.currentTimeMillis();
        synchronized (result) {
            result.addAll(atomResult);
//            System.out.printf("计算完毕，计算范围为:%6s---%-6s\t", startPos.toString(), stopPos.toString());
//            System.out.println("\t共找到 " + atomResult.size() + " 个素数 耗时: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * 计算startPos到stopPos范围内的素数
     *
     * @param startPos
     * @param stopPos
     * @return 返回结果集合
     */
    private Set<BigInteger> calculator(BigInteger startPos, BigInteger stopPos) {
        Set<BigInteger> atomResult = new TreeSet<>();

        // currentPos = startPos + 1
        BigInteger currentPos = startPos;
        BigInteger testNumber = null;
        BigInteger endTestNum = null;
        while (currentPos.compareTo(stopPos) <= 0) {
            testNumber = BigInteger.valueOf(2);
            endTestNum = new BigInteger(currentPos.subtract(BigInteger.valueOf(1)).toString());
            boolean isPN = true;
            // testNumber <= endTestNum - 1
            while (testNumber.compareTo(endTestNum) <= 0) {
                if (currentPos.mod(testNumber).equals(BigInteger.valueOf(0))) {
                    isPN = false;
                    break;
                } else {
                    endTestNum = currentPos.divide(testNumber).add(BigInteger.valueOf(1));
                }
                // testNumber++
                testNumber = testNumber.add(BigInteger.valueOf(1));
            }
            if (isPN)
                atomResult.add(currentPos);
            currentPos = currentPos.add(BigInteger.valueOf(1));
        }

        return atomResult;
    }
}
