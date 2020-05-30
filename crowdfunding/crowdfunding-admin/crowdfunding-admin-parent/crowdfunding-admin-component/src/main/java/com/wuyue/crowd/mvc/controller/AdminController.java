package com.wuyue.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.wuyue.crowd.service.inter.AdminService;
import com.wuyue.util.CrowdConstant;
import entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.wuyue.util.CrowdConstant.ATTR_NAME_LOGIN_ADMIN;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AdminController
 * @description
 * @date 2020/5/7 2:05
 */
@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/save.html")
    public String save(Admin admin, Model model) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @GetMapping("/admin/remove/{id}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("id") Integer id,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") String keyword) {
        adminService.remove(id);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            Model model
    ) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_ADMIN_PAGE_INFO.getStrConstant(), pageInfo);
        return "admin-page";
    }

    @GetMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        if (session.getAttribute(ATTR_NAME_LOGIN_ADMIN.getStrConstant()) != null)
            session.removeAttribute(ATTR_NAME_LOGIN_ADMIN.getStrConstant());
        return "redirect:/admin/to/login/page.html";
    }

    @PostMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session
    ) {
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        session.setAttribute(ATTR_NAME_LOGIN_ADMIN.getStrConstant(), admin);
        return "redirect:/admin/to/main/page.html";
    }
}
