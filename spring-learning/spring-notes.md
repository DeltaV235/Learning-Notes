# Spring 笔记

## Spring模块

![spring framework runtime](imgs/spring-framework-runtime.png)

## pom配置

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.2.4.RELEASE</version>
    </dependency>
</dependencies>
```

## IOC/DI

### IOC:(Inversion(反转) Of Control) 控制反转

- 控制:
  - 资源的获取方式:
    主动式：创建需要的对象

    ```java
    Person person = new Person();
    ```

  - 被动式：资源的获取不是我们自己创建，而是交给一个容器来创建和设置

  ```java
  BookServlet{
          BookService bs;
          public void test01(){
              bs.checkout();
          }
  }
  ```

- 容器：管理所有的组件（有功能的类）；假设，BookServlet受容器管理，BookService也受容器管理；容器可以自动的探查出那些组件（类）需要用到另一写组件（类）；容器帮我们创建BookService对象，并把BookService对象赋值过去；将主动的new资源变为被动的接受资源；

### DI:(Dependency Injection) 依赖注入

容器能知道哪个组件（类）运行的时候，需要另外一个类（组件）；容器通过反射的形式，将容器中准备好的BookService对象注入（利用反射给属性赋值）到BookServlet中；

只要IOC容器管理的组件，都能使用容器提供的强大功能；

## IOC容器

**步骤**:

1. 在配置文件中注册组件
2. 使用ClassPathXMLApplicationContext()实例化IOC容器
3. 通过IOC容器获取指定的对象

### 组件的注册

使用`<bean>`标签实例化一个对象

```xml
<bean id="person01" class="com.wuyue.domain.Person">
```

- 属性:
  - class: 要注册的组件的类全限定名
  - id: 这个对象在IOC容器中的唯一标识

**等价于**:

```java
Person person01 = new Person()
```

#### 通过\<property\>标签为指定的bean属性赋值

```xml
<bean id="person01" class="com.wuyue.domain.Person">
    <property name="name" value="wuyue"/>
    <property name="age" value="18"/>
    <property name="email" value="wuyue@wuyue.com"/>
    <property name="gender" value="m"/>
</bean>

<bean id="person02" class="com.wuyue.domain.Person">
    <property name="name" value="nini"/>
</bean>

```

- property属性:
  - name: bean对象的属性名
  - value: 属性值(该属性的数据类型只能是Java原生数据类型，这样IOC才能正确的进行自动类型转换(将xml中的String转为属性的真实数据类型))

IOC通过bean类的setter/getter方法的方法名来判断属性名，即 setUserName() --> userName ，所以setter/getter方法名发生改变会导致bean的属性也发生变化，属性名是有setter和getter方法决定的，而不是bean的实例域决定的

property标签等价于java中的 `person.setProperty();` 调用已实例化的对象中的setter方法为指定的属性赋值

#### 使用bean的构造构造方法完成实例化

```xml
<bean id="person03" class="com.wuyue.domain.Person">
    <constructor-arg name="age" value="19"/>
    <constructor-arg name="email" value="spring@test.com"/>
    <constructor-arg name="gender" value="f"/>
    <constructor-arg name="name" value="spring"/>
</bean>
<bean id="person04" class="com.wuyue.domain.Person">
    <constructor-arg value="妮妮"/>
    <constructor-arg value="24"/>
    <constructor-arg value="nini@test.com" index="3"/>
    <constructor-arg value="f"/>
</bean>
```

调用组件的有参构造器完成对象的实例化
constructor-arg标签的数量应与构造器的形参数量一致

- 属性:
  - name: 构造器形参名
  - value: 简单数据类型的值
  - index: 该标签对应构造器中参数的索引位置

其中的name属性可以省略，constructor-arg标签中的值应与构造器参数的位置相对应

#### 通过p命名空间为bean赋值

#### 属性的引用

```xml
<property name="" ref="">
```

使用property的ref属性，可以指定外部的bean对象，若引用的外部对象是单实例的，则直接引用该对象;  
若外部引用对象是多实例的，则会创建一个新的对象，并赋值

![singleton](imgs/singleton.png)
![prototype](imgs/prototype.png)

#### bean的继承

**NOTE**: 使用parent属性继承的引用类型对象是父类真实对象的一个深克隆，即 对引用类型成员变量的修改不会影响父类中该成员变量的值

#### 使用工厂模式创建实例

#### 使用静态工厂创建实例

```xml
<bean class="com.wuyue.factory.CarStaticFactory" id="carStaticFactory" factory-method="getCar">
    <constructor-arg value="5000"/>
