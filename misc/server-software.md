# 服务器软件的分类

>静态服务：主要提供静态资源，不同用户访问到的资源相同
动态服务：提供动态服务，不同用户访问到的资源不同
服务器的几个叫法：
web服务器：广义上来说，就是响应用户的需求，提供服务，当下所有的服务器软件都可以称之为web服务器软件
HTTP服务器(静态服务)：使用HTTP协议传输资源，提供服务
应用服务器(动态服务)：一个特定应用的承载容器
常见的轻量级服务器软件：
1、Nginx：典型的静态服务器，可做反向代理、负载均衡，一般放在最前面直面用户，和后端Tomcat打配合；纯C写的，性能贼高、内存消耗极少、稳定性也相当好，互联网公司重度使用
2、Tengine：阿里出品，基于Nginx服务器做的改造(加强和封装)，对大流量场景做了很多高级功能，性能、稳定性优秀
3、Apache http server：也是静态服务器，但是不如Nginx
3、IIS：微软开发，只能用在Windows下，具有应用服务器能力的http服务器
4、Tomcat：Apache出品，典型的应用服务器软件，符合Servlet标准的应用容器，也可以提供http服务，但一般不会作为http服务器；是Spring Boot框架默认的内置服务器
5、Jetty：跟Tomcat是一个性质的东西，符合Servlet标准的应用容器，也是Spring Boot框架支持的服务器，但不是默认的
6、Undertow：红帽子出品，跟Tomcat、Jetty一样也是Spring Boot框架支持的服务器，但不是默认的；高并发时性能优于Tomcat、Jetty
几款商用重量级的服务器软件：
1、JBoss(从8版开始更名为WildFly)：不仅是Servlet应用容器，更是EJB的应用容器，整套JavaEE框架部署的解决方案
2、WebLogic：Oracle公司出品，用于部署企业级JavaEE应用，全能型，几乎支持JavaEE所有的应用规范
3、WebSphere：IBM公司出品，支持更多JavaEE的应用规范的综合应用服务器

转自[服务器软件大科普！](https://www.bilibili.com/video/BV1ra4y1t7rs) 评论区

>服务器软件：
运行在服务器操作系统之上，绑定了服务器特定的IP地址，并且在某一个端口监听用户的请求 ，提供服务的软件，都可以叫做服务器软件
静态服务和动态服务
静态服务返回的是静态资源，不同用户无论何时只要访问同一个服务，闹到的内容是一模一样的，
动态服务返回的是动态资源，根据用户不同的身份和权限信息返回不同的内容和资源服务器软件的三个基础概念：
Web服务器
提供web化服务
【网站页面化服务、邮件服务、网络下载的服务】
响应用户的需求，然后提供服务
HTTP服务器
当今Web服务器，应用层通讯协议，主要就是HTTP协议
HTTP服务器只是把服务器上的资源通过HTPP协议形式传输给客户端
应用服务器【常称为动态服务器、动态容器】
一个特定应用的承载容器，需要有运行环境的支持
如Java领域：常用的应用服务器Tomcat，
【
Nginx：典型的HTTP服务器【静态服务器】
Nginx服务器也可以在诸如Lua脚本等辅助下做二次开发，可以变成一个提供动态服务的应用服务器，也就是大名鼎鼎的OpenRestyTengineApche HTTP Service
IIS 微软下的HTTP服务器
】
【
Tomcat 典型的Java应用服务器软件【Web容器】
SpringBoot 内嵌的默认应用服务器
Jetty 也是SpringBoot框架支持的内嵌应用容器之一
官网：Jetty是一个开源的HTTP服务器+应用容器，因此既可以提供静态服务，也可以提供动态服务
Jetty和Tomcat ,两者在功能上几乎是一致的
Jetty比Tomcat更加轻量一些，配置简单一些
Undertow 也是SpringBoot框架支持的内嵌应用容器之一
红帽，基于Java编写的重量级的应用服务器
JBoss ·······》WildflyWebLogic 是Oracle
WebSphere IBM出品
】

转自[服务器软件大科普！](https://www.bilibili.com/video/BV1ra4y1t7rs) 评论区
