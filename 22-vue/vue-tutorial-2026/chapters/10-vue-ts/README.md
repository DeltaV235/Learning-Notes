# 第 10 章 Vue3 对 TypeScript 的支撑

状态：目录已建立，待 Batch 06 生成正文、示例和练习。

## 本章定位

本章把 TypeScript 放回 Vue 项目中，解决真实项目里的组件 props、emits、router、Pinia、axios 类型问题。

## 计划内容

1. 创建支持 TypeScript 的 Vue 项目。
2. `reactive`、`ref`、`computed` 对 TypeScript 的支撑。
3. `props` 与 `emits` 类型。
4. Vue Router 类型。
5. Pinia 类型。
6. axios 请求和响应类型。
7. 综合应用案例：计划列表、用户搜索。

## 面试重点

- `ref<User | null>(null)` 为什么常见？
- `defineProps` 和 `defineEmits` 如何写类型？
- axios 响应泛型如何封装？
- Pinia store 如何保持类型推断？

