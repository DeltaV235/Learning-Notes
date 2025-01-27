package com.deltav.order.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JetbrainsFeignClientTest {
    @Autowired
    private JetbrainsFeignClient jetbrainsFeignClient;

    @Test
    void getHelp() {
        String help = jetbrainsFeignClient.getHelp();
        System.out.println(help);
    }
}