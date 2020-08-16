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

#### properties

```xml
<properties resource="dbconfig.properties" />
```

如果属性在不只一个地方进行了配置，那么MyBatis 将按照下面的顺序来加载：

- 在properties 元素体内指定的属性首先被读取。
- 然后根据properties 元素中的resource 属性读取类路径下属性文件或根据url 属性指定的路径读取属性文件，并覆盖已读取的同名属性。
- 最后读取作为方法参数传递的属性，并覆盖已读取的同名属性。

#### settings

```xml
<settings>
    <!-- 开启数据库字下划线段名到bean属性名的驼峰名的映射 -->
    <setting name="mapUndersourceToCamelCase" value="true">
</settings>
```

这是MyBatis 中极为重要的调整设置，它们会改变MyBatis的运行时行为。  
[https://mybatis.org/mybatis-3/zh/configuration.html#settings](https://mybatis.org/mybatis-3/zh/configuration.html#settings)

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

MyBatis已经为许多常见的Java类型内建了相应的类型别名。它们都是大小写不敏感的,用户使用时应该避免别名发生重复

| 别名       | 映射的类型 |
| ---------- | ---------- |
| _byte      | byte       |
| _long      | long       |
| _short     | short      |
| _int       | int        |
| _integer   | int        |
| _double    | double     |
| _float     | float      |
| _boolean   | boolean    |
| string     | String     |
| byte       | Byte       |
| long       | Long       |
| short      | Short      |
| int        | Integer    |
| integer    | Integer    |
| double     | Double     |
| float      | Float      |
| boolean    | Boolean    |
| date       | Date       |
| decimal    | BigDecimal |
| bigdecimal | BigDecimal |
| object     | Object     |
| map        | Map        |
| hashmap    | HashMap    |
| list       | List       |
| arraylist  | ArrayList  |
| collection | Collection |
| iterator   | Iterator   |

#### typeHandlers类型处理器

无论是MyBatis在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时，都会用类型处理器将获取的值以合适的方式转换成Java类型。

| `BooleanTypeHandler`    | `java.lang.Boolean`, `boolean` | 数据库兼容的 `BOOLEAN`               |
| ----------------------- | ------------------------------ | ------------------------------------ |
| `ByteTypeHandler`       | `java.lang.Byte`, `byte`       | 数据库兼容的 `NUMERIC` 或 `BYTE`     |
| `ShortTypeHandler`      | `java.lang.Short`, `short`     | 数据库兼容的 `NUMERIC` 或 `SMALLINT` |
| `IntegerTypeHandler`    | `java.lang.Integer`, `int`     | 数据库兼容的 `NUMERIC` 或 `INTEGER`  |
| `LongTypeHandler`       | `java.lang.Long`, `long`       | 数据库兼容的 `NUMERIC` 或 `BIGINT`   |
| `FloatTypeHandler`      | `java.lang.Float`, `float`     | 数据库兼容的 `NUMERIC` 或 `FLOAT`    |
| `DoubleTypeHandler`     | `java.lang.Double`, `double`   | 数据库兼容的 `NUMERIC` 或 `DOUBLE`   |
| `BigDecimalTypeHandler` | `java.math.BigDecimal`         | 数据库兼容的 `NUMERIC` 或 `DECIMAL`  |
| `StringTypeHandler`     | `java.lang.String`             | `CHAR`, `VARCHAR`                    |
| `ClobReaderTypeHandler` | `java.io.Reader`               | -                                    |

#### plugins插件

插件是MyBatis提供的一个非常强大的机制，我们可以通过插件来修改MyBatis的一些核心行为。插件通过动态代理机制，可以介入四大对象的任何一个方法的执行。

- Executor(update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
- ParameterHandler(getParameterObject, setParameters)
- ResultSetHandler(handleResultSets, handleOutputParameters)
- StatementHandler(prepare, parameterize, batch, update, query)

#### environments环境

- MyBatis可以配置多种环境，比如开发、测试和生产环境需要有不同的配置。
- 每种环境使用一个environment标签进行配置并指定唯一标识符
- 可以通过environments标签中的default属性指定一个环境的标识符来快速的切换环境

#### environment具体环境

- id: 指定当前环境的唯一标识
- transactionManager: 事务管理器
- dataSource: 数据源,需要连接数据库相关的属性

```xml
<environment id="mysql">
    <transactionManager type="JDBC"></transactionManager>
    <dataSource type="POOLED">
        <property name="driver" value="${mysql.driver}"/>
        <property name="url" value="${mysql.url}"/>
        <!-- Wrong Property -->
        <!-- <property name="username" value="${username}"/> -->
        <property name="username" value="${mysql.username}">
        <property name="password" value="${mysql.password}"/>
    </dataSource>
</environment>
```

**NOTE**: 一般把数据库连接相关的配置放于外置的properties文件中,然后在Mybatis的主配置文件中引用.`${username}`的引用将返回当前计算机的用户名,而不是properties文件中的值

##### transactionManager

type：JDBC | MANAGED | 自定义

- JDBC：使用了JDBC 的提交和回滚设置，依赖于从数据源得到的连接来管理事务范围。JdbcTransactionFactory
- MANAGED：不提交或回滚一个连接、让容器来管理事务的整个生命周期（比如JEE 应用服务器的上下文）。ManagedTransactionFactory
- 自定义：实现TransactionFactory接口，type=全类名/别名

##### dataSource

type：UNPOOLED | POOLED | JNDI | 自定义

- UNPOOLED：不使用连接池，UnpooledDataSourceFactory
- POOLED：使用连接池，PooledDataSourceFactory
- JNDI：在EJB 或应用服务器这类容器中查找指定的数据源
- 自定义：实现DataSourceFactory接口，定义数据源的获取方式。

实际开发中我们使用Spring管理数据源，并进行事务控制的配置来覆盖上述配置

#### 数据库厂商标识databaseIdProvider标签

MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。 MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库 databaseId 属性的所有语句。 如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃。 为支持多厂商特性只要像下面这样在 mybatis-config.xml 文件中加入 databaseIdProvider 即可：

```xml
<databaseIdProvider type="DB_VENDOR">
    <property name="MySQL" value="mysql">
    <property name="Oracle" value="oracle">
    <property name="SQL Server" value="sqlserver">
</databaseIdProvider>
```

- Type：DB_VENDOR
使用MyBatis提供的VendorDatabaseIdProvider解析数据库厂商标识。也可以实现DatabaseIdProvider接口来自定义。
- Property-name：数据库厂商标识
- Property-value：为标识起一个别名，方便SQL语句使用databaseId属性引用

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

- 3.在全局配置文件中启用驼峰命名法自动映射

```xml
<settings>
    <setting name="mapUnderscoreToCamelCase" value="true" />
</settings>
```

|column  |property|
|--|--|
|user_name   |userName|

##### resultMap

###### 联合查询封装结果集

1. 使用级联属性封装结果集 #{property.property}
2. 使用association标签指定联合的javabean对象封装规则 嵌套结果集
3. 使用association分步查询封装

###### association

指定嵌套的查询或封装规则，封装为指定对象

**entities:**
Account:

```java
public class Account {
    private Integer id;
    private Integer uid;
    private double money;
    private User user;
}
```

User:

```java
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
    private List<Account> accountList;
}
```

POJO中的属性可能会是一个对象,我们可以使用联合查询，并以级联属性的方式封装对象:

```xml
<resultMap id="accountMap2" type="account">
    <id column="id" property="id"/>
    <result column="money" property="money"/>
    <result column="uid" property="user.id"/>
    <result column="username" property="user.username"/>
    <result column="birthday" property="user.birthday"/>
    <result column="sex" property="user.sex"/>
    <result column="address" property="user.address"/>
</resultMap>
```

association-嵌套结果集:

```xml
<resultMap id="accountMap" type="account">
    <id column="id" property="id"/>
    <result column="money" property="money"/>
    <association property="user" javaType="user">
        <id column="uid" property="id"/>
        <result column="username" property="username"/>
        <result column="birthday" property="birthday"/>
        <result column="sex" property="sex"/>
        <result column="address" property="address"/>
    </association>
</resultMap>
```

association-分段查询:

- select：调用目标的方法查询当前属性的值
- column：将指定列的值传入目标方法

```xml
<resultMap id="accountMap3" type="account">
    <id property="id" column="id"/>
    <result property="money" column="money"/>
    <association property="user" select="com.wuyue.mybatis.dao.UserDao.findById" column="uid"/>
</resultMap>
```

###### collection

指定嵌套的封装规则，将多行结果封装为一个集合
collection嵌套结果集的方式，定义关联的集合类型元素的封装
使用collection标签定义关联的集合类型的属性封装规则

colums属性若需要传递多个列，则可以使用map进行封装 {key1=column1, key2=column2},key为接口函数的形参名

```xml
 <resultMap id="accountList" type="user">
    <id column="uid" property="id"/>
    <result column="username" property="username"/>
    <result column="birthday" property="birthday"/>
    <result column="sex" property="sex"/>
    <result column="address" property="address"/>
    <collection property="accountList" ofType="account">
        <id column="id" property="id"/>
        <result column="money" property="money"/>
        <result column="uid" property="uid"/>
    </collection>
</resultMap>
```

collection标签也支持分步查询以及延迟加载

**NOTE**: association或者collection标签的fetchType=eager/lazy可以覆盖全局的延迟加载策略，指定立即加载（eager）或者延迟加载（lazy）

###### 懒加载

在使用association进行分步查询的情况下，可以开启懒加载，在使用了相关属性时才会执行对应的SQL并封装

配置方式:

mybatis-conf.xml

```xml
<setttings>
    <setting name="lazyLoadingEnabled" value="true">
    <setting name="aggressiveLazyLoading" value="false">
</settings>
```

|设置名|描述|有效值|默认值|
|---|-----|-----|-----|
|lazyLoadingEnabled |延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。|true \| false|false |
|aggressiveLazyLoading|当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性会按需加载（参考 lazyLoadTriggerMethods)。|true \| false|false （在 3.4.1 及之前的版本默认值为 true）|

## DynamicSQL

### if

通过判断条件是否为true，来确定是否拼接text到SQL中
对象导航图语言（Object Graph Navigation Language）

```xml
<if test="OGNL">
    ...
</if>
```

### where

代替SQL中的where，通过判断条件数量来自动的去除条件表达式开头的and或者or

```xml
<where>
    <if test="id!=null">
        id=#{id}
    </if>
    <if test="name!=null">
        and name=#{name}
    </if>
</where>
```

### trim

自定义字符串截取

```xml
<!-- 
    prefix: 给trim包裹的字符串添加前缀
    suffix: 给trim包裹的字符串添加后缀
    prefixoverride: 删除整个字符串的指定前缀
    suffixoverride: 删除整个字符串的指定后缀
-->
<trim prefixoverride="and" prefix="where">
    <!-- 等价于 -->
<where>
```

若id为null，则mybatis不会将第二个条件语句的and拼接至SQL

### choose/when

分支选择，类似带break的switch-case语句

```xml
<select id="getEmpsByConditionChoose" resultType="employee">
    select * from employee
    <where>
        <choose>
            <when test="id!=null">
                id=#{id}
            </when>
            <when test="name!=null">
                and name like #{name}
            </when>
            <when test="email!=null">
                and email like #{email}
            </when>
            <otherwise>
            </otherwise>
        </choose>
    </where>
</select>
```

### set

用于更新SQL

```xml
<set>
<!-- 等价于 -->
<trim prefix="set" suffixoverride=",">
<!-- 在包裹的字符串前添加前缀set，并删除','后缀如果存在',' -->
```

```xml
<update id="updateEmp">
    update employee
    <set>
        <if test="name!=null">
            name = #{name},
        </if>
        <if test="email!=null">
            email = #{email},
        </if>
        <if test="gender!=null">
            gender = #{gender}
        </if>
    </set>
    <where>
        id = #{id}
    </where>
</update>
```

```xml
<update id="updateEmp">
    update employee
        <trim prefix="set" suffixOverrides=",">
            <if test="name!=null">
                name = #{name},
            </if>
            <if test="email!=null">
                email = #{email},
            </if>
            <if test="gender!=null">
                gender = #{gender}
            </if>
        </trim>
    <where>
        id = #{id}
    </where>
</update>
```

### foreach

```xml
<!--
    collection:指定要遍历的集合：
        list类型的参数会特殊处理封装在map中，map的key就叫list
    item:将当前遍历出的元素赋值给指定的变量
    separator:每个元素之间的分隔符
    open:遍历出所有结果拼接一个开始的字符
    close:遍历出所有结果拼接一个结束的字符
    index:索引。遍历list的时候是index就是索引，item就是当前值
               遍历map的时候index表示的就是map的key，item就是map的值
 
    #{变量名}就能取出变量的值也就是当前遍历出的元素
-->
<select id="getEmpsByIdForeach" resultType="employee">
    select * from employee
    where id in
    <foreach collection="ids" separator="," item="id" open="(" close=")">
        #{id}
    </foreach>
</select>
```

#### 批量插入数据

- 使用`values (), (), ...`的插入方式(MySQL支持)

```xml
<insert id="addEmps">
    insert into employee(name, email, gender) values
    <foreach collection="list" item="emp" separator=",">
        (#{emp.name}, #{emp.email}, #{emp.gender})
    </foreach>
</insert>
```

- 生成多条insert语句来实现批量插入

设置数据库连接属性allowMultiQueries为true:

```properties
mysql.url=jdbc:mysql://localhost:3306/mybatis02?allowMultiQueries=true
```

```xml
<insert id="addEmps">
    <foreach collection="list" item="emp" separator=";">
        insert into employee(name, email, gender) values
        (#{emp.name}, #{emp.email}, #{emp.gender})
    </foreach>
</insert>
```

## Mybatis的内置参数

### _databaseId

数据库标识的别名

### _parameter

传入的参数，如果是单个参数则表示那个参数，如果是多个参数，则代表封装后的map

## bind标签

可以将OGNL表达式的值绑定到一个变量中，方便后来引用这个变量的值

## Mybatis中使用Dao实现类执行CRUD

在Dao实现类中调用SqlSession的`selectList` `selectOne` `insert` `update` `delete`等方法执行crud操作，具体的SQL操作仍然位于mapper.xml文件中。在使用SQLSession的CRUD方法时，需要传入`Map<String, Mapper> mappers`中的key值，即全限定类名.方法名和需要的参数对象。
SqlSession的CRUD方法中，`insert` `update` `delete`最终都调用SqlSession中的`update`方法，所以调用`insert`和`delete`方法与直接调用`update`方法等价。

`SqlSession.getMapper()`将返回一个jdk代理对象，该代理对象最终还是会使用SqlSession中的`insert` `update` `selectList`等方法。

## 缓存

### 一级缓存

一级缓存：（本地缓存）：sqlSession级别的缓存。一级缓存是一直开启的；SqlSession级别的一个Map
与数据库同一次会话期间查询到的数据会放在本地缓存中。
以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库；

**一级缓存失效情况**（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）：

1. sqlSession不同。
2. sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据)
3. sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
4. sqlSession相同，手动清除了一级缓存（缓存清空）

