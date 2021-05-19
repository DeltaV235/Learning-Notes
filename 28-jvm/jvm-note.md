# JVM Note

## Reference document

[Oracle JDK Java Command Options Explain](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html)

## 一.JVM 与 Java 体系结构

### 1.JVM 的架构模型

### 2.JVM 的生命周期

1.启动

通过引导类加载器（bootstrap class loader）创建一个初始类（initial class）来完成的，这个类是由虚拟机的具体实现指定的.

2.执行

- 一个运行中的 java 虚拟机有着一个清晰的任务：执行 Java 程序；
- 程序开始执行的时候他才运行，程序结束时他就停止；
- 执行一个所谓的 Java 程序的时候，真真正正在执行的是一个叫做 Java 虚拟机的进程。

  3.退出

- 程序正常执行结束
- 程序异常或错误而异常终止
- 操作系统错误导致终止
- 某线程调用 Runtime 类或 System 类的 exit 方法，或 Runtime 类的 halt 方法，并且 java 安全管理器也允许这次 exit 或 halt 操作
- 除此之外，JNI 规范描述了用 JNI Invocation API 来加载或卸载 Java 虚拟机时，Java 虚拟机的退出情况

### 3.JVM 的发展历史

#### Sun Classic VM

- 1996 年 JDK1.0 发布时，Sun 公司发布了一款名为 Sun Classic VM 的 Java 虚拟机，它同时是**世界上第一款商用 Java 虚拟机**，JDK1.4 时完全被淘汰。
- 这款虚拟机内部只提供解释器。
- 如果使用 JIT 编译器，就需要进行外挂。但是一旦使用了 JIT 编译器，JIT 就会接管虚拟机的执行系统。解释器就不再工作。解释器和编译器不能配合工作。
- 现在 HotSpot 内置了次虚拟机

#### Exact VM

1.为了解决上一个虚拟机问题，jdk1.2 时，Sun 提供了此虚拟机。

2.Exact Memory Management：准确式内存管理

- 也可以叫 Non-Conservative/Accurate Memory Management
- 虚拟机可以知道内存中某个位置的数据具体是什么类型。

  3.具备现代高性能虚拟机的维形

- 热点探测（寻找出热点代码进行缓存）
- 编译器与解释器混合工作模式

  4.只在 Solaris 平台短暂使用，其他平台上还是 classic vm，英雄气短，终被 Hotspot 虚拟机替换

#### HotSpot VM

1.HotSpot 历史

- 最初由一家名为“Longview Technologies”的小公司设计
- 1997 年，此公司被 Sun 收购；2009 年，Sun 公司被甲骨文收购。
- JDK1.3 时，HotSpot VM 成为默认虚拟机

  2.目前 Hotspot 占有绝对的市场地位，称霸武林。

- 不管是现在仍在广泛使用的 JDK6，还是使用比例较多的 JDK8 中，默认的虚拟机都是 HotSpot
- Sun/oracle JDK 和 openJDK 的默认虚拟机
- 因此本课程中默认介绍的虚拟机都是 HotSpot，相关机制也主要是指 HotSpot 的 GC 机制。（比如其他两个商用虚机都没有方法区的概念）

  3.从服务器、桌面到移动端、嵌入式都有应用。

  4.名称中的 HotSpot 指的就是它的热点代码探测技术。

- 通过计数器找到最具编译价值代码，触发即时编译或栈上替换
- 通过编译器与解释器协同工作，在最优化的程序响应时间与最佳执行性能中取得平衡

#### JRockit（商用三大虚拟机之一）

1.专注于服务器端应用：它可以不太关注程序启动速度，因此 JRockit 内部不包含解析器实现，全部代码都靠即时编译器编译后执行。

2.大量的行业基准测试显示，JRockit JVM 是世界上最快的 JVM：使用 JRockit 产品，客户已经体验到了显著的性能提高（一些超过了 70%）和硬件成本的减少（达 50%）。

3.优势：全面的 Java 运行时解决方案组合

