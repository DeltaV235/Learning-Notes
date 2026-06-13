# 第 2 章 核心语法

本章是必学章节。目标不是背指令，而是能独立完成后台管理系统中最常见的页面：筛选表单、数据列表、状态展示、事件处理、条件渲染和动画反馈。

## 2.1 模板语法

### 插值语法

插值语法用于把 JavaScript 状态显示到模板中：

```vue
<template>
  <p>用户名：{{ user.name }}</p>
  <p>登录次数：{{ loginCount + 1 }}</p>
</template>
```

注意点：

- 插值表达式可以写简单表达式，不建议写复杂业务逻辑。
- 模板里不能写语句，例如 `if`、`for`、`const`。
- 复杂格式化应该放到 computed 或方法中。

### 指令语法

Vue 指令以 `v-` 开头：

```vue
<a v-bind:href="profileUrl">用户详情</a>
<button v-on:click="saveUser">保存</button>
```

常用简写：

```vue
<a :href="profileUrl">用户详情</a>
<button @click="saveUser">保存</button>
```

面试时要能说明：

- `:` 是 `v-bind` 的简写，用于绑定动态属性。
- `@` 是 `v-on` 的简写，用于绑定事件监听。
- 指令的值一般是 JavaScript 表达式。

### data 和 methods 配置项

Options API 写法：

```vue
<script>
export default {
  data() {
    return {
      keyword: '',
      users: []
    }
  },
  methods: {
    search() {
      console.log(this.keyword)
    }
  }
}
</script>
```

关键点：

- `data` 必须是函数，避免组件复用时共享同一份状态。
- `methods` 里通过 `this` 访问组件实例上的数据。
- Vue3 仍支持 Options API，但新项目会更多使用 `<script setup>`。

## 2.2 计算属性

### 计算属性的基本使用

计算属性用于从已有状态推导出新状态：

```vue
<script>
export default {
  data() {
    return {
      firstName: 'Ada',
      lastName: 'Lovelace'
    }
  },
  computed: {
    fullName() {
      return `${this.firstName} ${this.lastName}`
    }
  }
}
</script>
```

后台系统常见场景：

- 根据用户状态码显示中文状态。
- 根据订单金额计算总价。
- 根据筛选条件计算展示列表。
- 根据权限数组计算是否显示按钮。

### 计算属性和 method 方法

区别：

- computed 有缓存，依赖不变就不会重新计算。
- methods 每次模板重新渲染都会执行。
- computed 适合“值”，methods 适合“动作”。

判断标准：

```text
需要展示一个由状态推导出来的结果 -> computed
用户点击后执行保存、删除、跳转 -> methods
```

### 计算属性的 setter

计算属性默认只有 getter，也可以写 setter：

```js
computed: {
  fullName: {
    get() {
      return `${this.firstName} ${this.lastName}`
    },
    set(value) {
      const [firstName, lastName] = value.split(' ')
      this.firstName = firstName
      this.lastName = lastName
    }
  }
}
```

setter 用得不多，常见于双向绑定一个“组合字段”。

## 2.3 监听

### 监听的基本使用

`watch` 用于在数据变化后执行副作用：

```js
watch: {
  keyword(newValue, oldValue) {
    console.log('搜索条件变化', newValue, oldValue)
  }
}
```

适合场景：

- 搜索条件变化后重新请求接口。
- 表单字段变化后清空错误提示。
- 路由参数变化后重新加载详情。
- 状态变化后写入本地缓存。

不适合场景：

- 只是为了显示派生值，这种应该用 computed。

### 即时回调与深度监听

即时回调：

```js
watch: {
  keyword: {
    immediate: true,
    handler(value) {
      this.fetchUsers(value)
    }
  }
}
```

深度监听：

```js
watch: {
  form: {
    deep: true,
    handler(value) {
      console.log('表单变化', value)
    }
  }
}
```

注意：

- `deep: true` 成本更高，不要滥用。
- 监听接口请求要考虑防抖，避免输入一个字请求一次。

## 2.4 绑定动态样式

### class 绑定

对象写法：

```vue
<span :class="{ active: user.enabled, danger: user.locked }">
  {{ user.name }}
</span>
```

数组写法：

```vue
<span :class="['tag', user.enabled ? 'tag-success' : 'tag-muted']">
  {{ user.name }}
</span>
```

适合做状态标签、菜单高亮、按钮禁用样式。

### style 绑定

```vue
<div :style="{ width: progress + '%', backgroundColor: color }"></div>
```

项目中更推荐用 class 控制大部分样式，style 适合绑定动态数值，比如宽度、坐标、主题色。

## 2.5 条件渲染

### v-if 相关指令

```vue
<section v-if="loading">加载中</section>
<section v-else-if="error">加载失败</section>
<section v-else>展示数据</section>
```

`v-if` 会真正创建或销毁 DOM。

### v-show 指令

```vue
<aside v-show="panelVisible">筛选面板</aside>
```

`v-show` 只是切换 `display`。

### 比较 v-if 和 v-show

| 场景 | 推荐 |
| --- | --- |
| 条件很少切换 | `v-if` |
| 频繁切换显示隐藏 | `v-show` |
| 权限控制，不允许渲染 | `v-if` |
| Tab 面板、筛选区域折叠 | `v-show` |

面试回答：`v-if` 有更高切换成本，`v-show` 有更高初始渲染成本。

## 2.6 列表渲染

### 列表的动态渲染

```vue
<li v-for="user in users" :key="user.id">
  {{ user.name }} - {{ user.role }}
</li>
```

`key` 必须稳定且唯一，后台业务优先用数据库 ID，不建议用数组下标。

