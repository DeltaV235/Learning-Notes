# ActiveMQ

## Q & A

### ActiveMQ 启动后无法访问 Console

1. `firewalld` 端口未放开
2. `jetty` 监听的 ip 为 **127.0.0.1**，应该为 **0.0.0.0** 监听所有网卡上的请求(`conf/jetty.xml` -> `beanId = jettyPort` -> `host`)
