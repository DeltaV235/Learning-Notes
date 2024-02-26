#SOA #Microservice

https://bbs.huaweicloud.com/blogs/315752
https://www.cnblogs.com/zhuzhenwei918/p/8745733.html

## SOA

SOA（Service-Oriented Architecture，**面向服务的架构**）是一种高层级的架构设计理念，可通过在网络上使用基于通用通信语言的服务接口，让软件组件可重复使用。

那么什么是服务呢？

所有业务功能都可以定义为一项服务，服务就意味着要对外提供开放的能力，当其他系统需要使用这项功能时，无须定制化开发。

服务可大可小，可简单也可复杂。例如，商品管理可以是一项服务，包括商品基本信息管理、供应商管理、入库管理等功能；而商品基本信息管理也可以作为一项独立的服务，供应商管理也可以作为一项独立的服务。到底是划分为粗粒度的服务，还是划分为细粒度的服务，需要根据企业的实际情况进行判断。

SOA 集成了独立部署和维护的服务，并允许它们相互通信和协同工作，以构建一个跨不同系统的软件应用。

那么它们是如何通信和协同工作的呢？

ESB（Enterprise Service Bus，**企业服务总线**）把企业中各个不同的服务连接在一起。就像计算机总线一样，把计算机的各个不同的设备连接在一起。

因为不同的服务是使用不同的技术实现的，各个独立的服务是异构的，如果没有统一的标准，则各个异构系统对外提供的接口是各式各样的。SOA 使用 ESB 来屏蔽异构系统对外提供各种不同的接口方式，以此来达到服务间高效的互联互通。ESB通过使用标准网络协议（如 SOAP、XML、JSON、MQ ）来开放服务以发送请求或访问数据，实现与各种系统间的协议转换、数据转换、透明的动态路由等功能，消除了开发人员必须从头开始进行集成的困扰。

采用 SOA 架构后，各个服务是相互独立运行的，甚至都不清楚某个服务到底有多少对其他服务的依赖，减少各个服务间的依赖和互相影响，做到了松耦合。  
如果做不到松耦合，某个服务一升级，依赖它的其他服务全部故障，这样肯定是无法满足业务需求的。

## 微服务

