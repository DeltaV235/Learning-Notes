package com.wuyue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * IDEA debug 调试功能
 * 取自 https://www.bilibili.com/video/BV1xa411Y72S
 * 作者：程序员囧辉
 *
 * @author DeltaV235
 * @version 1.0
 */
public class DebugDemo {
    public static void main(String[] args) {
//        testBaseOperation();
//        testMethodBreakpoint();
//        testFieldBreakpoint();
//        testExceptionBreakpoint();
//        testThrowException();
//        testDropFrame();
//        testCondition();
//        testForceReturn();
//        testTraceCurrentStreamChain();
//        testEvaluateExpression();
//        testSuspend();
        testSuspend2();
    }

    /**
     * <p>
     * Step Over - F8: 往下运行一行<br/>
     * Step Into - F7: 进入方法内，自定义方法或第三方方法、JDK 方法无法进入<br/>
     * Force Step Into - Alt + Shift + F7: 强制进入方法内<br/>
     * Step Out: 退出方法<br/>
     * Resume Program - F9: 恢复程序运行，只到下一个断点<br/>
     * Toggle Line Breakpoint - Ctrl + F8: 标记/去除一个断点<br/>
     * </p>
     */
    private static void testBaseOperation() {
        System.out.println("start method");
        int count = countNumber();
        System.out.println(count);
        System.out.println("end");
    }

    private static int countNumber() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = count + 1;
        }
        return count;
    }

    /**
     * 方法断点：方法入口和出口可以自动暂停
     * 使用场景：打在接口接口方法会自动跳转到实现类，无需通过上下文环境去分析是哪个实现类
     * 缺点：会降低 debug 速度
     */
    private static void testMethodBreakpoint() {
        DebugServiceFactory debugServiceFactory = new DebugServiceFactory();
        DebugService debugService = debugServiceFactory.getDebugService();
        debugService.testMethodBreakpoint();
    }

    /**
     * 字段断点：当字段发生改变（默认断点配置）或被访问（需要额外配置）是暂停
     * 使用场景：想要知道某个字段在什么时候被赋值
     */
    private static void testFieldBreakpoint() {
        DebugModel debugModel = new DebugModel();
        debugModel.setUserId(100L);
        System.out.println(debugModel);
    }

    /**
     * 异常断点：当发生对应的异常时，程序暂停
     * 使用场景：想快速定位抛出异常的代码位置
     */
    private static void testExceptionBreakpoint() {
        try {
            Object obj = null;
            obj.getClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主动抛出异常
     */
    private static void testThrowException() {
        return;
    }

    private static void testDropFrame() {
        int i = 0;
        invokeMethod();
        System.out.println(i);
    }

    private static void invokeMethod() {
        System.out.println("coder1");
        System.out.println("coder2");
    }

    /**
     * 断点条件：在符合条件的情况下才会暂停程序
     * 使用场景：行断点的代码执行次数过多，避免浪费时间在非关注的代码流程上，例如排查某个有问题的 Spring bean
     */
    private static void testCondition() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = count + i;
        }
    }

    /**
     * 强制返回：用于结束当前流程，直接返回
     * 使用场景：避免后续资源操作，如 DB 写入等
     */
    private static void testForceReturn() {
        System.out.println("pre-save");
        writeDataBase();
        System.out.println("post-save");
    }

    private static void writeDataBase() {
        System.out.println("data has been saved");
    }

    /**
     * stream 调试：当代码进入 stream 时，可以将 stream 的详细转换过程展示出来
     * breakpoint 可以打在 stream 的任意一行
     * 使用场景：stream 流程过于复杂导致难以理解
     */
    private static void testTraceCurrentStreamChain() {
        List<Integer> numberList = new ArrayList<>();

        numberList.add(null);
        for (int i = 0; i < 20; i++) {
            numberList.add(i);
        }

        List<String> stringList = numberList.stream()
                .filter(Objects::nonNull)
                .filter(integer -> integer % 2 == 0)
                .map(String::valueOf)
                .collect(Collectors.toList());

        System.out.println("stringList = " + stringList);
    }

    /**
     * 执行表达式：在程序运行期，执行一段我们写的代码
     * 使用场景：查看、修改数据
     */
    private static void testEvaluateExpression() {
        int age = 64;
        if (age > 18) {
            System.out.println("hentai!");
        } else {
            System.out.println("pre-hentai!");
        }
    }

    /**
     * 远程 debug：线上问题排查，调试部署在远程服务器数的代码
     * 使用场景：线上问题排查
     * <p>
     * Note: IDEA 的本地 debug 是一种特殊的 Remote Debug，在本地 debug 时会添加如下的 JVM 参数<br/>
     * -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:13408,suspend=y,server=n<br/>
     * 其端口号随机，与 Remote JVM Debug Configuration 中提示的 Command line arguments for remote JVM 一致
     * <p>
     * 配置参数：<br/>
     * JDK 1.3.x or earlier<br/>
     * -Xnoagent -Djava.compiler=NONE -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005<br/>
     * JDK 1.4.x<br/>
     * -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005<br/>
     * JDK 5-8<br/>
     * -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005<br/>
     * JDK 9 or later<br/>
     * -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005<br/>
     *
     * <p>
     * 配置方式：
     * <ol>
     *     1）使用启动命令：
     *     java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar spring-demo.jar
     * </ol>
     * <ol>
     *     2）在启动脚本中配置该 JVM 参数
     * </ol>
     * <p>
     * 注意点：
     * <ol>1）只能在内网环境中使用，否则存在安全隐患</ol>
     * <ol>2）本地的代码最好和远程的代码一致，否则可能出现 PC 与源码执行位置不一致的问题</ol>
     */
    private static void testRemoveDebug() {
    }

    /**
     * 多线程调试
     * Thread：暂停进入断点线程，不影响其他线程的执行。并且所有线程依次 debug
     * All：在断点处暂停所有线程
     * 常用场景：多线程场景下，想跟踪每一个线程的执行过程
     */
    private static void testSuspend() {
        new Thread(() -> {
            System.out.println("Thread 1 start");
            System.out.println("Thread 1 end");
        }).start();

        new Thread(() -> {
            System.out.println("Thread 2 start");
            System.out.println("Thread 2 end");
        }).start();

        try {
            System.out.println("main thread end");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testSuspend2() {
        new Thread(() -> {
            System.out.println("Thread 1 start");
            System.out.println("Thread 1 end");
        }).start();

        new Thread(() -> {
            System.out.println("Thread 2 start");
            System.out.println("Thread 2 end");
        }).start();

        try {
            System.out.println("main thread end");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
