package dao;

import domain.QueryVo;
import domain.User;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description
 * @date 2020/2/28 17:57
 */
public interface UserDao {
    /**
     * @return
     * @author DeltaV235
     * @date 2020/2/28 17:59
     * @description 查找user表中的所有数据
     */
    List<User> findAll();

    /**
     * @param uid
     * @author DeltaV235
     * @date 2020/2/29 14:00
     * @description 根据用户id查询一个用户
     */
    User findById(Integer uid);

    /**
     * @param username
     * @return
     * @author DeltaV235
     * @date 2020/2/29 14:04
     * @description 根据用户名称模糊查询
     */
    List<User> findByName(String username);

    /**
     * @param vo
     * @return
     * @author DeltaV235
     * @date 2020/2/29 15:41
     * @description 通过传入的QueryVo对象，查询符合的记录
     */
    List<User> findByVo(QueryVo vo);

    /**
     * @param user
     * @return
     * @author DeltaV235
     * @date 2020/3/1 17:47
     * @description 通过条件查询记录
     */
    List<User> findByCondition(User user);

    /**
     * @param vo
     * @return
     * @author DeltaV235
     * @date 2020/3/1 18:10
     * @description 通过多个id查询多个用户记录
     */
    List<User> findByIds(QueryVo vo);
}




















