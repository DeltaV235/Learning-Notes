package com.wuyue.design.underwriting;

import com.wuyue.design.Event.UnderwritingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UnderWritingServiceImpl
 * @description 承保业务实现
 * @date 2020/8/15 21:11
 */
@Service
@Slf4j
public class UnderWritingServiceImpl {
    private final ApplicationContext applicationContext;

    public UnderWritingServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void underWriting() {
        log.info("start underwriting...");
        applicationContext.publishEvent(new UnderwritingEvent("underwriting finish"));
    }
}
