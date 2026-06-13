# 第 3 章练习

## ref 练习

1. 使用 `ref` 创建 `keyword`、`loading`、`count`。
2. 写一个 `increment` 函数修改 `count`。
3. 在模板中展示 `count`，在 JS 中打印 `count.value`。

## reactive 练习

1. 使用 `reactive` 创建查询表单：`keyword`、`role`、`enabledOnly`。
2. 使用 `Object.assign` 实现重置表单。
3. 对比直接替换 reactive 对象和 `Object.assign` 的差异。

## toRefs 练习

1. 创建一个 `useSearchForm` 函数，内部使用 `reactive` 保存状态。
2. 返回 `toRefs(state)` 和一个 `reset` 函数。
3. 在组件中解构返回值，并确认模板仍然响应式更新。

## computed 练习

1. 创建用户数组 `users`。
2. 根据 `keyword` 和 `role` 计算 `visibleUsers`。
3. 计算启用用户数量。
4. 创建可写 computed：`fullName`，拆分为 `firstName` 和 `lastName`。

## watch 练习

1. 监听 `keyword`，变化时打印新旧值。
2. 监听 `() => form.role`。
3. 监听 `[keyword, page]`，模拟请求用户列表。
4. 加上 `immediate: true`，观察首次执行。

## 生命周期练习

1. 在 `onMounted` 中模拟请求用户列表。
2. 创建一个定时器，每秒更新一次当前时间。
3. 在 `onBeforeUnmount` 中清理定时器。

## 综合练习

用 Composition API 重写第 2 章的用户管理小页面：

- 所有基础状态用 `ref` 或 `reactive`。
- 列表过滤和统计使用 `computed`。
- 搜索条件变化使用 `watch`。
- 初始化数据使用 `onMounted`。
- 抽取 `useCounter` 或 `useUserSearch` hook。

