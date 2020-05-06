package com.wuyue.util;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CrowdConstant
 * @description
 * @date 2020/5/6 20:27
 */
public enum CrowdConstant {
    EXCEPTION("exception");

    private final String strConstant;

    CrowdConstant(String strConstant) {
        this.strConstant = strConstant;
    }

    public String getStrConstant() {
        return strConstant;
    }
}
