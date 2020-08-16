package com.wuyue.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyue.crowd.mapper.AdminMapper;
import com.wuyue.crowd.service.inter.AdminService;
import com.wuyue.util.CrowdConstant;
import com.wuyue.util.CrowdUtil;
import com.wuyue.util.LoginAcctAlreadyInUseException;
import com.wuyue.util.LoginFailedException;
import entity.Admin;
import entity.AdminExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void saveAdmin(Admin admin) {
        String userPswdForm = admin.getUserPswd();
        String md5 = CrowdUtil.md5(userPswdForm);
        admin.setUserPswd(md5);

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDateTime = simpleDateFormat.format(now);
        admin.setCreateTime(createDateTime);

        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            String exceptionClass = e.getClass().getName();
            logger.info("异常类: " + exceptionClass);
            if (e instanceof DuplicateKeyException)
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCOUNT_ALREADY_IN_USE.getStrConstant());
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> result = adminMapper.selectByExample(adminExample);
        if (null == result || result.size() == 0)
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getStrConstant());
        if (result.size() > 1)
            throw new RuntimeException(CrowdConstant.MESSAGE_DATA_ERROR_LOGACCT_NOT_UNIQUE.getStrConstant());
        Admin admin = result.get(0);
        if (null == admin)
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getStrConstant());

        String userPswdDB = admin.getUserPswd();
        String userPswdForm = CrowdUtil.md5(userPswd);
        if (!Objects.equals(userPswdDB, userPswdForm))
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getStrConstant());

        logger.debug("user: " + admin.getLoginAcct() + "has login");
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Admin> admins = adminMapper.selectByKeyword(keyword);
        // 3.封装到PageInfo对象中
        return new PageInfo<>(admins);
    }

    @Override
    public void remove(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
