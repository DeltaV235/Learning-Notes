# Vue3 全栈课程笔记 2026

这是一套面向 Java 后端转 Vue3 全栈的学习资料。课件、示例和练习题由 Vue 页面渲染，不再使用章节 Markdown 作为学习内容载体；生成进度记录在 [PROGRESS.md](/Users/deltav/Developer/01-Learning-Notes/22-vue/vue-tutorial-2026/PROGRESS.md)。

## 学习路线

优先级按面试和真实后台管理系统开发重新整理：

1. 必学：第 2 章核心语法、第 3 章组合式 API、第 4 章组件通信、第 5 章 Vue Router、第 6 章 axios、第 7 章 Pinia、第 8 章 Element Plus、第 10 章 Vue + TypeScript。
2. 可学：第 9 章 TypeScript。Java 开发者需要掌握类型系统思想，但不必一开始追求所有高级类型技巧。
3. 了解：第 1 章 Vue 概述、Vant4、Vuex。Vuex 主要用于阅读旧项目，新项目优先 Pinia。

## 目录结构

```text
.
├── exercises/
│   └── */submissions, reviews, attachments
├── src/
│   ├── course/
│   ├── App.vue
│   └── style.css
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

1. 运行课程工作台，进入对应章节的“课件”页签，课件中直接包含示例代码。
2. 打开对应章节的“练习”页签，按目标文件完成指定练习。
3. 从第 2 章开始，`submissions/starter-vue-app` 提供基础 Vue 项目，不需要重新创建项目。
4. 把答案交给我后，我会读取 `submissions` 并批改到 `reviews`。
5. 如果你提问知识点，我会把问题和解答追加到对应章节“课件”页内的“本章答疑补充”。

## 运行课程索引页

本项目保留一个极简 Vue 索引页，便于后续把示例挂到浏览器中预览。

```sh
npm install
npm run dev
```

当前课程资料主要通过 Vue 页面呈现；第 2、3 章的练习 starter 项目位于对应章节的 `submissions/starter-vue-app`。
