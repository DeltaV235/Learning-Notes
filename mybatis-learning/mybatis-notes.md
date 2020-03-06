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

### 全局配置文件

一般放于项目根目录下，名为SqlMapConfig.xml，其中记录了数据库的连接信息(driver url username password)，以及映射文件的位置

配置文档的顶层结构如下：

- configuration（配置）
  - properties（属性）
  - settings（设置）
  - typeAliases（类型别名）
  - typeHandlers（类型处理器）
  - objectFactory（对象工厂）
  - plugins（插件）
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）
      - dataSource（数据源）
  - databaseIdProvider（数据库厂商标识）
  - mappers（映射器）

---

- 示例全局配置文件:

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

#### 类型别名（typeAliases）

类型别名是为 Java 类型设置一个短的名字。 它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余。例如：

```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
```

当这样配置时，Blog 可以用在任何使用 domain.blog.Blog 的地方。

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

```xml
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```

每一个在包 domain.blog 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名。 比如 domain.blog.Author 的别名为 author；若有注解，则别名为其注解值。见下面的例子：

```java
@Alias("author")
public class Author {
    ...
}
```

#### 数据库厂商标识databaseIdProvider标签

MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。 MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库 databaseId 属性的所有语句。 如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃。 为支持多厂商特性只要像下面这样在 mybatis-config.xml 文件中加入 databaseIdProvider 即可：

```xml
<databaseIdProvider type="DB_VENDOR" />
```

[数据库厂商标识（databaseIdProvider）](https://mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)

#### 映射器（mappers）

既然 MyBatis 的行为已经由上述元素配置完了，我们现在就要定义 SQL 映射语句了。 但是首先我们需要告诉 MyBatis 到哪里去找到这些语句。 Java 在自动查找这方面没有提供一个很好的方法，所以最佳的方式是告诉 MyBatis 到哪里去找映射文件。 你可以使用相对于类路径的资源引用， 或完全限定资源定位符（包括 file:/// 的 URL），或类名和包名等。例如：

```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/AuthorMapper.xml"/>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
  <mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

这些配置会告诉了 MyBatis 去哪里找映射文件，剩下的细节就应该是每个 SQL 映射文件了

### 映射文件

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

#### DML

##### Note

1. Mybaits中允许增删改接口直接定义以下类型返回值 **Integer Long Boolean Void** 及其 **原生Java类型**。Mybatis会将SQL影响的行数、是否成功(影响行数 > 0)自动返回

2. SqlSession默认禁用了自动提交，若需要自动提交，则在获取SqlSession时应使用`openSession(true)`来启用这个SqlSession的自动提交

3. 参数类型parameterType可以省略

##### 获取自增主键的值

jdbc中使用statement.getGenratedKeys()获取自增主键的值，Mybatis中在insert标签中设置`userGeneratedKeys="true"`可以使用自增主键获取主键值策略
`keyProperyt`属性指定Mybatis获取到主键值后，将这个值封装给javabean中的哪个属性

##### 获取非自增主键

在`<insert>`中，加入`<selectKey>`标签，可以在sql执行前或后执行指定sql并将值封装给javabean

##### 参数处理

###### 单个参数

SQL中的#{}中的内容可以任意

###### 多个参数

Mybatis默认将多个参数封装为一个map，其key值为param1 param2 ... paramN 以及 参数的索引 即
`param1` `args0`
value值为实参的值，所以映射xml中的SQL可以使用map的key来获取传入的参数值

可以在dao接口的形参前添加@Param("value")来明确指定map中key的值

如果参数是Collection(List Set)类型或者数组，mybatis也会把传入的list或数组封装在map中。
key:collection[index] list[index] array[index]
若存在多个集合，则使用param1[index] param2[index]来获取不同集合的指定索引处的值

#### DQL

##### 返回的类型为List

mapper.xml中`<select>`中resultType属性为List中泛型的数据类型，如返回类型为`List<User>`，则resultType="User"

##### 返回类型为Map

###### Map<field, value>

resultType="map"，Mybatis将直接将数据的字段名和值封装至一个Map中

###### Map<key, javabean>

resultType="javabean" 将返回值封装为JavaBean对象 在dao接口对应的方法上使用`@MapKey("JavaBeanProperty")`指定将哪个JavaBean属性作为Map的key

###### JavaBean属性与数据库字段不同的解决方法

若实体类中的属性名与数据库中的字段名相同，则直接指定`<select>`标签中的`resultType`属性为实体类的全限定名即可
若实体类中的属性名与数据库中的字段名不同，则有以下几种解决方法:

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

    <!-- 其中resultMap标签中，`id`属性为唯一id，用于后续的引用，`type`属性为最后封装为的JavaBean类型 -->
    <resultMap id="userMap" type="domain.User">
        <!-- id定义的主键底层会有优化 -->
        <!-- property属性为JavaBean中对应的属性，column为数据库中对应的字段，数据库主键使用<id>标签包裹，其余非主键字段使用`<result>`包裹 -->
        <id property="userId" column="id"/>
        <result property="userName" column="username"/>
        <result property="userBirthday" column="birthday"/>
        <result property="userSex" column="sex"/>
        <result property="userAddress" column="address"/>
        <!-- 其他不指定的列会自动按照数据库字段名和javabean属性名进行封装 -->
        <!-- 一般我们只要写resultMap就把全部的映射规则都写上 -->
    </resultMap>

    [...]

</mapper>
```


## Mybatis中使用Dao实现类执行CRUD

在Dao实现类中调用SqlSession的`selectList` `selectOne` `insert` `update` `delete`等方法执行crud操作，具体的SQL操作仍然位于mapper.xml文件中。在使用SQLSession的CRUD方法时，需要传入`Map<String, Mapper> mappers`中的key值，即全限定类名.方法名和需要的参数对象。
SqlSession的CRUD方法中，`insert` `update` `delete`最终都调用SqlSession中的`update`方法，所以调用`insert`和`delete`方法与直接调用`update`方法等价。

`SqlSession.getMapper()`将返回一个jdk代理对象，该代理对象最终还是会使用SqlSession中的`insert` `update` `selectList`等方法。
