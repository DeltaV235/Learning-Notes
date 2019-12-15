# MySQL笔记

## 1.数据库操作

### 创建数据库

```sql
CREATE {DATABASE|SCHEMA} [IF NOT EXISTS] 数据库名
[
    [DEFAULT] CHARACTER SET [=] 字符集
    [DEFAULT] COLLATE [=] 校对规则名称
];
```

### 查看数据库

```sql
SHOW {DATABASES|SCHEMA}
[LIKE '模式' WHERE 条件];
```

### 选择数据库

```sql
USE 数据库名;
```

### 修改数据库

```sql
ALTER {DATABASE|SCHEMA} [数据库名]
[DEFAULT] CHARACTER SET [=] 字符集
[DEFAULT] COLLATE [=] 校对规则名称
```

### 删除数据库

```sql
DROP {DATABASE|SCHEMA} [IF EXISTS] 数据库名;
```

## 2.MySQL数据类型

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

## 3.操作数据表

### 创建数据表

```sql
CREATE [TEMPORARY] TABLE [IF NOT EXISTS] 数据表名
[(crete_definition,...)] [table_option] [select_statenent];
```

`create_definition`部分:

```sql
col_name type [NOT NULL | NULL] [DEFAULT default_value] [AUTO_INCREMENT] [PRIMARY KEY] [reference_definition]
```

### 查看表结构

```sql
SHOW [FULL] COLUMNS FROM 数据表名 [FROM 数据库名];
SHOW [FULL] COLUMNS FROM 数据库名.数据表名;
DESCRIBE 数据表名;
DESC 数据表名 列名;
```

### 修改表结构

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

### 重命名表

```sql
RENAME TABLE <表名1> TO <表名2>;
```

### 复制表

```sql
CREATE TABLE [IF NOT EXISTS] <表名> LIKE <源表名>;                  只复制表结构
CREATE TABLE [IF NOT EXISTS] <表名> AS SELECT * FROM <源表名>;      复制表结构和数据
```

### 删除表

```sql
DROP TABLE [IF EXISTS] <TABLE_NAME>;
```