### 二级缓存

二级缓存：（全局缓存）：基于namespace级别的缓存：一个namespace对应一个二级缓存：

- 工作机制：

1. 一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
2. 如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容；
3. 不同namespace查出的数据会放在自己对应的缓存中（map）

```text
sqlSession==>   EmployeeMapper==>Employee
                DepartmentMapper==>Department
```

效果：数据会从二级缓存中获取
查出的数据都会被默认先放在一级缓存中。只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中

**使用:**
1、开启全局二级缓存配置，全局配置文件中设置:

```xml
<setting name="cacheEnabled" value="true"/>
```

2、去mapper.xml中配置使用二级缓存:

```xml
<cache></cache>
```

3、由于缓存的元素默认是非只读的，所以Mybatis会通过序列化/反序列化克隆一个缓存中的对象给用户，所以我们的POJO需要实现序列化接口

```xml
<cache eviction="FIFO" flushInterval="60000" readOnly="false" size="1024"></cache>
```

- eviction:缓存的回收策略：
  - LRU – 最近最少使用的：移除最长时间不被使用的对象。
  - FIFO – 先进先出：按对象进入缓存的顺序来移除它们。
  - SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象。
  - WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
  - 默认的是 LRU。

- flushInterval：缓存刷新间隔
  - 缓存多长时间清空一次，默认不清空，设置一个毫秒值

