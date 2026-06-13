# 第 1 章 Vue.js 概述

本章目标不是背 Vue 历史，而是建立正确的技术定位：Vue 是什么、为什么适合后台管理系统、Java 后端开发者应该如何切入。

## 1.1 六何分析 Vue

### Vue 是什么

Vue 是一个用于构建用户界面的 JavaScript 框架。它基于标准 HTML、CSS、JavaScript，提供声明式渲染、响应式状态和组件化开发模型。

从 Java 后端视角看：

- HTML 模板类似 View 层。
- 组件类似可复用的 UI 模块。
- 响应式数据类似“状态变化自动刷新界面”的机制。
- Vue Router 类似前端自己的页面路由。
- Pinia 类似前端全局状态容器，不等同于数据库或后端缓存。

### Vue 是由谁开发的

Vue 最初由 Evan You 开发，后来发展为由核心团队和社区共同维护的开源项目。现在 Vue Router、Pinia 等官方库构成了 Vue 生态中常用的工程能力。

### Vue 的发展历程

学习上只需要记住三个阶段：

1. Vue2：Options API 时代，很多旧项目仍在使用。
2. Vue3：重写响应式系统，引入 Composition API，更适合大型项目组织逻辑。
3. 当前新项目：通常使用 Vite、SFC、`<script setup>`、Vue Router、Pinia、TypeScript。

Vue2 已结束官方常规支持，因此新项目应直接选择 Vue3。

### Vue 用在哪些项目的开发中

最常见场景：

- 企业后台管理系统。
- 数据看板。
- 表单密集型业务系统。
- 内容管理系统。
- 中后台运营工具。
- 嵌入到传统后端页面中的交互模块。

对于 Java 全栈岗位，最常考的是后台管理系统：登录、菜单、权限、用户列表、表单、表格、详情页、接口请求、路由守卫、状态管理。

### 为什么要选择 Vue

对 Java 后端开发者比较友好的点：

- 模板语法接近 HTML，上手成本低。
- 单文件组件把模板、逻辑、样式放在一个 `.vue` 文件中，便于定位。
- 官方生态明确：Router 负责路由，Pinia 负责状态，Vite 负责工程构建。
- Element Plus 等 UI 框架非常适合后台系统。
- Composition API 更接近函数式组织方式，利于抽取业务逻辑。

### 如何学习 Vue

建议顺序：

1. 先学模板、指令、事件、表单、列表。
2. 再学响应式：`ref`、`reactive`、`computed`、`watch`。
3. 接着学组件通信和插槽。
4. 再学 Vue Router、axios、Pinia。
5. 最后学 TypeScript 和后台管理系统项目。

不要一开始就追求源码原理。先能写页面、调接口、处理状态，再补原理。

## 1.2 Vue3 的新特性

### 内在核心的变化

Vue3 最重要的底层变化是响应式系统基于 `Proxy`，比 Vue2 的 `Object.defineProperty` 更适合处理对象新增属性、删除属性、数组变化等场景。

项目开发中你需要掌握的是：

- `ref` 用于基本类型或需要 `.value` 的响应式值。
- `reactive` 用于对象、数组等复杂结构。
- `computed` 用于派生状态。
- `watch` 用于监听状态变化并执行副作用。

### 渲染引擎的改进

Vue3 的编译器和运行时做了更多优化。学习阶段不需要背全部细节，但要知道：

- 模板最终会被编译成渲染函数。
- 响应式数据变化后，Vue 会尽量只更新受影响的 DOM。
- `key` 对列表更新很重要，不能随便用数组下标替代业务 ID。

### 新的内置组件

后续重点会用到：

- `Transition`：单元素过渡动画。
- `TransitionGroup`：列表动画。
- `KeepAlive`：缓存组件实例，常用于缓存路由页面。
- `Teleport`：把弹窗等内容渲染到 DOM 的其他位置。
- `Suspense`：处理异步组件和异步依赖。

### API 的修改

新项目常见写法：

```vue
<script setup>
import { ref } from 'vue'

const count = ref(0)

function increment() {
  count.value++
}
</script>

<template>
  <button @click="increment">点击 {{ count }}</button>
</template>
```

Options API 仍然可用，但全套新项目建议重点学习 Composition API。

## 1.3 Vue3 的运行环境

### 运行环境搭建

推荐环境：

- Node.js：`^20.19.0 || >=22.12.0`。
- 包管理器：npm、pnpm、yarn 或 bun 均可，本课程默认 npm。
- 构建工具：Vite。
- 编辑器：VS Code + Vue - Official 扩展。

创建项目：

```sh
npm create vue@latest
cd your-project
npm install
npm run dev
```

本课程已经按 Vite 项目结构保留了一个轻量入口，后续示例会逐步挂载进去。

### Vue 开发者调试工具

建议安装：

- Chrome/Edge：Vue.js devtools。
- VS Code：Vue - Official。

调试时重点观察：

- 组件树。
- props 和 emits。
- Pinia store 状态。
- 路由切换。
- 响应式数据是否按预期更新。

## 本章面试点

1. Vue 的核心特性是什么？
2. Vue3 和 Vue2 在响应式系统上有什么区别？
3. Composition API 相比 Options API 解决了什么问题？
4. 为什么新项目推荐 Pinia 而不是 Vuex？
5. Vite 在 Vue 项目中承担什么职责？