- JRockit 面向延迟敏感型应用的解决方案 JRockit Real Time 提供以毫秒或微秒级的 JVM 响应时间，适合财务、军事指挥、电信网络的需要
- Mission Control 服务套件，它是一组以极低的开销来监控、管理和分析生产环境中的应用程序的工具。

  4.2008 年，JRockit 被 Oracle 收购。

  5.Oracle 表达了整合两大优秀虚拟机的工作，大致在 JDK8 中完成。整合的方式是在 HotSpot 的基础上，移植 JRockit 的优秀特性。

#### IBM 的 J9（商用三大虚拟机之一）

1. 全称：IBM Technology for Java Virtual Machine，简称 IT4J，内部代号：J9
2. 市场定位与 HotSpot 接近，服务器端、桌面应用、嵌入式等多用途 VM 广泛用于 IBM 的各种 Java 产品。
3. 目前，有影响力的三大商用虚拟机之一，也号称是世界上最快的 Java 虚拟机。
4. 2017 年左右，IBM 发布了开源 J9VM，命名为 openJ9，交给 Eclipse 基金会管理，也称为 Eclipse OpenJ9
5. OpenJDK -> 是 JDK 开源了，包括了虚拟机

#### KVM 和 CDC/CLDC Hotspot

1.Oracle 在 Java ME 产品线上的两款虚拟机为：CDC/CLDC HotSpot Implementation VM

2.KVM（Kilobyte）是 CLDC-HI 早期产品

3.目前移动领域地位尴尬，智能机被 Android 和 iOS 二分天下。

4.KVM 简单、轻量、高度可移植，面向更低端的设备上还维持自己的一片市场

- 智能控制器、传感器
- 老人手机、经济欠发达地区的功能手机

  5.所有的虚拟机的原则：一次编译，到处运行。

#### Azul VM（饿了吗）

1. 前面三大“高性能 Java 虚拟机”使用在通用硬件平台上
2. 这里 Azul VW 和 BEA Liquid VM 是与特定硬件平台绑定、软硬件配合的专有虚拟机：高性能 Java 虚拟机中的战斗机。
3. Azul VM 是 Azul Systems 公司在 HotSpot 基础上进行大量改进，运行于 Azul Systems 公司的专有硬件 Vega 系统上的 Java 虚拟机。
4. 每个 Azul VM 实例都可以管理至少数十个 CPU 和数百 GB 内存的硬件资源，并提供在巨大内存范围内实现可控的 GC 时间的垃圾收集器、专有硬件优化的线程调度等优秀特性。
5. 2010 年，Azul Systems 公司开始从硬件转向软件，发布了自己的 Zing JVM，可以在通用 x86 平台上提供接近于 Vega 系统的特性。

#### Liquid VM

1. 高性能 Java 虚拟机中的战斗机。
2. BEA 公司开发的，直接运行在自家 Hypervisor 系统上
3. Liquid VM 即是现在的 JRockit VE（Virtual Edition）。Liquid VM 不需要操作系统的支持，或者说它自己本身实现了一个专用操作系统的必要功能，如线程调度、文件系统、网络支持等。
4. 随着 JRockit 虚拟机终止开发，Liquid vM 项目也停止了。

#### Apache Marmony

1. pache 也曾经推出过与 JDK1.5 和 JDK1.6 兼容的 Java 运行平台 Apache Harmony。
2. 它是 IElf 和 Intel 联合开发的开源 JVM，受到同样开源的 Open JDK 的压制，Sun 坚决不让 Harmony 获得 JCP 认证，最终于 2011 年退役，IBM 转而参与 OpenJDK
3. 虽然目前并没有 Apache Harmony 被大规模商用的案例，但是它的 Java 类库代码吸纳进了 Android SDK。

#### Micorsoft JVM

1. 微软为了在 IE3 浏览器中支持 Java Applets，开发了 Microsoft JVM。
2. 只能在 window 平台下运行。但确是当时 Windows 下性能最好的 Java VM。
3. 1997 年，Sun 以侵犯商标、不正当竞争罪名指控微软成功，赔了 Sun 很多钱。微软 WindowsXP SP3 中抹掉了其 VM。现在 Windows 上安装的 jdk 都是 HotSpot。