- readOnly:是否只读：
  - true：只读；mybatis认为所有从缓存中获取数据的操作都是只读操作，不会修改数据。
mybatis为了加快获取速度，直接就会将数据在缓存中的引用交给用户。不安全，速度快
  - false：非只读：mybatis觉得获取的数据可能会被修改。
mybatis会利用序列化&反序列的技术克隆一份新的数据给你。安全，速度慢
- size：缓存存放多少元素；
- type=""：指定自定义缓存的全类名；
实现Cache接口即可；

**Note**
当readOnly设置为true时，通过二级缓存获取的查询结果为同一个真实对象，任何对这个真实对象的修改都将影响二级缓存中该对象的值；
当readOnly为false时，两次通过二级缓存获取的对象不是同一个，并且JavaBean类需要实现Serializable接口

**和缓存有关的设置/属性：**
1、cacheEnabled=true：false：关闭缓存（二级缓存关闭）(一级缓存一直可用的)

2、每个select标签都有useCache="true"：
false：不使用缓存（一级缓存依然使用，二级缓存不使用）

3、【每个增删改标签的：flushCache="true"：（一级二级都会清除）】

增删改执行完成后就会清楚缓存；
测试：flushCache="true"：一级缓存就清空了；二级也会被清除；

查询标签：flushCache="false"：
如果flushCache=true;每次查询之后都会清空缓存；缓存是没有被使用的；