</bean>
```

#### 使用实例工厂创建实例

```xml
<bean class="com.wuyue.factory.CarInstanceFactory" id="carInstanceFactory"/>
<bean class="com.wuyue.domain.Car" id="car" factory-bean="carInstanceFactory" factory-method="getCar">
    <constructor-arg value="800000"/>
</bean>
```

先创建工厂实例，再调用工程实例创建对象

#### 使用FactoryBean实现类工厂创建实例

```xml
<bean class="com.wuyue.factory.CarFactoryWithBean" id="car2"/>
```

```java
public class CarFactoryWithBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setCarName("Benz");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
```

在配置文件中直接注册这个工厂类即可，能够直接从IOC容器获取实例  
**NOTE**: 使用FactoryBean实现类工厂创建实例，不论是否是单实例，该工厂都只在获取实例对象的时候才会创建对象。
不同于普通的单例模式，在IOC容器初始化完成之前就创建实例

#### 创建带有生命周期方法的bean

生命周期:bean的创建到销毁；

```xml
<bean id="book01" class="com.atguigu.bean.Book"
destroy-method="myDestory" init-method="myInit" >
</bean>
```

- ioc容器中注册的bean的生命周期
1.单例bean，容器启动的时候就会创建好，容器关闭也会销毁创建的bean
2.多实例bean，获取的时候才创建

我们可以为bean自定义一些生命周期方法: spring在创建或者销毁的时候就会调用指定的方法  
这些初始化方法会在对象实例完成、property参数都设置完毕后执行。初始化和销毁方法不能带有参数，但可以抛出异常

- 定义了初始化和销毁方法的bean生命周期
  - 单例:
容器启动---->构造器---->(执行property标签的赋值)---->(初始化方法)---->(销毁方法)---->(容器关闭)
  - 多实例:
获取bean对象---->构造器---->(执行property标签的赋值)---->(初始化方法)---->(容器关闭不会调用bean的销毁方法)

- 后置处理器:
  - 单例:
容器启动---->构造器---->(执行property标签的赋值)---->(后置处理器before)---->(初始化方法)---->(后置处理器after)---->bean初始化完成---->(销毁方法)---->(容器关闭)
  - 多实例:
获取bean---->构造器---->(执行property标签的赋值)---->(后置处理器before)---->(初始化方法)---->(后置处理器after)---->bean初始化完成---->(销毁方法)

无论bean是否有初始化方法；后置处理器都会默认其有，还会继续工作;后置处理器对任何从该IOC容器中获取的对象都执行

#### IOC容器配置文件中引入外部的Properties文件

实例化PropertySourcesPlaceholderConfigurer对象，指定location属性为properties文件的路径，相对路径默认从类路径开始

```xml
<bean class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
    <property name="location" value="dbconfig.properties"/>
</bean>
```

通过${key}的方式，获取指定key的value值

*Druid数据库连接池的单实例对象:*

```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
    <property name="url" value="${mysql.url}"/>
    <property name="username" value="${mysql.username}"/>
    <property name="password" value="${mysql.password}"/>

    <property name="filters" value="stat"/>

    <property name="maxActive" value="20"/>
    <property name="initialSize" value="1"/>
    <property name="maxWait" value="60000"/>
    <property name="minIdle" value="1"/>

    <property name="timeBetweenEvictionRunsMillis" value="60000"/>
    <property name="minEvictableIdleTimeMillis" value="300000"/>

    <property name="testWhileIdle" value="true"/>
    <property name="testOnBorrow" value="false"/>
    <property name="testOnReturn" value="false"/>

    <property name="poolPreparedStatements" value="true"/>
    <property name="maxOpenPreparedStatements" value="20"/>

    <property name="asyncInit" value="true"/>
