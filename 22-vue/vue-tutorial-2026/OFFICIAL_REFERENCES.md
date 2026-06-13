# 官方参考与更新说明

这些资料用于校准课程内容，避免把旧写法当成新项目默认方案。

## 官方参考

- Vue 官方文档：[Introduction](https://vuejs.org/guide/introduction.html)
- Vue 快速开始：[Quick Start](https://vuejs.org/guide/quick-start.html)
- Vue `<script setup>`：[SFC Script Setup](https://vuejs.org/api/sfc-script-setup.html)
- Vue TypeScript：[Using Vue with TypeScript](https://vuejs.org/guide/typescript/overview.html)
- Vue 状态管理说明：[State Management](https://vuejs.org/guide/scaling-up/state-management.html)
- Vue Router 官方文档：[Getting Started](https://router.vuejs.org/guide/)
- Pinia 官方文档：[Introduction](https://pinia.vuejs.org/introduction.html)
- Element Plus 官方文档：[Installation](https://element-plus.org/en-US/guide/installation.html)
- Vite 官方文档：[Getting Started](https://vite.dev/guide/)

## 和原大纲相比的调整

1. 新项目默认使用 `create-vue`、Vite、SFC、Composition API 和 `<script setup>`。
2. Vuex 保留为旧项目阅读内容，新项目状态管理优先 Pinia。
3. Element Plus 作为后台管理系统重点，Vant4 只作为移动端 UI 框架了解。
4. TypeScript 不再只作为单独语法章节，后续会在 Vue Router、Pinia、axios、组件通信中穿插使用。
5. Node.js 版本按 Vue/Vite 当前文档要求，建议使用 `^20.19.0 || >=22.12.0`。

## 课程内的写法选择

- 基础章节会先用 Options API 解释 `data`、`methods`、`computed`、`watch`，因为这对应原大纲，也便于理解旧项目。
- Vue3 项目实践从第 3 章开始切换到 Composition API + `<script setup>`。
- 后续综合项目只使用 Composition API + Pinia + Vue Router + Element Plus + TypeScript。