#### Taobao JVM

1.由 AliJVM 团队发布。阿里，国内使用 Java 最强大的公司，覆盖云计算、金融、物流、电商等众多领域，需要解决高并发、高可用、分布式的复合问题。有大量的开源产品。

2.基于 OpenJDK 开发了自己的定制版本 AlibabaJDK，简称 AJDK。是整个阿里 Java 体系的基石。

3.基于 OpenJDK Hotspot VM 发布的国内第一个优化、深度定制且开源的高性能服务器版 Java 虚拟机。

- 创新的 GCIH（GCinvisible heap）技术实现了 off-heap，即将生命周期较长的 Java 对象从 heap 中移到 heap 之外，并且 GC 不能管理 GCIH 内部的 Java 对象，以此达到降低 GC 的回收频率和提升 GC 的回收效率的目的。
- GCIH 中的对象还能够在多个 Java 虚拟机进程中实现共享
- 使用 crc32 指令实现 JvM intrinsic 降低 JNI 的调用开销
- PMU hardware 的 Java profiling tool 和诊断协助功能
- 针对大数据场景的 ZenGC

  4.taobao vm 应用在阿里产品上性能高，硬件严重依赖 inte1 的 cpu，损失了兼容性，但提高了性能

- 目前已经在淘宝、天猫上线，把 Oracle 官方 JvM 版本全部替换了。

#### Dalvik VM

1.谷歌开发的，应用于 Android 系统，并在 Android2.2 中提供了 JIT，发展迅猛。

2.Dalvik VM 只能称作虚拟机，而不能称作“Java 虚拟机”，它没有遵循 Java 虚拟机规范

3.不能直接执行 Java 的 Class 文件

4.基于寄存器架构，不是 jvm 的栈架构。

5.执行的是编译以后的 dex（Dalvik Executable）文件。执行效率比较高。

- 它执行的 dex（Dalvik Executable）文件可以通过 class 文件转化而来，使用 Java 语法编写应用程序，可以直接使用大部分的 Java API 等。

  6.Android 5.0 使用支持提前编译（Ahead of Time Compilation，AoT）的 ART VM 替换 Dalvik VM。

#### Graal VM（未来虚拟机）

1. 2018 年 4 月，Oracle Labs 公开了 GraalvM，号称 “Run Programs Faster Anywhere”，勃勃野心。与 1995 年 java 的”write once，run anywhere"遥相呼应。

2. GraalVM 在 HotSpot VM 基础上增强而成的**跨语言全栈虚拟机，可以作为“任何语言”**的运行平台使用。语言包括：Java、Scala、Groovy、Kotlin；C、C++、Javascript、Ruby、Python、R 等

3. 支持不同语言中混用对方的接口和对象，支持这些语言使用已经编写好的本地库文件

4. 工作原理是将这些语言的源代码或源代码编译后的中间格式，通过解释器转换为能被 Graal VM 接受的中间表示。Graal VM 提供 Truffle 工具集快速构建面向一种新语言的解释器。在运行时还能进行即时编译优化，获得比原生编译器更优秀的执行效率。

5. 如果说 HotSpot 有一天真的被取代，Graalvm 希望最大。但是 Java 的软件生态没有丝毫变化。

#### 总结

具体 JVM 的内存结构，其实取决于其实现，不同厂商的 JVM，或者同一厂商发布的不同版本，都有可能存在一定差异。主要以 Oracle HotSpot VM 为默认虚拟机。

## 二.类加载子系统

