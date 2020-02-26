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

## 设置Maven项目的jdk版本

**Setting the -source and -target of the Java Compiler**
Sometimes when you may need to compile a certain project to a different version than what you are currently using. The javac can accept such command using -source and -target. The Compiler Plugin can also be configured to provide these options during compilation.

For example, if you want to use the Java 8 language features (-source 1.8) and also want the compiled classes to be compatible with JVM 1.8 (-target 1.8), you can either add the two following properties, which are the default property names for the plugin parameters:

```xml

<project>
  [...]
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  [...]
</project>
```

or configure the plugin directly:

```xml
<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
    [...]
  </build>
  [...]
</project>
```
