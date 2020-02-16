package com.wuyue.test;

import com.wuyue.array.ArrayInsert;
import org.junit.Assert;
import org.junit.Test;

public class ArrayInsertTest {
    @Test
    public void insertTest() {
        String[] country = {"Shanghai", "HongKong", "Taiwan", null};
        ArrayInsert.insert(country, "Germany", 1);
        Assert.assertArrayEquals(new String[]{"Shanghai", "Germany", "HongKong", "Taiwan"}, country);
    }
}
