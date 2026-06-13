# 课程生成计划

## 目标

面向 Java 后端开发者，生成一套可用于 Vue3 全栈面试和后台管理系统实战的课程资料。课程不机械复刻原书目录，而是保留章节顺序，同时把新项目常用技术栈调整为：

- Vue3 + Vite + SFC
- Composition API + `<script setup>`
- Vue Router
- Pinia
- axios
- Element Plus
- TypeScript

## 批次安排

| 批次 | 范围 | 状态 | 重点 |
| --- | --- | --- | --- |
| Batch 01 | 第 1 章到第 3 章 | 已生成 | Vue 入门、核心语法、组合式 API |
| Batch 02 | 第 4 章 | 待生成 | 组件通信、插槽、内置组件、封装能力 |
| Batch 03 | 第 5 章 | 待生成 | Vue Router、动态路由、守卫、缓存和懒加载 |
| Batch 04 | 第 6 章到第 7 章 | 待生成 | axios、接口封装、Pinia、持久化 |
| Batch 05 | 第 8 章 | 待生成 | Element Plus 后台管理页面，Vant4 只做了解 |
| Batch 06 | 第 9 章到第 10 章 | 待生成 | TypeScript 基础、Vue3 TS 工程实践 |
| Batch 07 | 综合项目 | 待生成 | Element Plus 后台管理系统项目 |

## 内容约定

每个章节目录包含：

- `README.md`：课程正文。
- `examples/`：示例代码。
- `exercises.md`：练习题。

重点章节要求：

- 概念先解释“为什么要用”。
- 示例要接近后台管理系统业务。
- 对 Java 后端开发者补充类比，例如 DTO、Controller、Service、Store、Router 的职责边界。
- 面试点要明确标注，避免只写 API 清单。

## 后续继续生成提示词

后续可以直接说：

```text
继续生成 Batch 02：第4章组件详解，保持当前目录结构和 PROGRESS.md 记录方式。
```

也可以指定更小范围：

```text
继续生成第4章 4.4 到 4.10，重点写组件通信、插槽和可运行示例。
```

