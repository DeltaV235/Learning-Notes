package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class AdvanceDebugServiceImpl implements DebugService {
    @Override
    public void testMethodBreakpoint() {
        System.out.println(this.getClass().getSimpleName());
    }
}
