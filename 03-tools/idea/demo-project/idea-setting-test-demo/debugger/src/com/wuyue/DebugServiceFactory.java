package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class DebugServiceFactory {
    public DebugService getDebugService() {
        return new GeneralDebugServiceImpl();
    }
}
