package jdbc.jdbctemplate;

import jdbc.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JdbcTemplateDemo1
 * @description JDBCTemplate示例
 * @date 2020/2/15 1:38
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into jdbc_test (name) values(?);";
        int count = template.update(sql, "zhangsan");
        System.out.println("count = " + count);
    }
}
