import com.wuyue.mapper.intf.EmployeeMapper;
import com.wuyue.model.entities.Department;
import com.wuyue.model.entities.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MapperTest
 * @description
 * @date 2020/3/26 2:13
 */
public class MapperTest {
    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("application.xml");
    }

    @Test
    public void testEmpSave() {
        EmployeeMapper bean = ioc.getBean(EmployeeMapper.class);
        bean.save(new Employee(1006,
                "testrow",
                "test@gmail.com",
                1,
                new Department(101, null)));
    }

    @Test
    public void testUpdate() {
        EmployeeMapper emoloyeeMapper = (EmployeeMapper) ioc.getBean("employeeMapper");
        emoloyeeMapper.update(new Employee(1006,
                null,
                "hehe@zs.com",
                1,
                new Department(103, null)));
    }

    @Test
    public void testDelete() {
        EmployeeMapper mapper = ioc.getBean(EmployeeMapper.class);
        mapper.delete(1006);
    }
}














