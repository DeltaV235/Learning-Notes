# Mybatis笔记

## 自定义Mybatis框架

### 配置文件相关类结构及作用

![configuration](img/configuration.svg)

### Database Access Object相关类

![dao](img/dao.svg)

### mybatis核心类

![core](img/core.svg)

[动态代理](https://www.cnblogs.com/zyzblogs/p/11009872.html)

## Mybatis的配置文件

**主配置文件**
一般放于项目根目录下，名为SqlMapConfig.xml，其中记录了数据库的连接信息(driver url username password)，以及映射文件的位置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- mybatis的主配置文件 -->
<configuration>

    <!-- 配置环境 -->
    <environments default="mysql">
        <!-- 配置mysql环境 -->
        <environment id="mysql">
            <!-- 配置事务的类型 -->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 配置数据源(连接池) -->
            <dataSource type="POOLED">
                <!-- 配置连接数据库的4个基本信息 -->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis01"/>
                <property name="username" value="deltav"/>
                <property name="password" value="testpass"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 指定映射配置文件的位置，映射配置文件指的是每个dao独立的配置文件 -->
    <mappers>
        <mapper resource="com/wuyue/dao/UserDao.xml"/>
    </mappers>
</configuration>
```

**映射文件**
可以放于任何目录下，一般放在dao接口同一级的目录下。该文件中记录了需要映射的dao接口类，以及dao接口中方法和SQL语句等的对应关系
使用`#{}`作为输入参数的占位符，类似PreparedStatement中的`?`占位符

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="dao.UserDao">

    <!-- 查询所有的用户记录，并封装至List中 -->
    <select id="findAll" resultType="domain.User">
        select *
        from user;
    </select>

    <!-- 插入一条记录 -->
    <insert id="saveUser" parameterType="domain.User">
        insert into user(username, birthday, sex, address)
        values (#{username}, #{birthday}, #{sex}, #{address})
    </insert>

    <!-- 删除一条记录 -->
    <delete id="deleteUser" parameterType="Integer">
        delete
        from user
        where id = #{udi}
    </delete>

    <!-- 通过id字段查找一条记录 -->
    <select id="findById" parameterType="INT" resultType="domain.User">
        select *
        from user
        where id = #{uid}
    </select>

    <!-- 通过用户姓名字段模糊查询多条记录 -->
    <select id="findByName" resultType="domain.User" parameterType="String">
        select *
        from user
        where username like #{name}
        <!--select *-->
        <!--from user-->
        <!--where username like '%${value}%'-->
    </select>

    <!-- 使用聚合函数查询总记录数 -->
    <select id="countTotal" resultType="int">
        select count(id)
        from user
    </select>

</mapper>
```

## Mybatis查询结果集的封装

若实体类中的属性名与数据库中的字段名相同，则直接指定`<select>`标签中的`resultType`属性为实体类的全限定名即可
若实体类中的属性名与数据库中的字段名不同，则有以下两种解决方法:

- 实体类的属性

```java
    private Integer userId;
    private String userName;
    private Date userBirthday;
    private String userSex;
    private String userAddress;
```

- 1.使SQL中查询字段的别名与实体类中属性名相同

```sql
<select [...]>

    [...]

    select id userId, username userName, birthday userBirthday, sex userSex, address userAddress from user;

    [...]

</select>
```

- 2.使用`<resultMap>`标签设置封装实体类属性名与数据库字段名之间的对应关系

```xml
<mapper>

    [...]

    <resultMap id="userMap" type="domain.User">
        <id property="userId" column="id"/>
        <result property="userName" column="username"/>
        <result property="userBirthday" column="birthday"/>
        <result property="userSex" column="sex"/>
        <result property="userAddress" column="address"/>
    </resultMap>

    [...]

</mapper>
```

`property`属性为实体类中对应的字段，`column`为数据库中对应的字段，数据库主键使用`<id>`标签包裹，其余非主键字段使用`<result>`包裹
