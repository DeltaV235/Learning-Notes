package com.deltav;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author DeltaV235
 * @version 1.0
 * <p>
 * <p>
 * 监控我们的应用服务器的堆内存使用情况，设置一些阈值进行报警等处理
 */

public class MemoryMonitor {
    public static void main(String[] args) {
        MemoryMXBean memoryMbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryMbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "m");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + memoryMbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memoryMbean.getNonHeapMemoryUsage());

        System.out.println("=======================通过Java runtime 来获取相关系统状态============================ ");
        System.out.println("当前堆内存大小totalMemory " + (int) Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m");// 当前堆内存大小
        System.out.println("空闲堆内存大小freeMemory " + (int) Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");// 空闲堆内存大小
        System.out.println("最大可用总堆内存maxMemory " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");// 最大可用总堆内存大小

    }
}
