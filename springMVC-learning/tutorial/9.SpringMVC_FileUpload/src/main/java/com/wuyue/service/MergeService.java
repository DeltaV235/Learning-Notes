package com.wuyue.service;

import com.wuyue.controller.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MergeService
 * @description
 * @date 2020/3/31 23:41
 */
@Service
public class MergeService {
//    @Autowired
    private FileUploadController controller;

    public MergeService() {
        System.out.println("Service...");
    }
}














