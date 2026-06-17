# 学习与生成进度

更新时间：2026-06-14

## 生成进度

| 章节 | 标题 | 生成状态 | 学习状态 | 备注 |
| --- | --- | --- | --- | --- |
| 第 1 章 | Vue.js 概述 | 已生成 | 练习已通过 | 环境、Vue3 变化、学习路线 |
| 第 2 章 | 核心语法 | 已生成 | 未开始 | 重点，已包含多个示例 |
| 第 3 章 | Vue3 新语法 | 已生成 | 未开始 | 重点，组合式 API |
| 第 4 章 | 组件详解 | 目录已建立 | 未开始 | 下一批生成 |
| 第 5 章 | Vue 路由 | 目录已建立 | 未开始 | 后台系统核心 |
| 第 6 章 | 数据请求 | 目录已建立 | 未开始 | axios 和接口封装 |
| 第 7 章 | 状态管理 | 目录已建立 | 未开始 | Pinia 为主，Vuex 为旧项目阅读 |
| 第 8 章 | UI 框架 | 目录已建立 | 未开始 | Element Plus 为主 |
| 第 9 章 | TypeScript | 目录已建立 | 未开始 | 可学但建议补齐 |
| 第 10 章 | Vue3 对 TypeScript 的支撑 | 目录已建立 | 未开始 | 面试和项目重点 |
| 综合项目 | Element Plus 后台管理系统 | 规划已建立 | 未开始 | 最终实战项目 |

## 展示方式

- 课件和练习不再使用章节 Markdown 文件承载。
- 课件、内嵌示例、练习目标和答疑标注数据统一维护在 `src/course/courseData.js`。
- 页面入口是 `src/App.vue`，包含“课件”“练习”两个视图；答疑内容直接生成在“课件”页内。
- 每章练习都有 `submissions`、`reviews`、`attachments` 目录，后续用于提交答案、保存批改和关联附件。

## Batch 01 记录

生成范围：

- `chapters/01-vue-overview`
- `chapters/02-core-syntax`
- `chapters/03-composition-api`
- `src/` 课程索引页
- Element Plus 后台管理系统项目规划

本批重点：

- 第 2 章详细覆盖模板语法、计算属性、监听、动态样式、条件渲染、列表渲染、事件、表单、生命周期、过渡动画、内置指令。
- 第 3 章详细覆盖 `setup`、`ref`、`reactive`、`toRef`、`toRefs`、只读 API、浅层响应式、`toRaw`、`markRaw`、`computed`、`watch`、生命周期钩子。
- 示例代码尽量贴近后台管理系统常见场景，如用户列表、筛选排序、表单、状态派生。

## 下次建议

下一批生成第 4 章组件详解，优先顺序：

1. 组件定义与样式隔离。
2. `props`、`emits`、`ref`、`defineExpose`。
3. `attrs`、`provide/inject`、`mitt`。
4. 默认插槽、具名插槽、作用域插槽。
5. `KeepAlive`、`Teleport`、自定义 directive、hook、plugin。

## 本次校验记录

- 已用 `npm pkg get name scripts engines` 校验 `package.json` 可被 npm 解析。
- 已用 `node --check` 校验 `vite.config.js`、`src/main.js`、`chapters/03-composition-api/examples/useCounter.js` 的 JavaScript 语法。
- 当时目录没有 `node_modules`，因此未运行 `npm run build`。当前如果需要预览课程工作台，可执行 `npm run dev`。

## 2026-06-14 调整记录

- 已把课件和练习改为 Vue 工作台呈现。
- 已删除 `chapters/*/README.md` 和 `chapters/*/exercises.md`。
- 已新增 `src/course/courseData.js` 作为课程数据源。
- 已新增 `exercises/*/submissions`、`exercises/*/reviews`、`exercises/*/attachments`，用于后续提交和批改。
- 后续答疑会追加到对应章节的 `qaNotes`，并在“课件”页内展示。

## 2026-06-14 二次调整记录

- 已将示例代码直接并入 Vue 课件数据，删除 `chapters/*/examples` 下的独立示例文件。
- 已移除“批改结构”页签，练习页只提示提交目录、starter 类型和目标文件。
- 已把内部批改规则迁移到 `exercises/gradingIndex.js`，由我批改时读取，不直接展示给用户。
- 第 1 章 submissions 保持空目录；第 2、3 章已提供 `submissions/starter-vue-app` 基础 Vue 项目。
- 后续章节生成时，需要同步补齐对应章节的 starter 项目和目标练习文件。

## 2026-06-14 课件扩写记录

- 已移除独立“答疑标注”页签，答疑记录直接显示在“课件”页的“本章答疑补充”区域。
- 已为第 1 到第 3 章补充教材式结构：本章学习目标、章节导读、操作步骤、常见错误、面试提示。
- 后续继续生成章节时，需要沿用相同课件结构，而不是只写简单要点列表。

## 2026-06-17 学习记录

- 第 1 章练习已提交并批改通过。
- 本次提交文件：`exercises/01-vue-overview/submissions/2026-06-17-01-basic.json`。
- 本次批改文件：`exercises/01-vue-overview/reviews/2026-06-17-01-basic-review.json`。
- 需要修正的表达：Vue 自动更新页面的前提是响应式数据变化，不是任意普通变量变化。
