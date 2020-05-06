package com.wuyue.crowd.service.impl;

import com.wuyue.crowd.mapper.AdminMapper;
import com.wuyue.crowd.service.inter.AdminService;
import entity.Admin;
import entity.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AdminServiceImpl
 * @description
 * @date 2020/5/3 16:11
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