</bean>
```

#### 自动装配

自动为自定义的类型进行赋值

自动装配：（仅限于对自定义类型的属性有效）

在bean标签中设置autowire属性

- autowire="default/no"：不自动装配；不自动为car属性赋值
- autowire="byName"：按照名字；
以属性名(car)作为id去容器中找到这个组件，给他赋值；如果找不到就装配null；
<==> car = ioc.getBean("car");
- autowire="byType":
1.以属性的类型作为查找依据去容器中找到这个组件；如果容器中有多个这个类型的组件，报错；
NoUniqueBeanDefinitionException:
No qualifying bean of type [com.atguigu.bean.Car] is defined:
expected single matching bean but found 2: car01,car02
2.若为找到这个类型的组件,则装配null
<==> car = ioc.getBean(Car.class);
- autowire="constructor":
按照构造器进行赋值；
1.先按照有参构造器参数的类型进行装配（成功就赋值）；没有就直接为组件装配null即可。
2.如果按照类型找到了多个；参数的名作为id继续匹配;找到就装配；找不到就null；
3.不会报错；

#### SpEL

SpEL(Spring Expression Language)Spring表达式语言

能够在在SpEL中使用字面量、引用其他bean、引用其他bean的某个属性值、【 调用非静态方法、调用静态方法】、使用运算符

```xml
<bean id="person04" class="com.atguigu.bean.Person">
  <!-- 字面量：${};   #{} -->
  <property name="salary" value="#{12345.67*12}"></property>
  <!--  引用其他bean的某个属性值、 -->
  <property name="lastName" value="#{book01.bookName}"></property>
  <!--  引用其他bean、 -->
  <property name="car" value="#{car}"></property>
  <!-- 
      调用静态方法： UUID.randomUUID().toString();
      #{T(全类名).静态方法名(1,2)}
     -->
 <property name="email" value="#{T(java.util.UUID).randomUUID().toString().substring(0,5)}"></property>
 <!--   调用非静态方法;  对象.方法名  -->
 <property name="gender" value="#{book01.getBookName()}"></property>
</bean>
```

#### 通过注解注册组件

通过给bean上添加某些注解,可以快速的将bean加入到IOC容器中

- Spring有四个注解
  - @Controller
  **控制器**    推荐给控制器层(servlet)的组件添加这个注解
  - @Service
  **业务逻辑**  推荐业务逻辑层(service)的组件添加这个注解
  - @Repository
  **仓库**      推荐数据库层(dao 持久化层)的组件使用这个注解
  - @Component
  **组件**      不属于以上几层的组件添加这个注解

**NOTE**: 注解可以随意添加,Spring底层不会验证这个组件是否和注解指定的层次一致,推荐各层使用各层的注解以提高代码可读性

**使用注解将组件快速的加入到容器中的步骤**:

1. 给要添加的组件标上以上四个注解之一
2. 在Spring IOC配置文件中使用`<context:component-scan base-package="">`标签指定自动扫描组件的基包,Spring会自动注册该包下及其子包下的所有使用了注解的类
3. 导入aop包以提供注解模式

**NOTE**: 使用注解创建的bean对象id为类名首字母小写.和使用配置文件加入到容器中的组件行为默认都是一样的,如组件的id默认就是类名首字母小写 组件的作用域默认就是单例的

**详细说明**
[1]base-package属性指定一个需要扫描的基类包，Spring容器将会扫描这个基类包及其子包中的所有类。
[2]当需要扫描多个包时可以使用逗号分隔。
[3]如果仅希望扫描特定的类而非基包下的所有类，可使用resource-pattern属性过滤特定的类，示例：

```xml
<context:component-scan
  base-package="com.atguigu.component"
  resource-pattern="autowire/*.class"/>
```

##### 通过注解添加的组件默认行为的调整

###### 更改默认id

在注册注解中指定value的值

```java
@Component("componentName")
```

###### 更改作用域

使用@Scope注解,指定注解的value为bean的作用域

```java
@Scope("prototype")
```

##### 包含于排除

component-scan下可以拥有若干个include-filter和exclude-filter子节点

###### 使用\<context: exclude-filter>排除不需要注册的类

```xml
<context:component-scan base-package="com.wuyue">
    <context:exclude-filter type="assignable" expression="com.wuyue.service.BookService"/>
