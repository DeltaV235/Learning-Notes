import com.wuyue.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SpringTest
 * @description
 * @date 2020/3/14 1:17
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    @Autowired
    BookService bookService;

    @Test
    public void test01() {
        System.out.println(bookService);
    }
}














