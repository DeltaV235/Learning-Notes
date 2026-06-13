# 第 4 章 组件详解

状态：目录已建立，待 Batch 02 生成正文、示例和练习。

## 本章定位

组件是 Vue 项目的组织单位。本章会重点服务后台管理系统开发：父子通信、跨层通信、插槽、组件缓存、弹窗传送、自定义 hook 和插件封装。

## 计划内容

1. 脚手架项目的分析。
2. ESLint 与 Prettier。
3. 组件样式控制：全局样式、局部作用域、深度样式。
4. 组件通信之 `props`。
5. 组件通信之 `ref` 与 `defineExpose`。
6. 组件通信之 `emits` 与 `defineEmits`。
7. 组件通信之 `attrs`。
8. 组件通信之 `provide` 与 `inject`。
9. 组件通信之 mitt。
10. 组件通信之 slot：默认插槽、具名插槽、插槽默认值、作用域插槽。
11. 内置组件 `Component`、`KeepAlive`、`Teleport`。
12. 自定义 directive。
13. 自定义 hook。
14. plugin。

## 面试重点

- `props` 和 `emits` 为什么构成单向数据流？
- `ref` 获取子组件实例时，为什么要配合 `defineExpose`？
- `attrs` 适合解决什么问题？
- `provide/inject` 和 Pinia 的边界是什么？
- 作用域插槽解决了什么复用问题？
- `KeepAlive` 如何影响生命周期？

