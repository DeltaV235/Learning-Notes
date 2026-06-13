# Vue3 全栈课程笔记 2026

这是一套面向 Java 后端转 Vue3 全栈的学习资料。内容按课程章节组织，每章包含课程文档、示例代码和练习题；生成进度记录在 [PROGRESS.md](/Users/deltav/Developer/01-Learning-Notes/22-vue/vue-tutorial-2026/PROGRESS.md)。

## 学习路线

优先级按面试和真实后台管理系统开发重新整理：

1. 必学：第 2 章核心语法、第 3 章组合式 API、第 4 章组件通信、第 5 章 Vue Router、第 6 章 axios、第 7 章 Pinia、第 8 章 Element Plus、第 10 章 Vue + TypeScript。
2. 可学：第 9 章 TypeScript。Java 开发者需要掌握类型系统思想，但不必一开始追求所有高级类型技巧。
3. 了解：第 1 章 Vue 概述、Vant4、Vuex。Vuex 主要用于阅读旧项目，新项目优先 Pinia。

## 目录结构

```text
.
├── chapters/
│   ├── 01-vue-overview/
│   ├── 02-core-syntax/
│   ├── 03-composition-api/
│   ├── 04-components/
│   ├── 05-router/
│   ├── 06-data-request/
│   ├── 07-state-management/
│   ├── 08-ui-framework/
│   ├── 09-typescript/
│   └── 10-vue-ts/
├── projects/
│   └── element-plus-admin/
├── src/
├── COURSE_PLAN.md
├── OFFICIAL_REFERENCES.md
└── PROGRESS.md
```

## 本批已生成

- 第 1 章：Vue.js 概述，偏快速理解和环境搭建。
- 第 2 章：核心语法，作为重点章节详细展开。
- 第 3 章：组合式 API，作为 Vue3 面试和项目核心详细展开。
- 第 4 到第 10 章：已建立章节入口和后续生成计划。
- Element Plus 后台管理系统项目：已建立项目规划入口。

## 如何使用

先读 [COURSE_PLAN.md](/Users/deltav/Developer/01-Learning-Notes/22-vue/vue-tutorial-2026/COURSE_PLAN.md)，再按 `PROGRESS.md` 的批次顺序学习。

每章建议遵循这个顺序：

1. 阅读 `README.md`，理解概念和面试重点。
2. 打开 `examples/` 目录，逐个运行或阅读示例。
3. 完成 `exercises.md` 里的练习。
4. 在 `PROGRESS.md` 标记学习状态，方便下次继续。

## 运行课程索引页

本项目保留一个极简 Vue 索引页，便于后续把示例挂到浏览器中预览。

```sh
npm install
npm run dev
```

当前课程资料本身主要是 Markdown 和示例源码，不依赖开发服务器阅读。