4、sqlSession.clearCache();只是清楚当前session的一级缓存；

5、localCacheScope：本地缓存作用域：（一级缓存SESSION）；当前会话的所有数据保存在会话缓存中；
STATEMENT：可以禁用一级缓存

## Mybaits Generator

基于xml配置文件，自动的生成基本的domain实体类、dao接口类和对应的SQL映射文件

### generatorConfig.xml

*src/main/resources/generatorConfig.xml:*

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>
    <!-- 引入配置文件 -->
    <properties resource="init.properties"/>

    <!-- 指定数据连接驱动jar地址 -->
    <classPathEntry location="${classPath}" />

    <!-- 一个数据库一个context -->
    <context id="infoGuardian">
    <!-- 注释 -->
    <commentGenerator >
        <property name="suppressAllComments" value="false"/><!-- 是否取消注释 -->
        <property name="suppressDate" value="true" /> <!-- 是否生成注释代时间戳-->
    </commentGenerator>

    <!-- jdbc连接 -->
    <jdbcConnection driverClass="${jdbc_driver}"
        connectionURL="${jdbc_url}" userId="${jdbc_user}"
        password="${jdbc_password}" />

    <!-- 类型转换 -->
    <javaTypeResolver>
        <!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
        <property name="forceBigDecimals" value="false"/>
    </javaTypeResolver>

    <!-- 生成实体类地址 -->
    <javaModelGenerator targetPackage="com.oop.eksp.user.model"
        targetProject="${project}" >
        <!-- 是否在当前路径下新加一层schema,eg：fase路径com.oop.eksp.user.model， true:com.oop.eksp.user.model.[schemaName] -->
        <property name="enableSubPackages" value="false"/>
        <!-- 是否针对string类型的字段在set的时候进行trim调用 -->
        <property name="trimStrings" value="true"/>
    </javaModelGenerator>

    <!-- 生成mapxml文件 -->
    <sqlMapGenerator targetPackage="com.oop.eksp.user.data"
        targetProject="${project}" >
        <!-- 是否在当前路径下新加一层schema,eg：fase路径com.oop.eksp.user.model， true:com.oop.eksp.user.model.[schemaName] -->
        <property name="enableSubPackages" value="false" />
    </sqlMapGenerator>

    <!-- 生成mapxml对应client，也就是接口dao -->
    <javaClientGenerator targetPackage="com.oop.eksp.user.data"
        targetProject="${project}" type="XMLMAPPER" >
        <!-- 是否在当前路径下新加一层schema,eg：fase路径com.oop.eksp.user.model， true:com.oop.eksp.user.model.[schemaName] -->
        <property name="enableSubPackages" value="false" />
    </javaClientGenerator>

    <!-- 配置表信息 -->
    <!-- tableName:要生成的表名
        domainObjectName:生成后的实例名
        enableCountByExample:Count语句中加入where条件查询，默认为true开启
        enableUpdateByExample:Update语句中加入where条件查询，默认为true开启
        enableDeleteByExample:Delete语句中加入where条件查询，默认为true开启
        enableSelectByExample:Select多条语句中加入where条件查询，默认为true开启
        selectByExampleQueryId:Select单个对象语句中加入where条件查询，默认为true开启

        版权声明：本文为CSDN博主「唐大麦」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/soonfly/article/details/64499423
    -->
    <table schema="${jdbc_user}" tableName="s_user"
        domainObjectName="UserEntity" enableCountByExample="false"
        enableDeleteByExample="false" enableSelectByExample="false"
        enableUpdateByExample="false">
        <!-- schema即为数据库名 tableName为对应的数据库表 domainObjectName是要生成的实体类 enable*ByExample 
            是否生成 example类   -->

        <!-- 忽略列，不生成bean 字段 -->
        <ignoreColumn column="FRED" />
        <!-- 指定列的java数据类型 -->
        <columnOverride column="LONG_VARCHAR_FIELD" jdbcType="VARCHAR" />
    </table>

    </context>
