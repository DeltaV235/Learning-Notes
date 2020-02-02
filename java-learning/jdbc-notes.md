# JDBC

## 驱动类加载

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

## Connection

与特定数据库的连接（会话）。 执行SQL语句并在连接的上下文中返回结果。
Connection对象的数据库能够提供描述其表，其支持的SQL语法，其存储过程，此连接的功能等的信息。

```java
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav", "testpass");
```

## Statement接口

用于向数据库提交SQL语句

### Statement

```java
Statement statement = connection.createStatement();
String sql = "SQL_STATEMENT";
statement.execute(sql);
```

使用`addBatch(SQL)`批量添加数据到**Statement**对象中，使用`executeBatch()`将所有SQL在数据库中执行。
为了提高数据插入效率，可以禁用该Connection的autocommit(`connection.setAutoCommit(false);`)，在所有SQL执行完后再commit(`connection.commit();`)。注意: 一个事务无法执行太对SQL，事务自动提交禁用后需要手动提交。

### PreparedStatement

**`setDate()`中的参数为`java.sql.Date`类型**

`java.sql.Date`为`java.util.Date`的子类
**PreparedStatement**类对象在实例化时就需要提供SQL，随后通过setter方法替换占位符(setter方法中的索引从1开始)，最后调用execute()方法提交该SQL。

```java
String sql = "insert into TABLE_NAME (...) values (?, ?, ?, ...)";
PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "长者");
//            ps.setDouble(2, 20000.5);
            ps.setObject(1, "OLDER");
            ps.setObject(2, 9000);
            ps.setDate(3, new java.sql.Date date(timestamp));
            ps.execute();
```

## ResultSet

- 表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。
- ResultSet对象保持一个光标指向其当前的数据行。 最初，光标位于第一行之前。 next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
- 使用getter方法通过字段索引获，取游标所在行的对应字段的值。