</context:component-scan>
```

*属性type的值和expression表达式的说明*:

|    类别    |           示例            |                             说明                             |
| :--------: | :-----------------------: | :----------------------------------------------------------: |
| annotation | com.atguigu.XxxAnnotation | 过滤所有标注了XxxAnnotation的类。这个规则根据目标组件是否标注了指定类型的注解进行过滤。 |
| assignable |    com.atguigu.BaseXxx    | 过滤所有BaseXxx类的子类。这个规则根据目标组件是否是指定类型的子类的方式进行过滤。 |
|  aspectj   |   com.atguigu.*Service+   | 所有类名是以Service结束的，或这样的类的子类。这个规则根据AspectJ表达式进行过滤。 |
|   regex    |   com\.atguigu\.anno\.*   | 所有com.atguigu.anno包下的类。这个规则根据正则表达式匹配到的类名进行过滤。 |
|   custom   | com.atguigu.XxxTypeFilter | 使用XxxTypeFilter类通过编码的方式自定义过滤规则。该类必须实现org.springframework.core.type.filter.TypeFilter接口 |

###### \<context:exclude-filter>指定包含的组件

\<context:include-filter>子节点表示要包含的目标类  
默认情况下\<context:component-sacn>会将所有组件都进行扫描,所以一定要禁用默认过滤规则才能使\<context:include-filter>标签生效

注意：通常需要与use-default-filters属性配合使用才能够达到“仅包含某些组件”这样的效果。即：通过将use-default-filters属性设置为false，禁用默认过滤器，然后扫描的就只是include-filter中的规则指定的组件了。

```xml
<context:component-scan use-default-filters="false" base-package="com.wuyue">
    <context:include-filter type="assignable" expression="com.wuyue.service.BookService"/>
</context:component-scan>
```

### 使用@Autowired注解实现根据类型实现自动装配

#### @Autowired执行步骤

**先按照类型去容器中找到对应的组件 <==> bookService = ioc.getBean(BookService.class)**
-----**找到一个**: 直接赋值
-----**没有找到**: 抛异常
-----**找到多个**: 以需要自动装配的变量**变量名**作为id继续匹配,若未找到则抛异常

在通过类型找到多个bean实例时,可以使用@Qualifier("idName")注解指定bean的名称,若仍未找到对应的bean则抛异常

**默认情况**下，所有使用@Autowired注解的属性都需要被设置。当Spring找不到匹配的bean装配属性时，会抛出异常。  
若某一属性允许不被设置(null)，可以设置@Autowired注解的required属性为 false,有对应的bean则装配,没有则不装配,不抛异常

@Autowired能够为方法的参数自动装配,参数也能够标注@Qualifier()注解来指定装配的bean的id.  
**NOTE**: 被@Autowired标注的方法在IOC容器初始化的时候就会被执行

**Q**: 尽管在方法上的@Autowired设置了request为false,但如果在容器中没有找到对应id的bean依旧会抛出异常;用@Qualifier注解指定的id若找不到,则方法不被执行

构造器、普通字段(即使是非public)、一切具有参数的方法都可以应用@Autowired注解

#### @Autowired和@Resource的区别

@Autowired @Resource @Inject 都是自动装配的意思
@Autowired 功能强大,是Spring提供的注解
@Resource javax包提供的注解

@Resource 扩展性更强,因为是Java的标准.如果切换成另一个容器框架,@Resource依然可以使用,@Autowired则不行

### 泛型依赖注入

Spring可以通过带泛型的父类类型来确定这个子类的类型,Spring注入依赖的时候不光选择类型对应的组件,并且会找到对应泛型的组件

![generic-dependency-injection](imgs/generic-dependency-injection.svg)

## Spring的单元测试

1. 导包: spring-test-x.x.x.RELEASE.jar
2. @ContextConfiguration(locations="")指定Spring配置文件的位置
3. @Runwith指定用哪种驱动进行单元测试,默认是junit
@RunWith(SpringJUnit4ClassRunner.class) 使用Spring的测试模块来执行标了@Test注解的测试方法
不需要使用ioc.getBean()来获取组件,直接使用@Autowired获取组件,Spring自动装配

## AOP

Aspect Oriented Programming: 面向切面编程
面向切面编程：基于OOP基础之上新的编程思想;指在程序运行期间，将某段代码动态的切入到指定方法的指定位置进行运行的这种编程方式，面向切面编程

AOP不用对象实现接口就可以实现动态代理的功能