</generatorConfiguration>

<!-- 版权声明：本文为CSDN博主「兮风」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。 -->
<!-- 原文链接：https://blog.csdn.net/pk490525/article/details/16819307 -->
```

```properties

# Mybatis Generator configuration
project = EKSP
classPath=E:/workplace/EKSP/WebContent/WEB-INF/lib/ojdbc14.jar
jdbc_driver = oracle.jdbc.driver.OracleDriver
jdbc_url=jdbc:oracle:thin:@127.0.0.1:1521:orcl
jdbc_user=INFOGUARDIAN
jdbc_password=info_idap132

# 版权声明：本文为CSDN博主「兮风」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
# 原文链接：https://blog.csdn.net/pk490525/article/details/16819307
```

### 使用MySQL时，配置文件中指定schema失效的情况

使用mybatis generator插件生产代码时，如果数据库是MySQL 8.x  自定义的表与系统表有同名时，会自动生产两张表的对应代码，而且会有很多冲突和错误，此时设置table的schema也没有效果，需要在连接节点里面添加 属性：

```xml
<jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                connectionURL="jdbc:mysql://localhost:3306/***" userId="root"
                password="****" >
    <property name="nullCatalogMeansCurrent" value="true" />
</jdbcConnection>
```

原文链接：[https://blog.csdn.net/hello_jiangdong/article/details/81512468](https://blog.csdn.net/hello_jiangdong/article/details/81512468)

>If you are using version 8.x of Connector/J you may notice that the generator attempts to generate code for tables in the MySql information schemas (sys, information_schema, performance_schema, etc.) This is probably not what you want! To disable this behavior, add the property "nullCatalogMeansCurrent=true" to your JDBC URL.

For example:

```xml
<jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost/my_schema"
        userId="my_user" password="my_password">
    <property name="nullCatalogMeansCurrent" value="true" />
</jdbcConnection>
```

### 运行

maven plugin:

```xml
<project>
   <build>
        <plugins>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.4.0</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### 根据条件查询

1. 创建实体类对应的Example对象
2. 通过Example对象创建一个criteria对象，用于设置查询条件
3. 调用criteria的`and*()`等方法添加条件
4. 将Example对象作为参数，传递给dao接口代理对象mapper的`*ByExample()`方法

```java
@Test
public void testMabtisGenerator() {
    EmployeeExample example = new EmployeeExample();
    EmployeeExample.Criteria criteria = example.createCriteria();
    criteria.andNameLike("%e%");
    criteria.andEmailLike("%g%");
    List<Employee> employees = mapper.selectByExample(example);
    for (Employee employee : employees) {
        System.out.println(employee);
    }
}
```
