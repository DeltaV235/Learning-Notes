package com.wuyue.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot03LoggingApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }

}
