# Log4j Notes

## pom

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.13.1</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.13.1</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## XML Configuration File

**classpath:log4j2.xml**:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<Configuration xmlns="http://logging.apache.org/log4j/2.0/config">
    <!-- 指定log的输出设备与输出格式 -->
    <Appenders>
        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <!-- logger配置,指定logger名称,日志等级,是否调用其他logger,引用的Appender等信息 -->
    <Loggers>
        <Logger name="com.wuyue.dao.EmployeeMapperDynamicSql" level="debug" additivity="false">
            <AppenderRef ref="console"/>
        </Logger>
        <Logger name="test" level="debug" additivity="false">
            <AppenderRef ref="console"/>
        </Logger>

        <Root level="info">
            <AppenderRef ref="console"/>
        </Root>
    </Loggers>
</Configuration>
```
