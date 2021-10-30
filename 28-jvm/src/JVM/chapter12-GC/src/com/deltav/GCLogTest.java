package com.deltav;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. -XX:+PrintGC ：输出GC日志。类似：-verbose:gc
 * 2. -XX:+PrintGCDetails ：输出GC的详细日志
 * 3. -XX:+PrintGCTimeStamps ：输出GC的时间戳（以基准时间的形式）
 * 4. -XX:+PrintGCDateStamps ：输出GC的时间戳（以日期的形式，如2013-05-04T21: 53: 59.234 +0800）
 * 5. -XX:+PrintHeapAtGC ：在进行GC的前后打印出堆的信息
 * 6. -Xloggc:…/logs/gc.log ：日志文件的输出路径
 *
 * @author DeltaV235
 * @version 1.0
 */
public class GCLogTest {
    public static void main(String[] args) {
        List<byte[]> bytes = new ArrayList<>();

        for (int i = 0; i < 1024 * 30; i++) {
            byte[] byteArray = new byte[1024];
            bytes.add(byteArray);
        }
    }
}
