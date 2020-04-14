# MySQL笔记

## 服务的停启

### Linux

```bash
sudo service mysql start
sudo service mysql stop
```

### Windows

```bash
net start mysql
net stop mysql
```

## 数据库的备份

**备份：** mysqldump -u用户名 -p密码 数据库名称 > 保存的路径

**还原：**

1. 登录数据库
2. 创建数据库
3. 使用数据库
4. 执行文件。source 文件路径

## MySQL服务器的登录方式

```bash
mysql -u user -p
mysql -hhost -uuser -ppassword
mysql --host=ip --user=user --password=password
```

## MySQL的语法规范

1. 不区分大小写,但建议关键字大写，表名、列名小写
2. 每条命令最好用分号结尾
3. 每条命令根据需要，可以进行缩进 或换行
4. 注释
单行注释：#注释文字
单行注释：-- 注释文字
多行注释：/* 注释文字  */

## SQL分类

1. DDL(Data Definition Language) 数据定义语言。用来定义数据库对象:数据库、表、列等。关键字: create drop alter 等。
2. DML(Data Manipulation Language) 数据操作语言。 用来对数据库中表的数据进行增删改。 关键字: insert delete update 等
3. DQL(Data Query Language) 数据查询语言。 用来查询数据库中表的记录(数据)。 关键字: select where 等。
4. DCL(Data Control Language) 数据控制语言。 用来定义数据库的访问权限和安全级别，及创建用户。 关键字: grant revoke 等。

## 常用命令

```sql
1.查看当前所有的数据库
  show databases;
2.打开指定的库
  use 库名
3.查看当前库的所有表
  show tables;
4.查看其它库的所有表
  show tables from 库名;
5.创建表
  create table 表名(
    列名 列类型,
    列名 列类型，
  );
6.查看表结构
  desc 表名;
7.查看服务器的版本
方式一：登录到mysql服务端
  select version();
方式二：没有登录到mysql服务端
  mysql --version
  或
  mysql --V
```

## DDL

### 1.数据库操作(CRUD)

#### 创建数据库(Create)

```sql
CREATE {DATABASE|SCHEMA} [IF NOT EXISTS] 数据库名
[
    [DEFAULT] CHARACTER SET [=] 字符集
    [DEFAULT] COLLATE [=] 校对规则名称
];
```

#### 查看数据库(Retrieve)

- 查看数据库系统中，指定的数据库名

```sql
SHOW {DATABASES|SCHEMA}
[LIKE '模式' WHERE 条件];
```

- 查看某个数据库的创建语句

```sql
SHOW CREATE DATABASE DBNAME;
```

#### 修改数据库(Update)

```sql
-- 修改数据库字符集
ALTER {DATABASE|SCHEMA} [数据库名]
[DEFAULT] CHARACTER SET [=] 字符集
[DEFAULT] COLLATE [=] 校对规则名称

-- 重命名数据库
RENAME DATABASE books TO 新库名;
```

#### 删除数据库(Delete)

```sql
DROP {DATABASE|SCHEMA} [IF EXISTS] 数据库名;
```

#### 选择数据库

```sql
USE 数据库名;
```

- 查询当前正在使用的数据库名称

```sql
SELECT DATABASE();
```

### 2.操作数据表

#### 创建数据表(C)

```sql
CREATE [TEMPORARY] TABLE [IF NOT EXISTS] 数据表名
[(crete_definition,...)] [table_option] [select_statenent];
```

`create_definition`部分:

```sql
col_name type [NOT NULL | NULL] [DEFAULT default_value] [AUTO_INCREMENT] [PRIMARY KEY] [reference_definition]
```

- **复制表**

```sql
CREATE TABLE [IF NOT EXISTS] <表名> LIKE <源表名>;                  只复制表结构
CREATE TABLE [IF NOT EXISTS] <表名> AS SELECT * FROM <源表名>;      复制表结构和数据
```

#### 查看表结构(R)

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

#### 修改表结构(U)

```sql
ALTER [IGNORE] TABLE <表名>
    [ADD [COLUMN] <新列名> <数据类型> [完整性约束]]
    [ADD <表级完整性约束>]
    [DROP [COLUMN] <列名> [CASCADE|RESTRICT]]
    [DROP CONSTRAINT <完整性约束名> [CASCADE|RESTRICT]]
    [ALTER COLUMN <列名> <数据类型>];
    [RENAME [AS] 新表名]
    [CHANGE [COLUMN] 旧字段名 新字段定义]
    [MODIFY [COLUMN] 列名 <定义>];
```

