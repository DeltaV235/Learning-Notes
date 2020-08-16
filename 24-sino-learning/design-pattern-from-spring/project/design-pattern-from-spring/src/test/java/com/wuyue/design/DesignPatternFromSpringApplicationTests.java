package com.wuyue.design;

import com.wuyue.design.underwriting.UnderWritingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternFromSpringApplicationTests {

    @Autowired
    private UnderWritingServiceImpl underWritingService;

    @Test
    public void testMessageListener() {
        underWritingService.underWriting();
    }
}
