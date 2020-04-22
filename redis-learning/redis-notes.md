# Redis Notes

使用命令行./redis-server启动的redis服务所产生的rdb文件将存于当前工作路径下，所以更换工作路径后，持久化的文件将发生变化
可以使用service 和 systemctl 来启动redis服务

flushall命令将清空所有库中的键值对以及持久化的rdb文件
在AOP启用的情况下,redis-server启动后将从aop文件中恢复持久化的数据,若不存在aop文件,则创建一个

## 配置文件

### bind

允许本机的哪个IP的请求访问Redis
注意: 放开防火墙的6379/tcp端口的入站请求

## Sentinel

哨兵可能会修改Redis的配置文件,和自身的sentinel.conf文件

## lua

能够保证lua中操作的原子性,能够在高并发的情况下保证数据的一致性