微服务（Microservices）是一种软件架构风格，它是以专注于单一责任与功能的小型功能区块 (Small Building Blocks) 为基础，利用模块化的方式组合出复杂的大型应用程序，各功能区块使用与语言无关 (Language-Independent/Language agnostic）的API集相互通信。

微服务的起源是由 Peter Rodgers 博士于 2005 年度云计算博览会提出的微 Web 服务（Micro-Web-Service）开始，Juval Löwy 则是与他有类似的前导想法，将类别变成细粒服务（granular services），以作为微软下一阶段的软件架构，其核心想法是让服务是由类似 Unix 管道的访问方式使用，而且复杂的服务背后是使用简单 URI 来开放接口，任何服务，任何细粒都能被开放（exposed）。这个设计在 HP 的实验室被实现，具有改变复杂软件系统的强大力量。

2014年，Martin Fowler 与 James Lewis 共同提出了微服务的概念，定义了微服务是由以单一应用程序构成的小服务，自己拥有自己的进程与轻量化处理，服务依业务功能设计，以全自动的方式部署，与其他服务使用HTTP API通信。同时服务会使用最小的规模的集中管理 (例如 Docker) 能力，服务可以用不同的编程语言与数据库等组件实现。

## SOA与微服务的对比

### 服务粒度

SOA 的服务粒度要粗一些，而微服务的服务粒度要细一些。例如，对一个电商企业来说，商品管理系统是一个 SOA 架构中的服务；而如果采用微服务架构，则商品管理系统会被拆分为更多的服务，比如商品基本信息管理、供应商管理、入库管理等更多服务。

### 服务通信

SOA 采用了 ESB 作为服务间通信的关键组件，负责服务定义、服务路由、消息转换、消息传递，一般情况下都是重量级的实现。微服务则使用统一的协议和格式，例如：HTTP RESTful 协议、TCP RPC 协议，不需要 ESB 这样的重量级实现。

### 服务交付

SOA 对服务的交付没有特殊要求，因为 SOA 更多考虑的是兼容已有的系统；微服务的架构理念则要求快速交付，相应地要求采取自动化测试、持续集成、自动化部署、自动化运维等的最佳实践。

### 应用场景

SOA 更加适合于庞大、复杂、异构的企业级系统。这类系统的典型特征就是很多系统已经发展多年，各个服务具有异构性，比如：采用不同的企业级技术、有的是内部开发的、有的是外部购买的，无法完全推倒重来或者进行大规模的优化和重构。因为成本和影响太大，只能采用兼容的方式进行处理，而承担兼容任务的就是 ESB。

微服务更加适合于快速、轻量级、基于 Web 的互联网系统，这类系统业务变化快，需要快速尝试、快速交付；同时基本都是基于 Web，虽然开发技术可能差异很大（例如，Java、.NET、PHP 等），但对外接口基本都是提供 HTTP RESTful 风格的接口，无须考虑在接口层进行类似 SOA 的 ESB 那样的处理。

还有一些不同，为了方便理解记忆，汇总成如下表格：

||SOA|微服务|
|---|---|---|
|服务粒度|粗粒度|细粒度|
|业务划分方式|水平多层|纵向业务划分|
|部署方式|整体部署|独立部署|
|通信方式|使用重量级通信方式，ESB充当服务之间通信的角色|使用轻量级通信方式，如HTTP RESTful|
|服务交付|交付慢|交付块|
|应用场景|庞大、复杂、异构的企业级系统|快速、轻量级、基于 Web 的互联网系统|

## SOA和Web Service关系是怎样的？　

SOA理念被提出之后，仅仅是概念上的，并没有落地。而当Web Servcie标准成熟和应用普及之后，SOA才真正被用在工程商。所以，**几乎所有的SOA应用场合都是和Web Service绑定的，且Web Service是现在最适合实现SOA的技术，**目前，我们基本认同Web Service技术在几方面体现了SOA的需要：

1. **基于标准访问的独立功能实体满足了松耦合要求 --- 在Web Service中所有的访问都通过SOAP进行**，**用WSDL定义的接口封装，通过UDDI进行目录查找，可以动态改变一个服务的提供方而无需影响客户端的配置，外界客户端是根本不关心服务器端的实现的。**
2. **适合大数据量、低频率访问，符合服务大颗粒度功能 ---** 基于性能和效率平衡的要求，SOA的服务提供的是大颗粒度的应用功能，而且跨系统边界的访问频率也不会像程序见函数调用那么频繁。 通过WSDL和基于文本的SOAP请求，可以实现能一次性接受处理大量数据。
3. 基于标准的文本消息传递为异构系统提供通讯机制：**web service所有的通讯都是通过SOAP进行的**，而SOAP是基于XML的，XML是结构化的文本消息。

**即SOA不是Web Servcie，而Web Service是目前最适合实现SOA的技术。**
**即Web Service是一套标准，可以使SOA思想用于实践。**

## 什么是Web Service？

WebService是一种**跨编程语言**和**跨操作系统平台**的**远程调用技术**。 所谓跨编程语言是说**服务器端可以用java写，而客户端可以用其他语言编写**，反之亦然。 而跨操作系统平台是指**服务器端程序和客户端程序可以在不同的操作系统上运行**。而所谓**远程调用**是说**一台计算机a上的一个程序可以调用另外一个计算机b上的一个对象的方法，**其实这就是普通的暴露接口，如腾讯qq把相关登录接口暴露出来给其他小企业用，而第三方网站可以调用这些服务功能，但这些功能的代码实际上是跑在腾讯qq上的，这就是远程调用了。

**实际上，Web Service是建立可操作的分布式应用程序的新平台，是一个平台，是一套标准，它定义了应用程序如何在web上实现互操作性，你可以用任何你喜欢的语言在任何你喜欢上的平台中写web service，只要我们可以通过web service标准对这些服务进行查询和访问。**

**即Web Service一种跨编程语言、操作系统平台的远程调用技术。**

## 如何实现WebService平台技术？

**实现webservice有三大技术 --- XML + XSD、SOAP、WSDL**。

### 1、XML + XSD
WebService采用http协议传输数据，**采用XML格式封装数据**（即XML中说明调用远程服务对象的哪个方法、什么参数、返回结果），XML主要的优点是它与平台、厂商都是无关的。

XML解决了数据表示的问题，但是没有定义一套标准的数据类型，比如整形数代表什么？ 16位还是32位？ 这些细节是非常重要的，而XML Schema（**XSD),** 其中schema的意思就是概要，即**XSD定义了一套标准的数据类型，并给出了一套语言来扩展这套数据类型，**webservice平台就是使用XSD来作为数据类型系统的，当你构造一个webservice时，为了符合其标准，你所使用的数据类型就必须被转换为XSD类型。 

### 2、SOAP　　

即Simple Object Access Protocol，中文就是**简单对象访问协议，简称SOAP，如下就是一个SOAP：**

```xml
<?xml version="1.0"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">

  <soap:Header>
      ...
      ...
  </soap:Header>

  <soap:Body>
      ...
      ...
      <soap:Fault>
        ...
        ...
      </soap:Fault>
  </soap:Body>

</soap:Envelope>
```

我们可以看到，这个SOAP不是简单的XML，而是经过SOAP标准改造之后得到的，SOAP可以在多种协议上传输，而**大多数情况是绑定在HTTP协议传输的，所以就导致大多数人认为SOAP就是HTTP+XML，或者认为SOAP是HTTP post请求的一个专用版本。**

