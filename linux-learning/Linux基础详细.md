# Linux基础

## Linux介绍

### Linux是什么

Linux是一种自由和开放源码的类UNIX操作系统。该操作系统的内核由林纳斯·托瓦兹在1991年10月5日首次发布，在加上用户空间的应用程序之后，成为Linux操作系统。
Linux严格来说是单指操作系统的内核，因操作系统中包含了许多用户图形接口和其他实用工具。如今Linux常用来指基于Linux的完整操作系统，内核则改以Linux内核称之。

### UNIX

UNIX操作系统（英语：UNIX），是美国AT&T公司贝尔实验室于1969年完成的操作系统。

### GNU

### Linus

林纳斯·班奈狄克·托瓦兹（Linus Benedict Torvalds，1969年12月28日－），生于芬兰赫尔辛基市，拥有美国国籍，Linux内核的最早作者，随后发起了这个开源项目，担任Linux内核的首要架构师与项目协调者，是当今世界最著名的电脑程序员、黑客之一。他还发起了开源项目Git，并为主要的开发者。

### Linux的特色

- 自由与开放的使用与学习环境
由于Linux是基于GPL的授权之下，因此它是自由软件，任何人都可以自由使用或是修改其中的源码。
- 配置需求低廉
- 内核功能强大且稳定

### Linux的优缺点

- 优点
  - 稳定的系统
  - 开源
  - 安全性
  - 多任务、多用户
  - 网络功能强大
  - 支持多种不同平台的处理器
  - 用户与用户组规划
  - 适合需要小内核的嵌入式系统

- 缺点
  - 游戏支持度不足
  - 专业软件支持度不足

### Linux的应用场景

- 服务器Linux系统应用领域
- 嵌入式Linux
- 个人桌面系统

### Linux发行版

Linux 发行版（英语：Linux distribution，也被叫做GNU/Linux 发行版），为一般用户预先集成好的Linux操作系统及各种应用软件。一般用户不需要重新编译，在直接安装之后，只需要小幅度更改设置就可以使用，通常以软件包管理系统来进行应用软件的管理。
Linux发行版通常包含了包括桌面环境、办公包、媒体播放器、数据库等应用软件。这些操作系统通常由Linux内核、以及来自GNU计划的大量的函数库，和基于X Window的图形界面。

#### Debian系

- Ubuntu
- Debian

#### Red Hat系

- RHEL
- CentOS

## 基础知识补充

### 硬盘分区和文件系统



### 终端和Shell

## Linux在PC上的安装

### VM

- 系统镜像下载

	- 镜像版本选择

		- CentOS

		  If you want to use CentOS on your desktop/pc select DVD or everything. Everything gives you bunch of options to select from.
		  Minimal packages are intended for system with less RAM and that only requires Linux core functionality like in Raspberry Pi.

			- DVD

			  标准安装盘。最小化的环境以及工具包、基础开发包和GUI。为了适应DVD，总容量控制在3.7GB

			- Everything

			  包含了所有DVD中的内容，对完整版安装盘的软件进行补充，集成所有软件。

			- Minimal

			  它包含了Linux运行所需的最小环境，不包含GUI

			- NetInstall

			  网络安装镜像

			- LiveKDE

			  KDE桌面版

			- LiveGNOME

			  GNOME桌面版

- VM配置

	- CPU
	- 内存
	- 硬盘
	- 网络

- 系统安装

	- 系统分区基础
	- 文件系统基础
	- Linux目录分类基础
	- 普通用户与管理员用户

- 优缺点

	- 优点

		- 不影响宿主机的系统环境
		- 能够同时运行多台虚拟机，能够进行模拟集群

	- 缺点

		- 系统资源占用大
		- CPU需要支持虚拟化
		- 执行效率低
		- 木得灵魂

### 物理机系统 / 多系统

- 优缺点

	- 优点

		- 执行效率高
		- 强制接触Linux，非常大的折腾空间

	- 缺点

		- 折腾
		- 吃灰
		- Windows专属软件无法运行，需要请求替代产品

### Windows Subsystem Linux

- 优点

	- 轻量快速
	- 与宿主机相融合

- 缺点

	- WSL1代IO效率低下
	- WSL1无法安装Docker

## SSH工具

### SecureCRT

### XShell

### Putty

### WSL + Windows Terminal

## 命令行基础

### 命令行结构

### Linux基础命令

- cd

  切换工作目录

- 文件的复制移动

	- cp
	- mv

- 普通文件的增删改查

	- 查看文件

		- less
		- more
		- cat
		- tail

	- 文件的创建

		- touch
		- vim

	- 文件的删除

		- rm

	- 文件的修改

		- 文件内容修改
		- 文件权限修改
		- 文件所有者修改

- 目录文件的增删改查

	- 查看目录文件中的内容

		- ls

	- 目录的创建

		- mkdir

	- 目录的删除

		- rmdir

	- 目录的修改

		- mv

- 常用快捷键

## 文本编辑器

### nano

### VIM

- 三种模式

	- Normal

		- 使用场景

		  光标的快速移动

	- Insert

		- 使用场景

		  文本的写入

	- Command

		- 使用场景

		  对文件进行批量操作，控制VIM

## 文件权限与目录配置

### 用户、用户组

- 用户切换

	- root

### Linux文件权限

- 文件属性
- 文件属性的修改

	- chgrp
	- chown
	- chmod

- 目录权限与文件权限的异同
- 文件种类与扩展名

	- 文件种类

		- 普通文件
		- 纯文本文件
		- 二进制文件
		- 数据格式文件
		- 目录文件
		- 连接文件
		- 设备文件
		- 套接字
		- 管道

	- 文件扩展名

### Linux目录配置

- 绝对、相对路径
- FHS

## 文件与目录管理

### 目录与路径

### 文件与目录管理

### 文件内容查阅

### 文件与目录的默认权限与隐藏权限

### 命令与文件的查询

### 权限与命令之间的关系

## 磁盘与文件系统管理

### 认识EXT4文件系统

### 文件系统简单操作

### 硬盘的分区、校验、挂载

### 开机自动挂载

### 内存交换空间SWAP

## 文件与文件系统的压缩与打包

### 常见压缩命令

### 打包命令

### dd

## shell / bash

### 环境变量

### 别名、历史

### 数据重定向

### pipe

## 正则表达式与文件格式化处理

## shell脚本

### 什么是Shell Script

### Hello World

### 条件判断

### 循环

### 调试

## 包管理

### rpm

### yum

*XMind - Trial Version*