import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className testDao
 * @description
 * @date 2020/2/29 13:20
 */
public class testDao {
    private UserDao userDao;
    private InputStream is;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = builder.build(is);
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destory() throws Exception {
        is.close();
    }

    /**
     * 测试插入一条数据
     */
    @Test
    public void testInsert() {
        User user = new User(null, "wuyue", new Date(), "男", "Shanghai");
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testSelect() {
        List<User> result = userDao.findAll();
        for (User user : result) {
            System.out.println(user);
        }
    }

    /**
     * 测试删除功能
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(50);
    }

    /**
     * 测试查询一个用户
     */
    @Test
    public void testFindOne() {
        User user = userDao.findById(51);
        System.out.println(user);
    }

    /**
     * 测试通过用户名模糊查询用户
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void testCountTotal() {
        int total = userDao.countTotal();
        System.out.println(total);
    }
}



