1.修改表名

```sql
alter table 表名 rename to 新的表名;
```

2.修改表的字符集

```sql
alter table 表名 character set 字符集名称;
```

3.添加一列

```sql
alter table 表名 add 列名 数据类型;
```

4.修改列名称 类型

```sql
alter table 表名 change 列名 新列名 新数据类型;
alter table 表名 modify 列名 新数据类型;
```

5.删除列

```sql
alter table 表名 drop 列名;
```

其中`MODIFY`只修改字段数据类型，而`CHANGE`能够修改字段的名字和数据类型。

#### 删除表(D)

```sql
DROP TABLE [IF EXISTS] <TABLE_NAME>;
```

#### 重命名表

```sql
RENAME TABLE <表名1> TO <表名2>;
```

#### 表的复制

##### 1.仅仅复制表的结构

```sql
CREATE TABLE copy LIKE author;
```

##### 2.复制表的结构+数据

```sql
CREATE TABLE copy2
SELECT * FROM author;
```

##### 3.只复制部分数据

```sql
CREATE TABLE copy3
SELECT id,au_name
FROM author
WHERE nation='中国';
```

##### 4.仅仅复制某些字段

```sql
CREATE TABLE copy4
SELECT id,au_name
FROM author
WHERE ...;
```

## DML

### 1.添加数据

**语法：**

```sql
insert into 表名(列名1,列名2,...列名n) values(值1,值2,...值n);
insert into table_name set column1 = value1, column2 = value2 , ...;
```

- **注意：**

1. 列名和值要一一对应。
2. 如果表名后，不定义列名，则默认给所有列添加值
`insert into 表名 values(值1,值2,...值n);`
3. 除了数字类型，其他类型需要使用引号(单双都可以)引起来
4. 可以使用如下方式，一条SQL添加多条数据
`insert into 表名 values (值1,值2,...值n), (值1,值2,...值n), ....;`
5. values可以使用子查询代替
`insert into 表名 select * from other_table;`

### 2.删除数据

**语法：**

```sql
delete from 表名 [where 条件]

-- 多表删除
delete table_name/alias
from table1, table2
where condition = value...;

delete table_name/alias
from table1 [[inner]/[[outer] left/right]] join
table2 on contitions
where conditions;
```

- **注意：**

1. 如果不加条件，则删除表中所有记录。
2. 如果要删除所有记录
    1. `DELETE FROM 表名;` -- 不推荐使用。有多少条记录就会执行多少次删除操作
    2. `TRUNCATE TABLE 表名;` -- 推荐使用，效率更高 先删除表，然后再创建一张一样的表。

1.truncate不能加where条件，而delete可以加where条件
2.truncate的效率高
3.truncate 删除带自增长的列的表后，如果再插入数据，数据从1开始
delete 删除带自增长列的表后，如果再插入数据，数据从上一次的断点处开始
4.truncate删除不能回滚，delete删除可以回滚

### 3.修改数据

- **语法：**

```sql
update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件];

-- 多表修改
update 表1 别名
inner|left|right join 表2 别名
on 连接条件
set 列=值,...
where 筛选条件;
```

- **注意：**

1. 如果不加任何条件，则会将表中所有记录全部修改。

## DQL

### 基础查询

​语法：
​SELECT 要查询的东西
​【FROM 表名】;

特点：
①通过select查询完的结果，是一个虚拟的表格，不是真实存在
②要查询的东西 可以是常量值、可以是表达式、可以是字段、可以是函数

### 条件查询

条件查询：根据条件过滤原始表的数据，查询到想要的数据
语法：

```sql
select
要查询的字段|表达式|常量值|函数
from
表
where
条件 ;
```

分类：
一、条件表达式
示例：salary>10000
条件运算符：

```sql
> < >= <= = != <>
```

二、逻辑表达式
示例：salary>10000 && salary<20000

逻辑运算符：

`and（&&）`:两个条件如果同时成立，结果为true，否则为false
`or(||)`:两个条件只要有一个成立，结果为true，否则为false
`not(!)`:如果条件成立，则not后为false，否则为true

三、模糊查询
示例：

```sql
last_name like 'a%'
```

