# 数据库系统概论笔记

## 1. 关系数据库标准语言SQL

### 1.1 数据定义

SQL的数据定义语句
|操作对象|操作方法|||
|:-------:|:-------:|:-------:|:-------:|
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
```
