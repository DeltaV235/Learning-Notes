package com.wuyue.case17.service.impl;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.dao.impl.UserDaoImpl;
import com.wuyue.case17.entities.PageBean;
import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserServiceImpl
 * @description 实行UserService接口，查询所用用户的数据
 * @date 2020/2/18 18:12
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = UserDaoImpl.INSTANCE;

    /**
     * @author DeltaV235
     * @date 2020/2/18 18:15
     * @see UserService
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        return checkUserData(user) && userDao.updateUser(user);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser("id", id);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    /**
     * @param user 由controller封装好的User对象
     * @return dao层添加成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 20:13
     * @description 完成添加一个用户的业务功能
     */
    @Override
    public boolean addUser(User user) {
        return checkUserData(user) && userDao.addUser(user);
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 17:28
     */
    @Override
    public boolean isLegal(User loginUser) {
        String loginUserName = loginUser.getUsername();
        String loginPassword = loginUser.getPassword();
        User user = userDao.findUser("username", loginUserName);
        return user != null && user.getPassword().equals(loginPassword);
    }

    @Override
    public boolean isCheckCodeLegal(String checkCode, String inputCheckCode) {
        if (checkCode != null) {
            return checkCode.equalsIgnoreCase(inputCheckCode);
        }
        return false;
    }

    @Override
    public boolean checkUserData(User user) {
        return user.getName().length() >= 1 && user.getGender().length() >= 1 && user.getQq().length() >= 1 && user.
                getEmail().length() >= 1 && user.getAddress().length() >= 1;
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 23:56
     */
    @Override
    public int batchDelUsers(String[] ids) {
        int count = 0;
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                boolean isDel = userDao.deleteUser(Integer.parseInt(id));
                if (isDel)
                    count++;
            }
        }
        return count;
    }

    /**
     * @author DeltaV235
     * @date 2020/2/21 20:10
     */
    @Override
    public PageBean<User> findUserByConditionAndPage(Map<String, String> paraMap) {
        // 判断是否有参数，判断请求分页参数是否为空字符串，否则赋初值
        String _currentPage = paraMap.get("currentPage");
        String _rows = paraMap.get("rows");
        if ("".equals(_currentPage))
            _currentPage = "1";
        if ("".equals(_rows))
            _rows = "5";

        // 获取分页参数，并从Map中移除
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        paraMap.remove("currentPage");
        paraMap.remove("rows");

        // 判断分页参数是否合法，若currentPage小于1，则显示第1页信息
        if (rows < 1 || currentPage < 1) {
            rows = 5;
            currentPage = 1;
        }

        // 将传入的请求参数转换为String数组
        List<String> keysList = new ArrayList<>();
        List<String> valuesList = new ArrayList<>();
        Set<String> keySet = paraMap.keySet();
        for (String key : keySet) {
            if (paraMap.get(key) == null || paraMap.get(key).equals(""))
                continue;
            keysList.add(key);
            valuesList.add(paraMap.get(key));
        }
        String[] keys = keysList.toArray(new String[0]);
        String[] values = valuesList.toArray(new String[0]);

        try {
            int totalCount = userDao.findUserCountByCondition(keys, values);
            int totalPage = (int) Math.ceil((double) totalCount / rows);
            // 若currentPage大于总页数，则显示最后一页
            if (currentPage > totalPage)
                currentPage = totalPage;
            List<User> userList = userDao.findUserByConditionAndLimit(keys, values,
                    (currentPage - 1) * rows, rows);
            return new PageBean<>(totalCount, totalPage, userList, currentPage, rows);
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
            return new PageBean<>(0, 0, null, 1, 5);
        }
    }

    /**
     * @author DeltaV235
     * @date 2020/2/21 14:47
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        // 若currentPage小于1，则显示第1页信息
        if (rows < 1 || currentPage < 1) {
            rows = 5;
            currentPage = 1;
        }
        int totalCount = userDao.findUserCount();
        int totalPage = (int) Math.ceil((double) totalCount / rows);
        // 若currentPage大于总页数，则显示最后一页
        if (currentPage > totalPage)
            currentPage = totalPage;
        List<User> userByLimit = userDao.findUserByLimit((currentPage - 1) * rows, rows);
        return new PageBean<>(totalCount, totalPage, userByLimit, currentPage, rows);
    }
}
