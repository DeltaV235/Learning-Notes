package com.wuyue.design.listener;

import com.wuyue.design.Event.UnderwritingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author DeltaV235
 * @version 1.0
 * @className WeChatListener
 * @description 微信监听器
 * @date 2020/8/15 21:25
 */
@Component
@Slf4j
public class WeChatListener implements ApplicationListener<UnderwritingEvent> {
    @Override
    public void onApplicationEvent(UnderwritingEvent event) {
        log.info("WeChat start...");
    }
}
