package com.wuyue.crowd.service.inter;

import com.github.pagehelper.PageInfo;
import entity.Admin;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AdminService
 * @description
 * @date 2020/5/3 16:11
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer id);
}
