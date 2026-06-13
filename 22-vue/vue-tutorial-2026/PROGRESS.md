# 学习与生成进度

更新时间：2026-06-13

## 生成进度

| 章节 | 标题 | 生成状态 | 学习状态 | 备注 |
| --- | --- | --- | --- | --- |
| 第 1 章 | Vue.js 概述 | 已生成 | 未开始 | 环境、Vue3 变化、学习路线 |
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

## Batch 01 记录

生成范围：

- `chapters/01-vue-overview`
- `chapters/02-core-syntax`
- `chapters/03-composition-api`
- `src/` 课程索引页
- `projects/element-plus-admin/README.md`

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
- 当前目录没有 `node_modules`，因此未运行 `npm run build`。需要预览课程索引页时，先执行 `npm install`，再执行 `npm run dev`。
