import com.wuyue.domain.Book;
import com.wuyue.domain.User;
import com.wuyue.service.BaseService;
import com.wuyue.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SpringIOCTest
 * @description
 * @date 2020/3/14 2:32
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringIOCTest {
    @Autowired
    private BaseService<User> userService;

    @Autowired
    private BaseService<Book> bookService;

    @Test
    public void test() {
        System.out.println(userService);
        System.out.println(bookService);
    }
}














