# Resume Project

这是一个简历项目，用于生成和管理简历文档。

## 项目结构

- `document-generator`: 文档生成服务，负责简历文档的生成和管理

## 技术栈

- Java 17
- Spring Boot 3.4.4
- MySQL 8.2.0
- Lombok
- Spring Data JPA
- Spring Web
- Prometheus & Actuator (监控)

## 构建要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.2.0 或更高版本

## 构建说明

1. 克隆项目
```bash
git clone [项目地址]
```

2. 进入项目目录
```bash
cd resume-project
```

3. 构建项目
```bash
mvn clean install
```

## 子模块说明

### document-generator

文档生成服务，提供以下功能：
- 简历文档的生成
- 文档模板管理
- 数据持久化
- 监控指标收集

详细说明请参考 [document-generator/README.md](document-generator/README.md) 