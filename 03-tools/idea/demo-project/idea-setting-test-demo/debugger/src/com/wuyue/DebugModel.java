package com.wuyue;

/**
 * The type Debug model.
 *
 * @author DeltaV
 */
public class DebugModel {
    private long userId;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DebugModel{" +
                "userId=" + userId +
                '}';
    }
}