### 排序查询

语法：

```sql
select
要查询的东西
from
表
where
条件
order by 排序的字段|表达式|函数|别名 【asc|desc】 ;
```

### 常见函数

#### 单行函数

每一行都将通过函数输出一个值

##### 字符函数

```sql
concat(str1, str2, ...)                             # 拼接
substr(str, index) / substr(str, index, length)     # 截取子串
upper(str)                                          # 转换成大写
lower(str)                                          # 转换成小写
trim(str)                                           # 去前后指定的空格和字符
ltrim(str)                                          # 去左边空格
rtrim(str)                                          # 去右边空格
replace(str, oldStr, newStr)                        # 替换
lpad(str, 填充字符, 总字符数)                         # 左填充
rpad                                                # 右填充
instr(str, objStr)                                  # 返回子串第一次出现的索引
length(str)                                         # 获取字节个数
```

##### 数学函数

```sql
round         四舍五入
rand          随机数
floor         向下取整
ceil          向上取整
mod           取余
truncate      截断
```

##### 日期函数

```sql
now             当前系统日期+时间
curdate         当前系统日期
curtime         当前系统时间
str_to_date     将字符转换成日期
date_format     将日期转换成字符
datediff        计算两个日期之间相差的天数
```

##### 流程控制函数

if 处理双分支
case语句 处理多分支
情况1：处理等值判断
情况2：处理条件判断

##### 其他函数

version版本
database当前库
user当前连接用户

#### 分组函数

```sql
sum 求和
max 最大值
min 最小值
avg 平均值
count 计数
```

**特点**：
1、以上五个分组函数都忽略null值，除了count(*)
2、sum和avg一般用于处理数值型
max、min、count可以处理任何数据类型
3、都可以搭配distinct使用，用于统计去重后的结果
4、count的参数可以支持：
字段、*、常量值，一般放1

建议使用 count(*)

### 分组查询

语法：

```sql
select 查询的字段，分组函数
from 表
group by 分组的字段
```

特点：
1、可以按单个字段分组
2、和分组函数一同查询的字段最好是分组后的字段
3、分组筛选
针对的表 位置 关键字
分组前筛选： 原始表 group by的前面 where
分组后筛选： 分组后的结果集 group by的后面 having

4、可以按多个字段分组，字段之间用逗号隔开
5、可以支持排序
6、having后可以支持别名

### 多表连接查询

笛卡尔乘积：如果连接条件省略或无效则会出现
解决办法：添加上连接条件

#### 内连接

一、传统模式下的连接 ：等值连接——非等值连接

1.等值连接的结果 = 多个表的交集
2.n表连接，至少需要n-1个连接条件
3.多个表不分主次，没有顺序要求
4.一般为表起别名，提高阅读性和性能

二、sql99语法：通过join关键字实现连接

含义：1999年推出的sql语法
支持：
等值连接、非等值连接 （内连接）
外连接
交叉连接

语法：

```sql
select 字段，...
from 表1
【inner|left outer|right outer|cross】join 表2 on  连接条件
【inner|left outer|right outer|cross】join 表3 on  连接条件
【where 筛选条件】
【group by 分组字段】
【having 分组后的筛选条件】
【order by 排序的字段或表达式】
```

好处：语句上，连接条件和筛选条件实现了分离，简洁明了！

三、自连接

案例：查询员工名和直接上级的名称

sql99

```sql
SELECT e.last_name,m.last_name
FROM employees e
JOIN employees m ON e.`manager_id`=m.`employee_id`;
```

sql92

```sql
SELECT e.last_name,m.last_name
FROM employees e,employees m
WHERE e.`manager_id`=m.`employee_id`;
```

#### 外连接

join一侧的表作为主表,无论是否符合多表连接条件,都将在结果集中显示,没有对应的字段使用null填充

```sql
SELECT *
FROM employees e
LEFT [OUTER] JOIN departments d
ON e.department_id = d.department_id;
```

### 子查询

出现在其他语句中的select语句，称为子查询或内查询
外部的查询语句，称为主查询或外查询

**按结果集的行列数不同：**
标量子查询（结果集只有**一行一列**）
列子查询（结果集只有**一列多行**）
行子查询（结果集有**一行多列**）
表子查询（结果集一般为**多行多列**）

**按子查询出现的位置：**

- select后面：
仅仅支持标量子查询

