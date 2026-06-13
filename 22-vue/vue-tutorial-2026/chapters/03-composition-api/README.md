# 第 3 章 Vue3 新语法

本章是 Vue3 的核心。对于新项目和面试，必须能熟练使用 Composition API，尤其是 `ref`、`reactive`、`computed`、`watch`、生命周期钩子和自定义 hook。

## 3.1 组合式 API 的了解

Options API 按配置项组织代码：

```js
export default {
  data() {},
  computed: {},
  watch: {},
  methods: {},
  mounted() {}
}
```

Composition API 按业务逻辑组织代码：

```vue
<script setup>
import { computed, ref, watch } from 'vue'

const keyword = ref('')
const users = ref([])

const visibleUsers = computed(() => {
  return users.value.filter(user => user.name.includes(keyword.value))
})

watch(keyword, value => {
  console.log('搜索条件变化', value)
})
</script>
```

为什么新项目常用 Composition API：

- 一个业务逻辑可以集中写在一起，不必拆到 `data`、`computed`、`methods` 多个配置项。
- 逻辑更容易抽成 hook，例如 `useUserList()`、`usePagination()`。
- TypeScript 类型推断更自然。
- `<script setup>` 可以减少样板代码。

Java 后端类比：Options API 像把字段、getter、方法、生命周期按固定区块放在一个类里；Composition API 更像把某个业务能力拆成一个可复用的 service 函数。

## 3.2 setup 组合式 API 入口函数

普通 `setup()` 写法：

```vue
<script>
import { ref } from 'vue'

export default {
  setup() {
    const count = ref(0)

    function increment() {
      count.value++
    }

    return {
      count,
      increment
    }
  }
}
</script>
```

`<script setup>` 写法：

```vue
<script setup>
import { ref } from 'vue'

const count = ref(0)

function increment() {
  count.value++
}
</script>
```

区别：

- 普通 `setup()` 需要 `return` 才能暴露给模板。
- `<script setup>` 顶层变量和函数可直接在模板中使用。
- 新项目优先使用 `<script setup>`。

## 3.3 利用 ref 函数定义响应式数据

`ref` 创建一个响应式包装对象：

```js
const count = ref(0)
count.value++
```

模板中会自动解包：

```vue
<p>{{ count }}</p>
```

使用场景：

- 字符串、数字、布尔值。
- 需要整体替换的对象或数组。
- DOM 引用，例如 `const inputRef = ref(null)`。

容易错的点：

- 在 JS 中访问和修改要写 `.value`。
- 在模板中不用写 `.value`。
- 解构普通对象里的 ref 可能导致理解混乱，保持命名清晰。

## 3.4 利用 reactive 函数定义响应式数据

`reactive` 用于对象或数组：

```js
const form = reactive({
  username: '',
  role: 'user',
  enabled: true
})

form.username = 'admin'
```

使用场景：

- 表单对象。
- 多字段查询条件。
- 局部复杂状态。

注意点：

- 不要直接整体替换 reactive 对象，否则会丢失响应式引用。
- 如果需要整体替换，优先使用 `ref({})`。

错误示例：

```js
let form = reactive({ username: '' })
form = { username: 'new' }
```

推荐：

```js
Object.assign(form, { username: 'new' })
```

或：

```js
const form = ref({ username: '' })
form.value = { username: 'new' }
```

## 3.5 toRefs 与 toRef 函数

直接解构 `reactive` 会丢失响应式：

```js
const state = reactive({ keyword: '', page: 1 })
const { keyword } = state
```

`keyword` 只是普通值，不再跟随 `state.keyword` 更新。

使用 `toRefs`：

```js
const state = reactive({ keyword: '', page: 1 })
const { keyword, page } = toRefs(state)
```

使用 `toRef`：

```js
const keyword = toRef(state, 'keyword')
```

实际场景：

- hook 返回 reactive 状态时，用 `toRefs` 暴露字段。
- 只想传出对象的某一个字段时，用 `toRef`。

## 3.6 readonly 与 shallowReadonly 函数

`readonly` 创建只读代理：

```js
const state = reactive({ user: { name: 'Ada' } })
const readOnlyState = readonly(state)
```

适合场景：

- 对外暴露状态，但不允许外部修改。
- 自定义 hook 返回只读数据和明确的修改函数。

`shallowReadonly` 只限制第一层：

```js
const state = shallowReadonly({
  user: { name: 'Ada' }
})
```

