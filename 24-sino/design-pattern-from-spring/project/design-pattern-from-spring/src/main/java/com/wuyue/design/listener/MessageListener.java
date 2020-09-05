package com.wuyue.design.listener;

import com.wuyue.design.Event.UnderwritingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MessageListener
 * @description
 * @date 2020/8/15 21:16
 */
@Slf4j
@Component
public class MessageListener implements ApplicationListener<UnderwritingEvent> {
    @Override
    public void onApplicationEvent(UnderwritingEvent event) {
        log.info("message start...");
    }
}