- from后面：
支持表子查询

- where或having后面：
标量子查询（单行）
列子查询  （多行）
行子查询

- exists后面（相关子查询）
表子查询

#### where或having后面

1、标量子查询（单行子查询）
2、列子查询（多行子查询）
3、行子查询（多列多行）

特点：
①子查询放在小括号内
②子查询一般放在条件的右侧
③标量子查询，一般搭配着单行操作符使用
> < >= <= = <>

列子查询，一般搭配着多行操作符使用
in、any/some、all

④子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果

##### 1.标量子查询

案例1：谁的工资比 Abel 高?

①查询Abel的工资

```sql
SELECT salary
FROM employees
WHERE last_name = 'Abel'
```

②查询员工的信息，满足 salary>①结果

```sql
SELECT *
FROM employees
WHERE salary>(
  SELECT salary
  FROM employees
  WHERE last_name = 'Abel'
);
```

案例2：返回job_id与141号员工相同，salary比143号员工多的员工 姓名，job_id 和工资

①查询141号员工的job_id

```sql
SELECT job_id
FROM employees
WHERE employee_id = 141
```

②查询143号员工的salary

```sql
SELECT salary
FROM employees
WHERE employee_id = 143
```

③查询员工的姓名，job_id 和工资，要求job_id=①并且salary>②

```sql
SELECT last_name,job_id,salary
FROM employees
  WHERE job_id = (
  SELECT job_id
  FROM employees
  WHERE employee_id = 141
) AND salary>(
  SELECT salary
  FROM employees
  WHERE employee_id = 143
);
```

案例3：返回公司工资最少的员工的last_name,job_id和salary

①查询公司的 最低工资

```sql
SELECT MIN(salary)
FROM employees
```

②查询last_name,job_id和salary，要求salary=①

```sql
SELECT last_name,job_id,salary
FROM employees
WHERE salary=(
  SELECT MIN(salary)
  FROM employees
);
```

案例4：查询最低工资大于50号部门最低工资的部门id和其最低工资

①查询50号部门的最低工资

```sql
SELECT  MIN(salary)
FROM employees
WHERE department_id = 50
```

②查询每个部门的最低工资

```sql
SELECT MIN(salary),department_id
FROM employees
GROUP BY department_id
```

③ 在②基础上筛选，满足min(salary)>①

```sql
SELECT MIN(salary),department_id
FROM employees
GROUP BY department_id
HAVING MIN(salary)>(
  SELECT  MIN(salary)
  FROM employees
  WHERE department_id = 50
);
```

##### 2.列子查询（多行子查询）

案例1：返回location_id是1400或1700的部门中的所有员工姓名

①查询location_id是1400或1700的部门编号

```sql
SELECT DISTINCT department_id
FROM departments
WHERE location_id IN(1400,1700)
```

②查询员工姓名，要求部门号是①列表中的某一个

```sql
SELECT last_name
FROM employees
WHERE department_id = ANY (
  SELECT DISTINCT department_id
  FROM departments
  WHERE location_id IN(1400,1700)
);
```

##### 3.行子查询（结果集一行多列或多行多列）

案例：查询员工编号最小并且工资最高的员工信息

```sql
SELECT *
FROM employees
WHERE (employee_id,salary)=(
  SELECT MIN(employee_id),MAX(salary)
  FROM employees
);
```

①查询最小的员工编号

```sql
SELECT MIN(employee_id)
FROM employees
```

②查询最高工资

```sql
SELECT MAX(salary)
FROM employees
```

③查询员工信息

```sql
SELECT *
FROM employees
WHERE employee_id=(
  SELECT MIN(employee_id)
  FROM employees
)AND salary=(
  SELECT MAX(salary)
  FROM employees
);
```

#### select后面

仅仅支持**标量子查询**

案例：查询每个部门的员工个数

```sql
SELECT d.*,(

  SELECT COUNT(*)
  FROM employees e
  WHERE e.department_id = d.`department_id`
) 个数
 FROM departments d;
```

案例2：查询员工号=102的部门名

```sql
SELECT (
  SELECT department_name,e.department_id
  FROM departments d
  INNER JOIN employees e
  ON d.department_id=e.department_id
  WHERE e.employee_id=102
) 部门名;
```

#### from后面

将子查询结果充当一张表，要求必须起别名

案例：查询每个部门的平均工资的工资等级
①查询每个部门的平均工资

