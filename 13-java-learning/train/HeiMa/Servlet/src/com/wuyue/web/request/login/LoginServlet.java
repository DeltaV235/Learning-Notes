package com.wuyue.web.request.login;

import com.wuyue.web.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginServlet
 * @description 检查是否存在输入的用户
 * @date 2020/2/15 14:49
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        // 构建User对象
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);
        User loginUser = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 构建UserDao对象并判断loginUser是否合法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        // 请求转发到对应的servlet
        if (user != null) {
            request.setAttribute("username", loginUser.getUsername());
            request.getRequestDispatcher("/SuccessServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("/FailServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    // 由于使用Dao来进行数据库操作，所以不再使用下方的方法

    /**
     * @param username 需要检查的用户名
     * @param password 需要检查的用户密码
     * @return 合法用户则返回true，否则false
     * @author DeltaV235
     * @date 2020/2/15 15:20
     * @description 检查传入的用户名和密码在数据库中是否有对应的记录
     * @deprecated 使用UserDao代替
     */
    @Deprecated
    private boolean isPass(String username, String password) {
        int id = -1;
        boolean flag = false;
        if ((id = hasUserName(username)) >= 0) {
            flag = checkPass(id, password);
        }
        return flag;
    }

    /**
     * @param username 用户名
     * @return 存在用户则返回用户ID，否则返回-1
     * @author DeltaV235
     * @date 2020/2/15 15:16
     * @description 检查数据库中是否存在指定的用户名，若存在则返回该用户的ID，否则返回-1
     * @deprecated 使用UserDao代替
     */
    @Deprecated
    private int hasUserName(String username) {
        String sql = "select username,id from users where username=?";
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            Map<String, Object> row = jdbcTemplate.queryForMap(sql, username);
            return (int) row.get("id");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @param id            传入的用户ID
     * @param enterPassword 需要对比的用户密码
     * @return 若数据库中密码与传入密码相同，则返回ture，否则false
     * @author DeltaV235
     * @date 2020/2/15 15:17
     * @description 检查指定用户的数据库中密码与参数传入的密码是否相等
     * @deprecated 使用UserDao代替
     */
    @Deprecated
    private boolean checkPass(int id, String enterPassword) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select password from users where id = ?";
        String password = jdbcTemplate.queryForObject(sql, String.class, id);
        if (password != null) {
            return password.equals(enterPassword);
        }
        return false;
    }
}