**![](https://images2018.cnblogs.com/blog/1044137/201804/1044137-20180408170625709-1355817671.png)**

####  WSDL

即**web service description language**，**网络服务描述语言**，简称为WSDL。 它是一门**基于XML的语言。**用于描述Web Service以及如何对他们进行范文。文档主要用下面几个元素来描述某个web service：
  
- <portType> 执行的操作
- <message> 使用的消息
- <types> 使用的数据类型
- <binding> 使用的通信协议　　

```xml
<wsdl:definitions  
    xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"  
    xmlns:tns="tns"   xmlns:plink="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"  
    xmlns:xop="http://www.w3.org/2004/08/xop/include" xmlns:senc="http://schemas.xmlsoap.org/soap/encoding/"  
    xmlns:s12env="http://www.w3.org/2003/05/soap-envelope/"  
    xmlns:s12enc="http://www.w3.org/2003/05/soap-encoding/"  
    xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:senv="http://schemas.xmlsoap.org/soap/envelope/"  
    xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" targetNamespace="tns"  
    name="Application">
    <wsdl:types>
        <xs:schema   targetNamespace="tns"   elementFormDefault="qualified">
            <xs:import   namespace="http://www.w3.org/2001/XMLSchema" />
            <xs:complexType   name="say_hello">
                <xs:sequence>
                    <xs:element   name="name"   type="xs:string"  
                        minOccurs="0"   nillable="true" />
                </xs:sequence>
            </xs:complexType>
            <xs:complexType   name="say_helloResponse">
                <xs:sequence>
                    <xs:element   name="say_helloResult"   type="xs:string"  
                        minOccurs="0"   nillable="true" />
                </xs:sequence>
            </xs:complexType>
            <xs:element   name="say_hello"   type="tns:say_hello" />
            <xs:element   name="say_helloResponse"  
                type="tns:say_helloResponse" />
        </xs:schema>
    </wsdl:types>
    <wsdl:message   name="say_hello">
        <wsdl:part   name="say_hello"   element="tns:say_hello" />
    </wsdl:message>
    <wsdl:message   name="say_helloResponse">
        <wsdl:part   name="say_helloResponse"  
            element="tns:say_helloResponse" />
    </wsdl:message>
    <wsdl:portType   name="Application">
        <wsdl:operation   name="say_hello"   parameterOrder="say_hello">
            <wsdl:input   name="say_hello"   message="tns:say_hello" />
            <wsdl:output   name="say_helloResponse"  
                message="tns:say_helloResponse" />
        </wsdl:operation>
    </wsdl:portType>
    <wsdl:binding   name="Application"   type="tns:Application">
        <soap:binding   style="document"  
            transport="http://schemas.xmlsoap.org/soap/http" />
        <wsdl:operation   name="say_hello">
            <soap:operation   soapAction="say_hello"   style="document" />
            <wsdl:input   name="say_hello">
                <soap:body   use="literal" />
            </wsdl:input>
            <wsdl:output   name="say_helloResponse">
                <soap:body   use="literal" />
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>
    <wsdl:service   name="Application">
        <wsdl:port   name="Application"   binding="tns:Application">
            <soap:address   location="http://10.2.70.10:7789/SOAP/?wsdl" />
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>
```

对于接口来说，接口文档非常重要，它描述了如何去访问接口，WSDL可以看做web service接口的一种标准格式的文档。 

#### UDDI

**Universal Description, Discovery and Integration"，可译为“通用描述、发现与集成服务”，简称UDDI**。WSDL用来描述了访问特定的web service的一些相关消息，但是在互联网上，如何发现我们需要的web service呢？ 这时就要用到UDDI了。

- UDDI 是一个独立于平台的框架，用于通过使用 Internet 来描述服务，发现企业，并对企业服务进行集成。
- UDDI 指的是通用描述、发现与集成服务
- UDDI 是一种用于存储有关 web services 的信息的目录。
- UDDI 是一种由 WSDL 描述的 web services 界面的目录。
- UDDI 经由 SOAP 进行通信
- UDDI 被构建入了微软的 .NET 平台

UDDI**可以帮助web服务器提供商在互联网上发布web service的信息，UDDI是一种目录服务，企业可以通过UDDI来注册和搜索web services。** 

**通过前面的介绍可以知道： SOAP、WSDL和UDDI构成了web service的三要素。**

## Web Service体系结构

体系结构包括了**webservice提供者、**webservice中介、**webservice请求者，******这三者就对应了三个动作**发布、绑定、查找。**

**webservice提供者**：即发布webservice，等待其他的服务或应用程序访问自己。
**webservice请求者**：即通过**SOAP**消息向webservice提供者发送请求以获得服务。 
**webservice中介**：即通过代理，**把一个webservice请求者和一个webservice提供者联系在一起，通过UDDI实现。**

**总结：**

**web service和http是可以互相替代的，但是web service也有一些自身的优点，**比如接口中实现的方法和要求参数一目了然、不用担心大小写问题、不用担心中文Urlencode问题、传递参数可以是数组对象等。