一般项目中，优先使用 `readonly`。只有明确需要浅层只读时才使用 `shallowReadonly`。

## 3.7 shallowRef 与 shallowReactive 函数

`shallowRef` 只追踪 `.value` 的替换，不深度追踪内部对象变化：

```js
const chartInstance = shallowRef(null)
```

适合：

- 第三方库实例。
- 大型不可变对象。
- 不希望 Vue 深度代理的数据。

`shallowReactive` 只让对象第一层响应式。普通业务表单很少需要。

判断标准：

- 普通业务数据：`ref` / `reactive`。
- 第三方实例、大对象性能优化：`shallowRef`。
- 没有明确性能问题，不要过早使用 shallow API。

## 3.8 toRaw 与 markRaw 函数

`toRaw` 获取响应式代理背后的原始对象：

```js
const rawForm = toRaw(form)
```

适合：

- 调试。
- 和某些不接受代理对象的第三方库交互。

不要长期持有 raw 对象并修改它，否则会绕过 Vue 响应式。

`markRaw` 标记对象不被转成响应式：

```js
const editor = markRaw(createEditor())
```

适合：

- 富文本编辑器实例。
- 图表库实例。
- 地图库实例。

## 3.9 computed 函数

Composition API 中的 computed：

```js
const enabledUsers = computed(() => {
  return users.value.filter(user => user.enabled)
})
```

可写 computed：

```js
const fullName = computed({
  get() {
    return `${firstName.value} ${lastName.value}`
  },
  set(value) {
    const [first, last] = value.split(' ')
    firstName.value = first
    lastName.value = last
  }
})
```

项目判断：

- 页面展示的派生值，用 computed。
- 请求接口、写缓存、操作 DOM，用 watch 或事件处理。

## 3.10 watch 函数

监听 ref：

```js
watch(keyword, (newValue, oldValue) => {
  console.log(newValue, oldValue)
})
```

监听 getter：

```js
watch(
  () => form.role,
  role => {
    console.log('角色变化', role)
  }
)
```

多个来源：

```js
watch([keyword, page], ([newKeyword, newPage]) => {
  fetchUsers(newKeyword, newPage)
})
```

即时执行：

```js
watch(keyword, fetchUsers, { immediate: true })
```

深度监听：

```js
watch(form, value => {
  console.log(value)
}, { deep: true })
```

更推荐的做法是监听具体字段，而不是整个大对象。

## 3.11 生命周期钩子函数

Composition API 生命周期：

```js
import { onBeforeUnmount, onMounted, onUpdated } from 'vue'

onMounted(() => {
  fetchUsers()
})

onUpdated(() => {
  console.log('组件更新')
})

onBeforeUnmount(() => {
  clearInterval(timer)
})
```

常用映射：

| Options API | Composition API |
| --- | --- |
| `beforeCreate` | 不需要，直接在 `setup` 中执行 |
| `created` | 不需要，直接在 `setup` 中执行 |
| `mounted` | `onMounted` |
| `beforeUpdate` | `onBeforeUpdate` |
| `updated` | `onUpdated` |
| `beforeUnmount` | `onBeforeUnmount` |
| `unmounted` | `onUnmounted` |

后台项目常见使用：

- 页面加载后请求列表：`onMounted`。
- 离开页面清理定时器：`onBeforeUnmount`。
- 页面缓存后恢复数据：后续结合 `KeepAlive` 学 `onActivated`、`onDeactivated`。

## 自定义 hook 的提前理解

组合式 API 最重要的收益是复用逻辑。比如分页逻辑：

```js
import { computed, ref } from 'vue'

export function usePagination(total) {
  const page = ref(1)
  const pageSize = ref(10)

  const pageCount = computed(() => Math.ceil(total.value / pageSize.value))

  function resetPage() {
    page.value = 1
  }

  return {
    page,
    pageSize,
    pageCount,
    resetPage
  }
}
```

第 4 章会正式讲自定义 hook。

## 本章面试重点

1. `ref` 和 `reactive` 的区别。
2. 为什么 JS 中 ref 要使用 `.value`，模板中不用？
3. `reactive` 解构后为什么会丢失响应式？如何解决？
4. `computed` 和 `watch` 在 Composition API 中如何使用？
5. `shallowRef`、`markRaw` 通常在什么场景使用？
6. Composition API 相比 Options API 对大型项目有什么优势？

