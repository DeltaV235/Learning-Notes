import com.wuyue.mybatis.dao.UserDao;
import com.wuyue.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className testDao
 * @description UserDao代理对象的测试类
 * @date 2020/2/29 13:20
 */
public class testDao {
    private SqlSession sqlSession;
    private UserDao userDao;
    private InputStream is;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = builder.build(is);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception {
//        sqlSession.commit();
        sqlSession.close();
        is.close();
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

}



















