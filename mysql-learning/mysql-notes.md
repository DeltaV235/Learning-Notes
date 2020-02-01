# MySQL笔记

## MySQL服务器的登录方式

1. `mysql -u user -p`
2. `mysql -hhost -uuser -ppassword`
3. `mysql --host=ip --user=user --password=password`

## SQL分类

1. DDL(Data Definition Language) 数据定义语言。用来定义数据库对象:数据库、表、列等。关键字: create drop alter 等。
2. DML(Data Manipulation Language) 数据操作语言。 用来对数据库中表的数据进行增删改。 关键字: insert delete update 等
3. DQL(Data Query Language) 数据查询语言。 用来查询数据库中表的记录(数据)。 关键字: select where 等。
4. DCL(Data Control Language) 数据控制语言。 用来定义数据库的访问权限和安全级别，及创建用户。 关键字: grant revoke 等。

## DDL

### 1.数据库操作

#### 创建数据库

```sql
CREATE {DATABASE|SCHEMA} [IF NOT EXISTS] 数据库名
[
    [DEFAULT] CHARACTER SET [=] 字符集
    [DEFAULT] COLLATE [=] 校对规则名称
];
```

#### 查看数据库

- 查看数据库系统中，指定的数据库名

```sql
SHOW {DATABASES|SCHEMA}
[LIKE '模式' WHERE 条件];
```

- 查看某个数据库的创建语句

```sql
SHOW CREATE DATABASE DBNAME;
```

#### 选择数据库

```sql
USE 数据库名;
```

#### 修改数据库

```sql
ALTER {DATABASE|SCHEMA} [数据库名]
[DEFAULT] CHARACTER SET [=] 字符集
[DEFAULT] COLLATE [=] 校对规则名称
```

#### 删除数据库

```sql
DROP {DATABASE|SCHEMA} [IF EXISTS] 数据库名;
```

### 2.操作数据表

#### 创建数据表

```sql
CREATE [TEMPORARY] TABLE [IF NOT EXISTS] 数据表名
[(crete_definition,...)] [table_option] [select_statenent];
```

`create_definition`部分:

```sql
col_name type [NOT NULL | NULL] [DEFAULT default_value] [AUTO_INCREMENT] [PRIMARY KEY] [reference_definition]
```

#### 查看表结构

```sql
SHOW [FULL] COLUMNS FROM 数据表名 [FROM 数据库名];
SHOW [FULL] COLUMNS FROM 数据库名.数据表名;
DESCRIBE 数据表名;
DESC 数据表名 列名;
```

- 查看表的构建语句

```sql
SHOW CREATE TABLE TABLENAME;
```

#### 修改表结构

```sql
ALTER [IGNORE] TABLE <表名>
    [ADD [COLUMN] <新列名> <数据类型> [完整性约束]]
    [ADD <表级完整性约束>]
    [DROP [COLUMN] <列名> [CASCADE|RESTRICT]]
    [DROP CONSTRAINT <完整性约束名> [CASCADE|RESTRICT]]
    [ALTER COLUMN <列名> <数据类型>];
    [RENAME [AS] 新表名]
    [CHANGE [COLUMN] 旧字段名 新字段定义]
    [MODIFY [COLUMN] <定义>];
```

其中`MODIFY`只修改字段数据类型，而`CHANGE`能够修改字段的名字和数据类型。

#### 重命名表

```sql
RENAME TABLE <表名1> TO <表名2>;
```

#### 复制表

```sql
CREATE TABLE [IF NOT EXISTS] <表名> LIKE <源表名>;                  只复制表结构
CREATE TABLE [IF NOT EXISTS] <表名> AS SELECT * FROM <源表名>;      复制表结构和数据
```

#### 删除表

```sql
DROP TABLE [IF EXISTS] <TABLE_NAME>;
```

## Chore

MySQL表结构以及表数据默认存放于`/var/lib/mysql`下，一个Schema对应一个目录。
MySQL数据库不区分SQL大小写，但建议使用大写。

### 对GROUP BY的理解

