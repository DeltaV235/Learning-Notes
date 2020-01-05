package something.math;

import java.io.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class TaskAssignment {
    private BigInteger startPos;
    private BigInteger stopPos;
    private int threadNum;
    private Set<BigInteger[]> task;
    Set<Thread> threads;

    public TaskAssignment(BigInteger startPos, BigInteger stopPos, int threadNum) {
        this.startPos = startPos;
        this.stopPos = stopPos;
        this.threadNum = threadNum;
        threads = new HashSet<>();
    }

    private Set<BigInteger[]> calcuTask() {
        Set<BigInteger[]> task = new HashSet<>();
        BigInteger pointer = startPos;
        BigInteger totalNum = stopPos.subtract(startPos).add(BigInteger.ONE);
        BigInteger[] divideResult = totalNum.divideAndRemainder(BigInteger.valueOf(threadNum));
        BigInteger step = divideResult[0];
        BigInteger remain = divideResult[1];
        for (int i = 0; i < threadNum; i++) {
            if (i == 0) {
                task.add(new BigInteger[]{pointer, pointer.add(step.subtract(BigInteger.ONE)).add(remain)});
                pointer = pointer.add(step).add(remain);
            } else {
                task.add(new BigInteger[]{pointer, pointer.add(step.subtract(BigInteger.ONE))});
                pointer = pointer.add(step);
            }
        }
        return task;
    }

    public void start() {
        this.task = calcuTask();
        Iterator<BigInteger[]> iterator = task.iterator();
        for (int i = 0; i < threadNum; i++) {
            if (iterator.hasNext()) {
                BigInteger[] temp = iterator.next();
                threads.add(new Thread(new PrimeNumber(temp[0], temp[1])));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public Set<BigInteger> getResult() {
        return new PrimeNumber().getResult();
    }

    public void outputResult(OutputStream outputStream) throws IOException {
        boolean hasAlive = true;
        long startTime = System.currentTimeMillis();
        while (hasAlive) {
//            Thread.sleep(5000);
            hasAlive = false;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    hasAlive = true;
                    break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - startTime) + " ms");
        Set<BigInteger> result = getResult();
        System.out.println("共找到素数: " + result.size());
        if (outputStream != null) {
            if (outputStream instanceof ObjectOutputStream) {
                ((ObjectOutputStream) outputStream).writeObject(result);
            } else {
                for (BigInteger bigInteger : result) {
                    outputStream.write((bigInteger.toString() + "  ").getBytes());
                }
            }
            outputStream.close();
        }
    }

    public void outputResult(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        Set<BigInteger> result = (Set<BigInteger>) is.readObject();
        for (BigInteger bigInteger : result) {
            System.out.print(bigInteger.toString() + "  ");
        }
        is.close();
    }

    public static void main(String[] args) {
        TaskAssignment ta = new TaskAssignment(BigInteger.ONE, BigInteger.valueOf(1000000), 50000);
        ta.start();
        try {
            ta.outputResult(new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Test/PN"))));
//            ta.outputResult("Test/PN");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
