# MySQL Advance Note

## MySQL配置文件

### 二进制日志log-bin

用于主从复制

### 错误日志log-error

默认是关闭的,记录严重的警告和错误信息,每次启动和关闭的详细信息等.

### 查询日志log

默认关闭,记录查询的sql语句，如果开启会减低mysql的整体性能，因为记录日志也是需要消耗系统资源的

### 数据文件

数据库数据默认放置于Linux文件系统的`/var/lib/mysql`下
一个目录对应一个数据库schema

#### .frm文件

form,存放表结构

#### .myd文件

myData,存放表数据

#### .myi文件

myIndex,存放表索引
