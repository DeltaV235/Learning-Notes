package com.wuyue.design.Event;

import org.springframework.context.ApplicationEvent;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UnderwritingEvent
 * @description 承保完成事件
 * @date 2020/8/15 21:16
 */
public class UnderwritingEvent extends ApplicationEvent {
    public UnderwritingEvent(Object source) {
        super(source);
    }
}
