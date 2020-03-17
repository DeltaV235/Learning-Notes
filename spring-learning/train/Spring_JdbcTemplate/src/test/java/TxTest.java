import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author DeltaV235
 * @version 1.0
 * @className TxTest
 * @description
 * @date 2020/3/17 2:19
 */
public class TxTest {
    private JdbcTemplate jdbcTemplate;
    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    }

    @Test
    public void test01() {
        String sql = "update employee set salary=? where emp_id=?";
        int update = jdbcTemplate.update(sql, 1300.00, 5);
        System.out.println("影响了:" + update + " 行");
    }
}














