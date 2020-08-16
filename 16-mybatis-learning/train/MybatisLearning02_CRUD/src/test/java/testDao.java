import dao.UserDao;
import domain.QueryVo;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    /**
     * 通过查询条件对象查询记录
     */
    @Test
    public void testFindByVo() {
        User user = new User();
        user.setUserName("%王%");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userDao.findByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 测试通过id和username查找指定的一个用户
     */
    @Test
    public void testFindByIdAndName() {
        User user = userDao.findByIdAndName(41, "老王");
        System.out.println(user);
    }

    @Test
    public void testFindByIds() {
        List<Integer> ids = new ArrayList<>();
        List<Integer> ids2 = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids2.add(43);
        ids2.add(44);
        List<User> users = userDao.findByIds(ids, ids2);
        for (User user : users) {
            System.out.println(user);
        }
    }
}



















