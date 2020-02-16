package com.wuyue;

import com.wuyue.entities.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wuyue.utils.JDBCUtils;

import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description 对User对象的数据库操作
 * @date 2020/2/17 0:40
 */
public class UserDao {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @param loginUser 用户传入User
     * @return 合法用户则返回User，否则null
     * @author DeltaV235
     * @date 2020/2/17 0:47
     * @description 传入用户输入的User对象，在数据库中进行比对，若存在的返回数据库中User对象，否则反复null
     */
    public User login(User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String sql = "select * from users where username = ? and password = ?;";
        try {
            Map<String, Object> map = template.queryForMap(sql, username, password);
            User user = new User();
            BeanUtils.populate(user, map);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
