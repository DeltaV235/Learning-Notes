# 数据库系统概论笔记

## 1. 关系数据库标准语言SQL

### 1.1 数据定义

SQL的数据定义语句

|操作对象|操作方法|||
|:---:|:---:|:---:|:---:|
||创建|删除|修改|
|模式|CREATE SCHEMA|DROP SCHEMA||
|表|CREATE TABLE|DROP TABLE|ALTER TABLE|
|视图|CRRATE VIEW|DROP VIEW||
|索引|CREATE INDEX|DROP INDEX|ALTER INDEX|

#### 1.1.1 模式的定义和删除

##### 定义模式

```sql
CREATE SCHEMA <模式名> AUTHORIZATION <用户名>;
CREATE SCHEMA "S-T" AUTHORIZATION WANG;
```

 在`CREATE SCHEMA`中可以接受`CREATE TABLE`,`CREATE VIEW`和`GRANT`子句。
 即

 ```sql
 CREATE SCHEMA TEST AUTHORIZATION ZHANG
 CREATE TABLE TAB1( COL1 SMALLINT,
                    COL2 INT,
                    COL3 CHAR(20),
                    COL4 NUMERIC(10,3),
                    COL5 DECIMAL(5,2)
 );
 ```

##### 删除模式

```sql
DROP SCHEMA <模式名><CASCADE|RESTRICT>;
DROP SCHEMA ZHANG CASCADE;
```

- CASCADE级联，表示在删除模式的同时把该模式中所有的数据库对象全部删除；
- RESTRICT限制，表示如果该模式中已经定义了下属的数据库对象(如表、视图等)，则拒绝该删除语句的执行

#### 1.1.2 基本表的定义、删除与修改

##### 定义基本表

```sql
CREATE TABLE <表名> (<列名> <数据类型> [列级完整性约束条件]
                     <列名> <数据类型> [列级完整性约束条件]
                     [<表级完整性约束条件>]);

CREATE TABLE Student
                    (   Sno CHAR(9) PROMARY KEY,
                        Sname CHAR(20) UNIQUE,
                        Ssex CHAR(2),
                        Sage SMALLINT,
                        Sdept CHAR(20)
                    );

CREATE TABLE SC(
    Sno CHAR(9),
    Cno CHAR(4),
    Grade SMALLINT,
    PRIMARY KEY(Sno,Cno),                           // 主码由两个属性构成,必须作为表级完整性进行定义
    FOREIGN KEY (Sno) REFERENCES Student(Sno),      // 表级完整性约束条件，Sno是外码，被参照表是Student
    FOREIGN KEY (Cno) REFERENCES Course(Cno)        // 表级完整性约束条件，Cno是外码，被参照表是Course
);
```

##### 数据类型

|数据类型|含义|
|---|---|
|CHAR(n),CHARACTER(n)|长度为n的定长字符串|
|VARCHAR(n),CHARACTERVARYING(n)|最大长度为n的变长字符串|
|CLOB|字符串大对象|
|BLOB|二进制大对象|
|INT,INTEGER|长整数(4 Byte)|
|SMALLINT|短整数(2 Byte)|
|BIGINT|大整数(8 Byte)
|NUMERIC(p,d)|定点数，由p位数字(不包括符号，小数点)组成，小数点后面有d位数字|
|DECIMAL(p,d),DEC(p,d)|同NUMERIC|
|REAL|取决于机器精度的单精度浮点数|
|DOUBLE PRECISION|取决于机器精度的双精度浮点数|
|FLOAT(n)|可选精度的浮点数，精度至少为n位数字|
|BOOLEAN|逻辑布尔型|
|DATE|日期，格式为YYYY-MM-DD|
|TIME|时间，格式为HH:MM:SS|
|TIMESTAMP|时间戳类型|
|INTERVAL|时间间隔类型|

##### 修改基本表

```sql
ALTER TABLE <表名>
    [ADD [COLUMN] <新列名> <数据类型> [完整性约束]]
    [ADD <表级完整性约束>]
    [DROP [COLUMN] <列名> [CASCADE|RESTRICT]]
    [DROP CONSTRAINT <完整性约束名> [CASCADE|RESTRICT]]
    [ALTER COLUMN <列名> <数据类型>];
```

##### 删除基本表

```sql
DROP TABLE <表名> [RESTRICT|CASCADE];
```

- RESTRICT:删除有条件限制，欲删除的表有限制条件。该表不能被其他表的约束所引用(CHECK,FOREIGN KEY等)，不能有视图、触发器、存储过程或函数等。
- CASCADE:相关依赖一起被删除

#### 1.1.3 索引的建立与删除

##### 建立索引

```sql
CREATE [UNIQUE] [CLUSTER] INDEX <索引名>
ON <表名>(<列名> [次序] [, <列名> [<次序>]] ··· );
```

##### 修改索引

```sql
ALTER INDEX <旧索引名> RENAME TO <新索引名>;
```

##### 删除索引

```sql
DROP INDEX <索引名>;
```

### 数据查询

#### 一般格式

```sql
SELECT [ALL|DISTINCT] <目标列表达式> [,<目标列表达式>] ···
FROM <表名或视图名> [,<表名或视图名> ···] | (<SELECT语句>) [AS] <别名>
[WHERE <条件表达式>]
[GROUP BY <别名1> [HAVING <条件表达式>]]
[ORDER BY <列名2> [ASC|DESC]];
```

### 数据插入

### 视图

#### 建立视图

```sql
CREATE VIEW <视图名> [(<列名> [,<列名>] ···)]
AS <子查询>
[WITH CHECK OPTION];
```
