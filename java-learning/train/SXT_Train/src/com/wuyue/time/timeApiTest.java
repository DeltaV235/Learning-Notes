package com.wuyue.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class timeApiTest {
    public static void main(String[] args) {
//        Instant instant = Instant.now();
//        System.out.println(instant);
//        instant = Instant.ofEpochMilli(2000);
//        System.out.println(instant);
//        instant = Instant.ofEpochSecond(5, 600);
//        System.out.println(instant);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }
}
