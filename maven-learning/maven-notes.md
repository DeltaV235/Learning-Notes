# Maven笔记

## 设置代理

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <proxies>
    <proxy>
      <id>myproxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.somewhere.com</host>
      <port>8080</port>
      <username>proxyuser</username>
      <password>somepassword</password>
      <nonProxyHosts>*.google.com|ibiblio.org</nonProxyHosts>
    </proxy>
  </proxies>
  ...
</settings>
```

## 使用内嵌Tomcat运行项目

```xml
  <plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
    <hostName>localhost</hostName>        <!--   Default: localhost -->
    <port>8080</port>                     <!-- 启动端口 Default:8080 -->
    <path>/</path>   <!-- 访问应用路径  Default: /${project.artifactId}-->
    <uriEncoding>UTF-8</uriEncoding>      <!-- uri编码 Default: ISO-8859-1 -->
    </configuration>
  </plugin>
```
