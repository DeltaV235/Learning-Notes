package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className FileUploadController
 * @description
 * @date 2020/3/30 2:32
 */
@Controller
public class FileUploadController {
    @RequestMapping("/upload")
    public String upload(String username,
                         @RequestParam(value = "userFile", required = false) MultipartFile file,
                         Model model) {
        // 表单中input的name
        System.out.println(file.getName());
        // 文件的名字
        System.out.println(file.getOriginalFilename());
        try {
            file.transferTo(new File("file/" + file.getOriginalFilename()));
            model.addAttribute("msg", "文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "文件上传失败");
        } finally {
            return "forward:/index.jsp";
        }
    }
}














