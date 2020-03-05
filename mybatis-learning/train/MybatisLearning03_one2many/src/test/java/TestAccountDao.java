import com.wuyue.mybatis.dao.AccountDao;
import com.wuyue.mybatis.domain.Account;
import com.wuyue.mybatis.domain.AccountUser;
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
 * @className TestAccountDao
 * @description
 * @date 2020/3/1 20:40
 */
public class TestAccountDao {
    private SqlSession sqlSession;
    private AccountDao accountDao;
    private InputStream is;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = builder.build(is);
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After
    public void destory() throws Exception {
//        sqlSession.commit();
        sqlSession.close();
        is.close();
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindAllAccountUser() {
        List<AccountUser> allAccountUser = accountDao.findAllAccountUser();
        for (AccountUser accountUser : allAccountUser) {
            System.out.println(accountUser);
        }
    }
}