[参考笔记(https://github.com/youthlql/JavaYouth/blob/main/docs/Java/JVM/JVM%E7%B3%BB%E5%88%97-%E7%AC%AC2%E7%AB%A0-%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%AD%90%E7%B3%BB%E7%BB%9F.md)](https://github.com/youthlql/JavaYouth/blob/main/docs/Java/JVM/JVM%E7%B3%BB%E5%88%97-%E7%AC%AC2%E7%AB%A0-%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%AD%90%E7%B3%BB%E7%BB%9F.md)

### 内存结构概述

TODO 简图
TODO 详细图

### 类加载子系统

#### 类加载器子系统作用

1. 类加载器子系统负责从文件系统或者网络中加载 Class 文件，class 文件在文件开头有特定的文件标识。
2. ClassLoader 只负责 class 文件的加载，至于它是否可以运行，则由 Execution Engine 决定。
3. **加载的类信息存放于一块称为方法区的内存空间**。除了类的信息外，方法区中还会存放运行时常量池信息，可能还包括字符串字面量和数字常量（这部分常量信息是 Class 文件中常量池部分的内存映射）

#### 类加载的过程

##### Loading

**加载：**

1. 通过一个类的全限定名获取定义此类的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. 在内存中生成一个代表这个类的 java.lang.Class 对象，作为方法区这个类的各种数据的访问入口

**加载 class 文件的方式：**

1. 从本地系统中直接加载
2. 通过网络获取，典型场景：Web Applet
3. 从 zip 压缩包中读取，成为日后 jar、war 格式的基础
4. 运行时计算生成，使用最多的是：动态代理技术
5. 由其他文件生成，典型场景：JSP 应用从专有数据库中提取.class 文件，比较少见
6. 从加密文件中获取，典型的防 Class 文件被反编译的保护措施

##### Linking

链接分为三个子阶段：**验证** -> **准备** -> **解析**

**验证(Verify)**:

1. 目的在于确保 Class 文件的字节流中包含信息符合当前虚拟机要求，保证被加载类的正确性，不会危害虚拟机自身安全
2. 主要包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证。

查看字节码文件，其开头均为 CAFE BABE ，如果出现不合法的字节码文件，那么将会验证不通过。

![image-20210211175611397](jvm-note.assets/image-20210211175611397.png)

**准备(Prepare)**:

1. 为类变量（static 变量）分配内存并且设置该类变量的默认初始值，即零值
2. 这里不包含用 final 修饰的 static，因为 final 在编译的时候就会分配好了默认值，准备阶段会显式初始化
3. 注意：这里不会为**实例变量**分配初始化，**类变量**会分配在方法区中，而实例变量是会随着对象一起分配到 Java 堆中

变量 a 在准备阶段会赋初始值，但不是 1，而是 0，在初始化阶段会被赋值为 1

```java
public class HelloApp {
    private static int a = 1;//prepare：a = 0 ---> initial : a = 1

    public static void main(String[] args) {
        System.out.println(a);
    }
}
```

**解析(Resolve)**:

1. **将常量池内的符号引用转换为直接引用的过程**
2. 事实上，解析操作往往会伴随着 JVM 在执行完初始化之后再执行
3. 符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java 虚拟机规范》的 class 文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄
4. 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中的 CONSTANT Class info、CONSTANT Fieldref info、CONSTANT Methodref info 等

**符号引用**:

- 反编译 class 文件后可以查看符号引用，下面带# 的就是符号引用

![image-20210211180433925](jvm-note.assets/image-20210211180433925.png)

##### Initialization

## command

### javap

显示通过字节码反编译后的 **Java** 代码。

**usage**:

```shell
用法: javap <options> <classes>
其中, 可能的选项包括:
  -? -h --help -help               输出此帮助消息
  -version                         版本信息
  -v  -verbose                     输出附加信息
  -l                               输出行号和本地变量表
  -public                          仅显示公共类和成员
  -protected                       显示受保护的/公共类和成员
  -package                         显示程序包/受保护的/公共类
                                   和成员 (默认)
  -p  -private                     显示所有类和成员
  -c                               对代码进行反汇编
  -s                               输出内部类型签名
  -sysinfo                         显示正在处理的类的
                                   系统信息 (路径, 大小, 日期, MD5 散列)
  -constants                       显示最终常量
  --module <模块>, -m <模块>       指定包含要反汇编的类的模块
  --module-path <路径>             指定查找应用程序模块的位置
  --system <jdk>                   指定查找系统模块的位置
  --class-path <路径>              指定查找用户类文件的位置
  -classpath <路径>                指定查找用户类文件的位置
  -cp <路径>                       指定查找用户类文件的位置
  -bootclasspath <路径>            覆盖引导类文件的位置

GNU 样式的选项可使用 = (而非空白) 来分隔选项名称
及其值。

每个类可由其文件名, URL 或其
全限定类名指定。示例:
   path/to/MyClass.class
   jar:file:///path/to/MyJar.jar!/mypkg/MyClass.class
   java.lang.Object
```

```shell
javap [-v] classFile
```

### jps

```bash
jps
```

show JVM instance run on the system.

### jstat

```bash
jstat [-gc] pid   # show gc status of specificate pid
```

### jinfo

```bash
jinfo -flag [JVM XX parameter] pid   # show the flag of pid
```

### jmap

Export dump file.

## JVM Parameters

### -Xss

Max memory size of JVM Stack. `-Xx:Stack Size`

```bash
-Xss10m
```

### -Xms

Initial heap memory size. `-Xx:Memory Start`

```bash
-Xms600m
```

### -Xmx

Max heap memory size. `-Xx:Memory maX`

```bash
-Xmx600m
```

### -Xmn

Young generation memory size. Priority is higher than `-XX:NewRatio` parameter. `-Xx:Memory of New`

```bash
-Xmn100m
```

### -server

Enable server mode. In this mode, Scalar Replace and Escape Analysis function will start working.

>Selects the Java HotSpot Server VM. The 64-bit version of the JDK supports only the Server VM, so in that case the option is implicit.

### -XX:NewRatio

The old generation and young generation memory size ratio. Default value is 2.

```bash
-XX:NewRatio=2
```

### -XX:SurvivorRatio

The Eden and Survivor generation memory size ratio.Default ratio is 8.
Because of Adaptive Memory Size Policy, the ratio between Eden and Survivor generation is not the default value. You must set `SurvivorRatio` to 8 obviously， then the ratio can take effect.

```bash
-XX:SurvivorRatio=8
```

### -XX:-UseAdaptiveSizePolicy

Disable Adaptive memory size policy.(Not effection, Need set ratio)

```bash
-XX:-UseAdaptiveSizePolicy
```

### -XX:+PrintGCDetails

Print GC Details Infomation after JVM instance stop.

```bash
-XX:+PrintGCDetails
```

### -XX:+PrintGC

Print GC simplified information.

### -XX:MaxTenuringThreshold

The threshold which time to promote the survivor object ot tenuring generation.

```bash
-XX:MaxTenuringThreshold=15
```

### -XX:+DoEscapeAnalysis

Enable escape analysis in HotSpot. Accurately the Scalar Replace function in HotSpot.

### -XX:+EliminateAllocations

Enable Scalar replace function in HotSpot. The function will allocate object which allocate on heap memory originally on Stack Frame, and split the polymer(object) into scalar(local variables).

### -XX:PermSize

Before JDK8, the implementation of `method area` is `Permanent Generation`.
Sets initial permanent generation memory size. Default value is 20.75 MiB.

```java
-XX:PermSize=100m
-XX:PermSize=2g
```

### -XX:MaxPermSize

Before JDK8.
Sets max permanent generation memory size. Default valus is 82 MiB.

```java
-XX:MaxPermSize=100m
-XX:MaxPermSize=1G
```

### -XX:MetaspaceSize

After JDK8, Metaspace replace PermGen.
Sets initial metaspace size. Default value of metaspace is 21 MiB.

```java
-XX:MetaspaceSize=100m
```

### -XX:MaxMetaspaceSize

After JDK8, sets max metaspace size. Default value is -1(all native heap memory).

```java
-XX:MaxMetaspaceSize=100m
-XX:MaxMetaspaceSize=1g
```
