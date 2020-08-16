package jdbc.jdbctemplate;

import jdbc.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JdbcTemplateDemo1
 * @description 使用JDBCTemplate进行CRUD
 * @date 2020/2/15 1:38
 */
public class JdbcTemplateDemo2 {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Test
    public void test1() {
        String sql = "update emp  set salary=10000 where id = 1001;";
        int count = template.update(sql);
        System.out.println("count = " + count);
    }

    @Test
    public void test2() {
        String sql = "insert into emp values(?,?,?,?,?,?,?,?);";
        Object[] args = {1015, "大月月", 4, 1004, "2020-1-23", 40000, null, 10};
//        int update = template.update(sql, 1015,"大月月",4,1004,"2020-1-23",40000,null,10);
        int update = template.update(sql, args);
        System.out.println("update = " + update);
    }

    @Test
    public void test3() {
        String sql = "delete from emp where id = ?;";
        int update = template.update(sql, 1015);
        System.out.println("update = " + update);
    }

    @Test
    public void test4() {
        String query = "select * from emp where id = 1001;";
        Map<String, Object> stringObjectMap = template.queryForMap(query);
        Set<String> keySet = stringObjectMap.keySet();
        for (String key : keySet) {
            Object obj = stringObjectMap.get(key);
            System.out.print(key + " = ");
            System.out.println(obj);
        }
    }

    @Test
    public void test5() {
        String query = "select id,ename from emp;";
        List<Map<String, Object>> maps = template.queryForList(query);
        for (Map<String, Object> map : maps) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Object obj = map.get(key);
                System.out.println(key + " = " + obj);
            }
        }
    }

    // 查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void test6_1() {
        String query = "select * from emp;";
        List<EmpBean> query1 = template.query(query, new RowMapper<EmpBean>() {
            @Override
            public EmpBean mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String ename = resultSet.getString("ename");
                int job_id = resultSet.getInt("job_id");
                int mgr = resultSet.getInt("mgr");
                Date joindate = resultSet.getDate("joindate");
                double salary = resultSet.getDouble("salary");
                double bonus = resultSet.getDouble("bonus");
                int dept_id = resultSet.getInt("dept_id");
                EmpBean emp = new EmpBean();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate.toLocalDate());
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
                return emp;
            }
        });
        for (EmpBean empBean : query1) {
            System.out.println(empBean);
        }
    }

    @Test
    public void test6_2() {
        String query = "select * from emp";
        List<EmpBean> query1 = template.query(query, new BeanPropertyRowMapper<EmpBean>(EmpBean.class));
        for (EmpBean empBean : query1) {
            System.out.println(empBean);
        }
    }

    // 查询总记录数
    @Test
    public void test7() {
        String query = "select count(id) from emp;";
        Long count = template.queryForObject(query, long.class);
        System.out.println("count = " + count);
    }
}
