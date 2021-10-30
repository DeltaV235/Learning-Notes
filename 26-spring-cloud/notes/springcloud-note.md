# Spring Cloud Note

## Eureka

### Eureka Server

#### Eureka Server Startup Class

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class, args);
    }
}
```

#### Eureka Server POM

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

#### Eureka Server application.yml

```yml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com  # eureka服务端的实例名字
  client:
    register-with-eureka: false  # 表示不向注册中心注册自己
    fetch-registry: false # 表示自己就是注册中心，职责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/ # 注册服务都需要依赖这个地址
  server:
    # 禁用 self-preservation
    #    enable-self-preservation: false
    # expiry instance 剔除执行的时间间隔
    eviction-interval-timer-in-ms: 2000
```

#### self-preservation

[Spring Cloud Eureka 自我保护机制](https://www.cnblogs.com/xishuai/p/spring-cloud-eureka-safe.html)

Eureka Server 在运行期间会去统计心跳失败比例在 15 分钟之内是否低于 85%，如果低于 85%，Eureka Server 会将这些实例保护起来，让这些实例不会过期，但是在保护期内如果服务刚好这个服务提供者非正常下线了，此时服务消费者就会拿到一个无效的服务实例，此时会调用失败，对于这个问题需要服务消费者端要有一些容错机制，如重试，断路器等。

我们在单机测试的时候很容易满足心跳失败比例在 15 分钟之内低于 85%，这个时候就会触发 Eureka 的保护机制，一旦开启了保护机制，则服务注册中心维护的服务实例就不是那么准确了，此时我们可以使用eureka.server.enable-self-preservation=false来关闭保护机制，这样可以确保注册中心中不可用的实例被及时的剔除（不推荐）。

自我保护模式被激活的条件是：在 1 分钟后，`Renews (last min) < Renews threshold`。

这两个参数的意思：

`Renews threshold`：**Eureka Server 期望每分钟收到客户端实例续约的总数**。
`Renews (last min)`：**Eureka Server 最后 1 分钟收到客户端实例续约的总数**。

```yml
eureka:
  server:
    # 禁用 self-preservation
    enable-self-preservation: false
    # expiry instance 剔除执行的时间间隔
    eviction-interval-timer-in-ms: 2000
```

#### Server Configure Parameters

**eureka.server.enable-self-preservation**
是否开启自我保护模式，默认为true。

默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。

Eureka通过“自我保护模式”来解决这个问题——当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。

综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。

**eureka.server.eviction-interval-timer-in-ms**
eureka server清理无效节点的时间间隔，默认60000毫秒，即60秒

### Eureka Client

#### Eureka Client POM

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

#### Eureka Client application.yml

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: deltav
    password: testpass

mybatis:
  mapper-locations: classpath:mapper/*.xml
#  type-aliases-package: com.deltav.cloudproviderpayment8001.entities

eureka:
  client:
    # 表示是否将自己注册进 EurekaServer，默认为 true
    register-with-eureka: true
    # 是否从 EurekaServer 抓取已有的注册信息，默认为 true。单节点无所谓，集群必须设置为 true 才能配合使用
    fetchRegistry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
  instance:
    instance-id: paymwent8001
    prefer-ip-address: true
```

#### Eureka Client Startup Class

```java
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
```

#### @LoadBalanced

>Annotation to mark a RestTemplate bean to be configured to use a LoadBalancerClient.

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
```

#### Instance Configure Parameters

**eureka.client.registry-fetch-interval-seconds**
表示eureka client间隔多久去拉取服务注册信息，默认为30秒，对于api-gateway，如果要迅速获取服务注册状态，可以缩小该值，比如5秒

**eureka.instance.lease-expiration-duration-in-seconds**
leaseExpirationDurationInSeconds，表示eureka server至上一次收到client的心跳之后，等待下一次心跳的超时时间，在这个时间内若没收到下一次心跳，则将移除该instance。

默认为90秒

如果该值太大，则很可能将流量转发过去的时候，该instance已经不存活了。

如果该值设置太小了，则instance则很可能因为临时的网络抖动而被摘除掉。

该值至少应该大于leaseRenewalIntervalInSeconds

**eureka.instance.lease-renewal-interval-in-seconds**
leaseRenewalIntervalInSeconds，表示eureka client发送心跳给server端的频率。如果在leaseExpirationDurationInSeconds后，server端没有收到client的心跳，则将摘除该instance。除此之外，如果该instance实现了HealthCheckCallback，并决定让自己unavailable的话，则该instance也不会接收到流量。

- 默认30秒