[对GROUP BY的理解](https://blog.csdn.net/qq403580298/article/details/90756352)

### 标准的 SQL 的解析顺序

  1. from子句组装来自不同数据源的数据；
  2. where子句基于指定的条件对记录行进行筛选；
  3. group by子句将数据划分为多个分组；
  4. 使用聚集函数进行计算；
  5. 使用having子句筛选分组；
  6. 计算所有的表达式；
  7. 使用order by对结果集进行排序。

- **执行过程**:

  1. FROM：对FROM子句中前两个表执行笛卡尔积生成虚拟表vt1
  2. ON: 对vt1表应用ON筛选器只有满足 join_condition 为真的行才被插入vt2
  3. OUTER(join)：如果指定了 OUTER JOIN保留表(preserved table)中未找到的行将行作为外部行添加到vt2，生成t3，如果from包含两个以上表，则对上一个联结生成的结果表和下一个表重复执行步骤和步骤直接结束。
  4. WHERE：对vt3应用 WHERE 筛选器只有使 where_condition 为true的行才被插入vt4
  5. GROUP BY：按GROUP BY子句中的列列表对vt4中的行分组生成vt5
  6. CUBE|ROLLUP：把超组(supergroups)插入vt6，生成vt6
  7. HAVING：对vt6应用HAVING筛选器只有使 having_condition 为true的组才插入vt7
  8. SELECT：处理select列表产生vt8
  9. DISTINCT：将重复的行从vt8中去除产生vt9
  10. ORDER BY：将vt9的行按order by子句中的列列表排序生成一个游标vc10
  11. TOP：从vc10的开始处选择指定数量或比例的行生成vt11 并返回调用者

注意：from后面的表关联，是自右向左解析的 ，而where条件的解析顺序是自下而上的。

### 注释

- 单行注释: `--` `#`
- 多行注释: `/*` `*/`

### MySQL数据类型

- 数字类型
  - 整数: tinyint、smallint、mediumint、int、bigint
  - 浮点数: float、double、real、decimal
- 日期和时间: date、time、datetime、timestamp、year
- 字符串类型
  - 字符串: char、varchar
  - 文本: tinytext、text、mediumtext、longtext
- 二进制(可用来存储图片、音乐等): tinyblob、blob、mediumblob、longblob

### 数字类型

#### 整型

| type | Storage | Minumun Value | Maximum Value|
| :------------- | :------------- | :------------- | :------------- |
||(Bytes)|(Signed/Unsigned)|(Signed/Unsigned)|
|TINYINT|1|-128|127|
|||0|255|
|SMALLINT|2|-32768|32767|
|||0|65535|
|MEDIUMINT|3|-8388608|8388607|
|||0|16777215|
|INT|4|-2147483648|2147483647|
|||0|4294967295|
|BIGINT|8|-9223372036854775808|9223372036854775807|
|||0|18446744073709551615|

#### 浮点型

| 属性 | 存储空间 | 精度 | 精确性 | 说明 |
| ---- | ----  | ---- | ---- | ---- |
|FLOAT(M, D)|4 bytes|单精度|非精确| 单精度浮点型，m总个数，d小数位 |
|DOUBLE(M, D)|8 bytes|双精度|比Float精度高| 双精度浮点型，m总个数，d小数位 |

- FLOAT容易造成精度丢失

#### 定点数DECIMAL

- 高精度的数据类型，常用来存储交易相关的数据
- DECIMAL(M,N).M代表总精度，N代表小数点右侧的位数（标度）
- 1 < M < 254, 0 < N < 60;
- 存储空间变长

### 时间类型

| 类型 | 字节 | 例 | 精确性 |
| ---- | ----  | ---- | ---- |
| DATE | 三字节 | 2015-05-01 | 精确到年月日 |
| TIME | 三字节 | 11:12:00 | 精确到时分秒 |
| DATETIME | 八字节 | 2015-05-01 11:12:00 | 精确到年月日时分秒 |
| TIMESTAMP |  | 2015-05-01 11:12:00 | 精确到年月日时分秒 |

- MySQL在`5.6.4`版本之后，`TIMESTAMP`和`DATETIME`支持到微秒。
- `TIMESTAMP`会根据系统时区进行转换，`DATETIME`则不会
- 存储范围的区别
  - `TIMESTAMP`存储范围：1970-01-01 00::00:01 to 2038-01-19 03:14:07
  - `DATETIME`的存储范围：1000-01-01 00:00:00 to 9999-12-31 23:59:59
- 一般使用`TIMESTAMP`国际化
- 如存时间戳使用数字类型`BIGINT`

### 字符串类型

| 类型 | 单位 | 最大 | 特性 |
| ---- | ----  | ---- | ---- |
| CHAR | 字符 | 最大为255字符 | 存储定长，容易造成空间的浪费 |
| VARCHAR | 字符 | 可以超过255个字符 | 存储变长，节省存储空间 |
| TEXT | 字节 | 总大小为65535字节，约为64KB | - |

- TEXT在MySQL内部大多存储格式为溢出页，效率不如CHAR
- Mysql默认为utf-8，那么在英文模式下1个字符=1个字节，在中文模式下1个字符=3个字节。
