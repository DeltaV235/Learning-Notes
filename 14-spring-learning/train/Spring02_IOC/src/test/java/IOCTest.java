import com.wuyue.dao.BookDao;
import com.wuyue.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className IOCTest3
 * @description
 * @date 2020/3/12 17:02
 */
public class IOCTest {

    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test01() throws SQLException {
        Object bean = ioc.getBean(BookDao.class);
        Object bean2 = ioc.getBean("bookdaohaha");
        System.out.println(bean2 == bean);
        System.out.println(bean);
        System.out.println(bean2);
    }

    @Test
    public void test02() {
        Object bookService = ioc.getBean("bookService");
        System.out.println(bookService);
    }

    @Test
    public void test03() {
//        BookService service = ioc.getBean(BookService.class);
//        service.save();
    }
}














