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

## Redis集群

### 1.安装ruby环境

CentOS:

```shell
sudo yum install ruby
sudo yum install rubygems
```

### 2.修改redis.conf中的集群配置

```shell
cluster-enabled yes                 # 打开集群模式
cluster-config-file nodes.conf      # 设定节点配置文件名
cluster-node-timeout 15000          # 设定节点失联超时时间(毫秒),集群自动进行主从切换
```

### 3.合体

已过时

```shell
/opt/redis-x.x.x/redis-trib.rb create --replica 1 \
cluster ip:port cluster ip:port
cluster ip:port cluster ip:port
cluster ip:port cluster ip:port
```

```shell
You should use redis-cli instead.

All commands and features belonging to redis-trib.rb have been moved
to redis-cli.
In order to use them you should call redis-cli with the --cluster
option followed by the subcommand name, arguments and options.

Use the following syntax:
redis-cli --cluster SUBCOMMAND [ARGUMENTS] [OPTIONS]

Example:
redis-cli --cluster create 192.168.2.200:6379 192.168.2.200:6380 192.168.2.201:6379 192.168.2.201:6380 192.168.2.202:6379 192.168.2.202:6380 --cluster-replicas 1

To get help about all subcommands, type:
redis-cli --cluster help
```

选项`–replicas 1`表示我们希望为集群中的每个主节点创建一个从节点。

登录任意一个集群节点,选项`-c`表示cluster模式的客户端,能够根据key的hash自动重定向到对应的节点中

```shell
redis-cli -c -p port
```

查看集群的连接状态

```shell
CLUSTER NODES
```

当一个节点的主机下线后,其从机将上位称为master,如果所有从节点都下线了,则集群不可用
可以设置以下配置,使集群在hash slots不完整的情况下也能提供服务

```shell
cluster-require-full-coverage no
```

```shell
cluster keyslot <key>                   # 计算key应该分配到哪个hash槽上
cluster countkeysinslot <slot>          # 返回指定slot目前包含的键值对数量
cluster getkeysinslot <slot> <count>    # 返回count个slot中的key
```

### 指定多个key在一个节点hash分片中

>Hash tags are documented in the Redis Cluster specification, but the gist is that if there is a substring between {} brackets in a key, only what is inside the string is hashed, so for example this{foo}key and another{foo}key are guaranteed to be in the same hash slot, and can be used together in a command with multiple keys as arguments.

### NOTE

在集群通信时,需要使用到集群总线端口,该端口为对应的redis服务的端口+10000,(sentinel为+20000)
所以一定要在防火墙中放开这些端口的入站请求

>Every Redis Cluster node requires two TCP connections open. The normal Redis TCP port used to serve clients, for example 6379, plus the port obtained by adding 10000 to the data port, so 16379 in the example.
>This second high port is used for the Cluster bus, that is a node-to-node communication channel using a binary protocol. The Cluster bus is used by nodes for failure detection, configuration update, failover authorization and so forth. Clients should never try to communicate with the cluster bus port, but always with the normal Redis command port, however make sure you open both ports in your firewall, otherwise Redis cluster nodes will be not able to communicate.
>The command port and cluster bus port offset is fixed and is always 10000.
>Note that for a Redis Cluster to work properly you need, for each node:
>The normal client communication port (usually 6379) used to communicate with clients to be open to all the clients that need to reach the cluster, plus all the other cluster nodes (that use the client port for keys migrations).
>The cluster bus port (the client port + 10000) must be reachable from all the other cluster nodes.
>If you don't open both TCP ports, your cluster will not work as expected.
>The cluster bus uses a different, binary protocol, for node to node data exchange, which is more suited to exchange information between nodes using little bandwidth and processing time.

## Cluster与Sentinel的区别

Cluster能够自动的对数据进行分片,是负载均衡的一种解决方法,Sentinel是解决主从节点的高可用的方案,能够实现故障节点自动切换