```sql
SELECT AVG(salary),department_id
FROM employees
GROUP BY department_id
```

```sql
SELECT * FROM job_grades;
```

②连接①的结果集和job_grades表，筛选条件平均工资 between lowest_sal and highest_sal

```sql
SELECT  ag_dep.*,g.`grade_level`
FROM (
  SELECT AVG(salary) ag,department_id
  FROM employees
  GROUP BY department_id
) ag_dep
INNER JOIN job_grades g
ON ag_dep.ag BETWEEN lowest_sal AND highest_sal;
```

#### exists后面（相关子查询）

语法：
exists(完整的查询语句)

结果：
1或0

```sql
SELECT EXISTS(SELECT employee_id FROM employees WHERE salary=300000);
```

案例1：查询有员工的部门名

in

```sql
SELECT department_name
FROM departments d
WHERE d.`department_id` IN(
  SELECT department_id
  FROM employees
)
```

exists

```sql
SELECT department_name
FROM departments d
WHERE EXISTS(
  SELECT *
  FROM employees e
  WHERE d.`department_id`=e.`department_id`
);
```

### 联合查询

union 联合 合并：将多条查询语句的结果合并成一个结果

语法：

```sql
查询语句1
union
查询语句2
union
...
```

应用场景：
要查询的结果来自于多个表，且多个表没有直接的连接关系，但查询的信息一致时

特点：
1、要求多条查询语句的查询列数是一致的
2、要求多条查询语句的查询的每一列的类型和顺序最好一致
3、union关键字默认去重，如果使用union all 可以包含重复项

## Chore

MySQL表结构以及表数据默认存放于`/var/lib/mysql`下，一个Schema对应一个目录。
MySQL数据库不区分SQL大小写，但建议使用大写。

### 编解码字符集

**charset** 和 **collation** 有多个级别的设置：**服务器级**、**数据库级**、**表级**、**列级和连接级**

**1.服务器级**
查看设置：`show global variables like 'character_set_server';` 和 `show global variables like 'collation_server';`
修改设置：在OPTION FILE （/etc/mysql/my.cnf）里设置：

```bash
[mysqld]
character_set_server=utf8
collation_server=utf8_general_ci
```

**2.数据库级**
查看设置：`select * from information_schema.schemata where schema_name = 'DataBase_Name';`
`show create database database_name;`
   设置：
     1. 若没有显式设置，则自动使用服务器级的配置
     2. 显式设置：在创建库时指定
       `create database database_name DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;`

**3.表级**
   查看设置：`show create table course;`
   设置：
     1. 若没有显式设置，则自动使用数据库级的配置
     2. 显式设置：在创建表时指定
       `create table table_name ( id int ) default charset=utf8 default collate=utf8_bin;`

**4.列级**
   查看设置：`show create table course;`
   设置：
     1. 若没有显式设置，则自动使用表级的配置
     2. 显式设置：
     `CREATE TABLE Table1(column1 VARCHAR(5) CHARACTER SET latin1 COLLATE latin1_german1_ci);`

5.连接级别
  查看设置：

```sql
show variables like 'character_set_client';       # 服务端使用这个编码来理解客户端发来的statements
show variables like 'character_set_connection' ;  # 我还不知道什么意思，等看了mysql源码再说
show variables like 'character_set_results';      # 服务端使用这个编码回送结果集和错误信息
```

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

## 事务隔离级别

- read uncommitted 读未提交(一般不使用)
- read committed 读已提交
- repeatable read 可重复读
- serializable 串行化(一般不使用)

隔离级别用于解决多个事务中读写并发执行时的问题,多个事务同时读取或同时写入的情况与隔离级别无关

- 脏读
读取到其他事务未提交的数据.**无论如何都不应该发生**
- 不可重复读
同一事务中,两次读取的数据不一致.**可以发生**
- 幻读
同一事务中,两次按同一条件读取的数据不一致.**可以发生**

**NOTE**: 在事务隔离级别为 repeatable-read 的情况下,事务每读取一个数据,就会为这行数据创建一个快照,在事务内后续对该数据的读取都将从快照中获取. 若在读取数据前,数据就发生了变化,则第一次读取的数据就是真实的数据.

## 并行写入

在事务中修改某行的数据,该行将产生一个行锁,只有这个事务提交了之后,其他事务才能修改这行数据