### 列表的增、删、改

```js
this.users.push(newUser)
this.users = this.users.filter(user => user.id !== id)
this.users = this.users.map(user =>
  user.id === id ? { ...user, enabled: !user.enabled } : user
)
```

Vue3 能追踪数组变化，但为了代码可读性和不可变思维，更新列表时常用 `filter`、`map`。

### 列表的过滤

过滤展示列表应该优先用 computed：

```js
computed: {
  filteredUsers() {
    return this.users.filter(user => user.name.includes(this.keyword))
  }
}
```

### 列表的排序

不要直接在 computed 里修改原数组：

```js
computed: {
  sortedUsers() {
    return [...this.users].sort((a, b) => b.score - a.score)
  }
}
```

## 2.7 事件处理

### 绑定事件监听

```vue
<button @click="save">保存</button>
<input @input="handleInput">
```

传参：

```vue
<button @click="removeUser(user.id)">删除</button>
```

如果既要传业务参数又要拿原生事件：

```vue
<button @click="removeUser(user.id, $event)">删除</button>
```

### 事件修饰符

常用：

```vue
<form @submit.prevent="submitForm"></form>
<button @click.stop="openMenu">打开菜单</button>
<button @click.once="init">只执行一次</button>
```

解释：

- `.prevent` 阻止默认行为。
- `.stop` 阻止事件冒泡。
- `.once` 只触发一次。

### 按键修饰符

```vue
<input @keyup.enter="search">
<input @keyup.esc="clear">
```

适合搜索框、弹窗关闭、快捷操作。

## 2.8 收集表单数据

### 使用 v-model 指令

```vue
<input v-model="form.username">
<select v-model="form.role">
  <option value="admin">管理员</option>
  <option value="user">普通用户</option>
</select>
<input type="checkbox" v-model="form.enabled">
```

`v-model` 是表单控件双向绑定的语法糖。

后台表单注意点：

- 表单初始值要完整，避免 `undefined`。
- 提交前要做前端校验。
- 接口 DTO 字段名要和后端约定清楚。
- 编辑表单要区分“原始数据”和“当前表单数据”。

### 相关指令修饰符

```vue
<input v-model.trim="form.username">
<input v-model.number="form.age">
<input v-model.lazy="form.remark">
```

- `.trim` 去掉首尾空格。
- `.number` 转为数字。
- `.lazy` 在 change 事件后同步，而不是 input 时同步。

## 2.9 Vue 实例的生命周期

### 生命周期流程

Options API 常用钩子：

```text
beforeCreate -> created -> beforeMount -> mounted -> beforeUpdate -> updated -> beforeUnmount -> unmounted
```

### Vue 实例的生命周期分析

常用理解：

- `created`：数据已初始化，但 DOM 还没挂载。
- `mounted`：DOM 已挂载，适合访问 DOM 或发起首屏请求。
- `updated`：响应式更新导致 DOM 更新后触发。
- `beforeUnmount`：组件销毁前，适合清理定时器、事件监听。
- `unmounted`：组件销毁后。

### 常用生命周期钩子函数

后台系统里最常用：

```js
mounted() {
  this.fetchUsers()
},
beforeUnmount() {
  clearInterval(this.timer)
}
```

进入 Composition API 后对应：

```js
onMounted(() => {})
onBeforeUnmount(() => {})
```

## 2.10 过渡与动画

### 基于 CSS 的过渡动画效果

```vue
<Transition name="fade">
  <p v-if="visible">保存成功</p>
</Transition>
```

```css
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
```

### 基于 CSS 的逐帧动画效果

```css
@keyframes pulse {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.04);
  }
  100% {
    transform: scale(1);
  }
}
```

### 基于第三方动画库的 CSS 动画效果

可以配合 Animate.css，但后台系统要克制使用。动效主要服务于反馈，不要影响操作效率。

### 基于 JavaScript 的动画效果

Vue 的 `Transition` 支持 JS 钩子。业务项目里除非需要复杂动画，否则优先 CSS。

### 多元素分组动画效果

列表增删改时使用 `TransitionGroup`：

```vue
<TransitionGroup name="list" tag="ul">
  <li v-for="user in users" :key="user.id">{{ user.name }}</li>
</TransitionGroup>
```

## 2.11 内置指令

### v-text 和 v-html 指令

```vue
<p v-text="message"></p>
<article v-html="htmlContent"></article>
```

重点：`v-html` 有 XSS 风险。不要直接渲染用户输入或后端返回的未清洗 HTML。

### v-pre 指令

跳过编译：

```vue
<code v-pre>{{ raw }}</code>
```

适合展示 Vue 模板源码。

### v-once 指令

只渲染一次：

```vue
<h1 v-once>{{ title }}</h1>
```

适合不会变化的静态内容。

### v-memo 指令

按依赖缓存模板片段：

```vue
<div v-for="user in users" :key="user.id" v-memo="[user.enabled]">
  {{ user.name }}
</div>
```

普通项目不需要过早使用。只有在长列表性能优化时再考虑。

### v-cloak 指令

用于 CDN 或无构建场景，避免模板未编译前闪现：

```html
<div id="app" v-cloak>{{ message }}</div>
```

```css
[v-cloak] {
  display: none;
}
```

Vite + SFC 项目里很少需要它。

## 本章面试重点

1. `computed` 和 `watch` 的区别。
2. `v-if` 和 `v-show` 的区别。
3. `v-for` 为什么要写 `key`，为什么不建议用 index。
4. `v-model` 的作用和常见修饰符。
5. Vue 生命周期常用钩子及适用场景。
6. `v-html` 的安全风险。

