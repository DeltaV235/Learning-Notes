export const gradingWorkflow = {
  root: 'exercises',
  internalOnly: true,
  conventions: [
    '不要要求用户从零创建练习项目；章节进入项目阶段后，直接在 submissions/starter-vue-app 中提供基础 Vue 工程。',
    '用户提交练习后，把答案文件保存在对应章节 submissions 目录下，批改结果保存在 reviews 目录下。',
    '批改时读取练习的 rubric，但不要把所有内部批改规则直接展示给用户。',
    '用户提问后，把问题、解答、关联知识点追加到对应章节 qaNotes，并让它成为课件内容的一部分。'
  ]
}

const vueStarter = chapter => ({
  type: 'basic-vue-project',
  label: '基础 Vue 项目',
  path: `exercises/${chapter}/submissions/starter-vue-app`,
  hint: '进入该目录后只完成练习指定的组件或 composable，不需要重新创建 Vite/Vue 项目。'
})

const emptyStarter = chapter => ({
  type: 'empty',
  label: '空目录',
  path: `exercises/${chapter}/submissions`,
  hint: '当前阶段只需要理解和口述，不提供项目脚手架；后续进入编码阶段后会提供基础 Vue 项目。'
})

export const chapters = [
  {
    id: '01',
    slug: 'vue-overview',
    title: 'Vue.js 概述',
    priority: '了解',
    status: '已生成',
    summary: '建立 Vue3 的技术定位，明确 Java 后端转 Vue 的学习顺序。',
    submission: emptyStarter('01-vue-overview'),
    courseware: [
      {
        title: 'Vue 的定位',
        points: [
          'Vue 是用于构建用户界面的 JavaScript 框架，不是后端 MVC 框架，也不是 UI 组件库。',
          'Vue 负责把状态渲染成界面，并在状态变化后尽量精确地更新 DOM。',
          '在后台管理系统里，Vue 通常负责表单、表格、弹窗、筛选条件、菜单、路由和页面状态。'
        ],
        details: [
          'Java 后端开发者可以把 Vue 组件理解成一个有模板、有状态、有行为的 UI 模块。不同于 Controller 处理 HTTP 请求，Vue 组件处理浏览器中的用户交互。',
          'Vue Router 是前端单页应用内部的页面切换机制；Pinia 是前端共享状态容器；axios 是和后端接口通信的 HTTP 客户端。'
        ],
        examples: [
          {
            title: '最小状态渲染',
            description: '状态 `loginCount` 变化后，模板中的数字会自动更新。',
            code: `<template>
  <section>
    <p>用户名：{{ user.name }}</p>
    <button @click="loginCount++">登录 {{ loginCount }} 次</button>
  </section>
</template>`
          }
        ]
      },
      {
        title: 'Vue3 新项目默认选择',
        points: [
          '新项目优先使用 Vue3、Vite、单文件组件、Composition API 和 `<script setup>`。',
          'Pinia 是新项目的默认状态管理选择；Vuex 主要用于阅读旧项目。',
          'Element Plus 是 Vue3 后台管理系统常见 UI 框架，后续综合项目会围绕它展开。'
        ],
        details: [
          'Options API 仍然可用，本课程第 2 章会用它解释基础概念，因为很多旧项目仍使用这种写法。',
          '第 3 章开始会逐步切到 Composition API，这是新项目、TypeScript 和逻辑复用更常用的写法。'
        ],
        examples: [
          {
            title: 'Composition API 起步',
            description: '`ref` 创建响应式值，JavaScript 中修改要使用 `.value`。',
            code: `<script setup>
import { ref } from 'vue'

const count = ref(0)

function increment() {
  count.value++
}
</script>

<template>
  <button @click="increment">点击 {{ count }}</button>
</template>`
          }
        ]
      },
      {
        title: 'Java 后端转 Vue 的路线',
        points: [
          '先会写页面：模板、指令、列表、事件、表单。',
          '再理解状态：ref、reactive、computed、watch。',
          '然后做工程：组件通信、路由、请求封装、Pinia、Element Plus、TypeScript。'
        ],
        details: [
          '不要一开始钻源码。面试和项目更看重你能不能把用户列表、搜索表单、分页、编辑弹窗、权限按钮、路由守卫和接口错误处理写清楚。',
          '等能独立完成一个后台管理系统后，再补响应式原理、虚拟 DOM、编译优化等底层内容。'
        ]
      }
    ],
    exercises: [
      {
        id: '01-basic-01',
        type: '口述题',
        title: '解释 Vue 的声明式渲染',
        requirement: '用自己的话解释声明式渲染，并说明它和手动操作 DOM 的区别。',
        target: '口述或直接在对话中回答',
        rubric: ['能说出状态变化驱动页面更新', '能说明 Vue 负责维护 DOM 更新细节']
      },
      {
        id: '01-basic-02',
        type: '理解题',
        title: '区分 Vue Router、Pinia、axios',
        requirement: '分别说明 Vue Router、Pinia、axios 在后台管理系统中的职责。',
        target: '口述或直接在对话中回答',
        rubric: ['Router 负责页面路由', 'Pinia 负责共享状态', 'axios 负责 HTTP 请求']
      }
    ],
    qaNotes: [
      {
        id: 'qa-2026-06-17-spa',
        topic: 'Vue 项目形态',
        date: '2026-06-17',
        question: '什么是前端单页应用？',
        answer:
          '前端单页应用（SPA，Single Page Application）是浏览器先加载一个前端应用入口页面，后续页面切换主要由前端路由和 JavaScript 完成，不再每次都向服务器请求一整张新的 HTML 页面。它通常通过 axios、fetch 等工具向后端接口请求 JSON 数据，再由 Vue 根据状态变化更新当前页面中的组件。'
      }
    ]
  },
  {
    id: '02',
    slug: 'core-syntax',
    title: '核心语法',
    priority: '必学',
    status: '已生成',
    summary: '掌握模板、指令、计算属性、监听、列表、事件、表单和生命周期。',
    submission: vueStarter('02-core-syntax'),
    courseware: [
      {
        title: '模板语法：把状态声明成界面',
        points: [
          '插值语法 `{{ }}` 用于显示状态，适合写简单表达式，不适合塞复杂业务逻辑。',
          '`v-bind` 绑定动态属性，简写为 `:`；`v-on` 绑定事件，简写为 `@`。',
          '模板的职责是描述界面，不应该承担数据清洗、复杂筛选和接口请求。'
        ],
        details: [
          '后台页面最常见的问题是把筛选、排序、状态转换都写在模板中，导致模板难读。正确做法是把派生数据放到 computed，把动作放到 methods 或函数中。',
          '动态属性不仅能绑定 `href`，也能绑定 `class`、`style`、`disabled`、`title` 等，表格行状态、按钮权限、菜单高亮都依赖它。'
        ],
        examples: [
          {
            title: '属性和事件绑定',
            description: '这个示例展示 `:` 和 `@` 的基本职责。',
            code: `<template>
  <a :href="profileUrl">用户详情</a>
  <button :disabled="saving" @click="saveUser">保存</button>
</template>`
          }
        ]
      },
      {
        title: 'computed：把派生状态集中管理',
        points: [
          'computed 有缓存，依赖不变时不会重复计算。',
          '列表过滤、排序、统计、状态文案转换都适合 computed。',
          '不要在 computed 中修改原数组；排序前先复制。'
        ],
        details: [
          'computed 的本质是“值”，不是“动作”。如果模板需要展示一个由当前状态推导出来的结果，就优先考虑 computed。',
          '用户列表页面中，`visibleUsers`、`enabledCount`、`pageTitle` 都是典型 computed。'
        ],
        examples: [
          {
            title: '用户列表筛选和排序',
            description: '复制数组后排序，避免 computed 修改原始列表。',
            code: `computed: {
  visibleUsers() {
    const keyword = this.keyword.trim()
    return this.users
      .filter(user => keyword === '' || user.name.includes(keyword))
      .filter(user => this.role === 'all' || user.role === this.role)
  },
  sortedUsers() {
    return [...this.visibleUsers].sort((a, b) => b.score - a.score)
  }
}`
          }
        ]
      },
      {
        title: 'watch：只处理副作用',
        points: [
          'watch 用于状态变化后执行副作用，例如重新请求接口、写缓存、清空错误提示。',
          '`immediate: true` 可以让监听在初始化时先执行一次。',
          '`deep: true` 会带来额外成本，真实项目优先监听具体字段。'
        ],
        details: [
          '如果你只是想根据 keyword 得到过滤后的列表，不要用 watch。watch 更适合“状态变化后做一件事”，而不是“计算一个展示值”。',
          '搜索框自动请求接口时，要进一步考虑防抖，否则输入一个字就会请求一次。'
        ],
        examples: [
          {
            title: '监听搜索关键字',
            description: '初始化时请求一次，后续 keyword 变化后重新请求。',
            code: `watch: {
  keyword: {
    immediate: true,
    handler(value, oldValue) {
      console.log('搜索变化', value, oldValue)
      this.fetchUsers(value)
    }
  }
}`
          }
        ]
      },
      {
        title: '条件渲染、列表渲染和事件',
        points: [
          '`v-if` 会创建或销毁 DOM，适合权限控制、低频显示隐藏。',
          '`v-show` 只切换 `display`，适合筛选面板、Tab 内容等高频切换。',
          '`v-for` 必须使用稳定唯一 key，后台业务优先使用数据库 ID。',
          '事件修饰符能让常见交互更清晰，例如 `@submit.prevent` 和 `@click.stop`。'
        ],
        details: [
          '面试中常问 `v-if` 和 `v-show` 的区别：前者切换成本更高，后者初始渲染成本更高。',
          '删除按钮放在可点击表格行内部时，要用 `.stop` 阻止冒泡，否则点击删除也可能触发行点击。'
        ],
        examples: [
          {
            title: '列表行和删除按钮',
            description: '删除按钮阻止冒泡，避免触发行点击。',
            code: `<li v-for="user in visibleUsers" :key="user.id" @click="openDetail(user.id)">
  <strong>{{ user.name }}</strong>
  <span>{{ user.enabled ? '启用' : '禁用' }}</span>
  <button @click.stop="removeUser(user.id)">删除</button>
</li>`
          }
        ]
      },
      {
        title: '表单和生命周期',
        points: [
          '`v-model.trim` 适合用户名、关键字等字符串输入。',
          '`v-model.number` 适合年龄、数量、分页大小等数字输入。',
          '`mounted` 适合首屏请求和访问 DOM，`beforeUnmount` 适合清理定时器和事件监听。'
        ],
        details: [
          '后台表单必须有完整初始值。新增、编辑、重置表单时，字段缺失会导致模板里出现 undefined 或校验异常。',
          '前端校验用于提升体验，后端校验才是最终可信边界。前端不能因为做了校验就省略后端校验。'
        ],
        examples: [
          {
            title: '新增用户表单',
            description: '提交时先校验，成功后重置表单。',
            code: `<form @submit.prevent="submit">
  <input v-model.trim="form.name" placeholder="用户名">
  <input v-model.number="form.age" type="number">
  <button type="submit">新增</button>
</form>`
          }
        ]
      }
    ],
    exercises: [
      {
        id: '02-list-01',
        type: '编码题',
        title: '用户列表渲染',
        requirement: '在目标组件中创建用户数组，字段包含 id、name、role、enabled、score，用 v-for 渲染并绑定稳定 key。',
        target: 'exercises/02-core-syntax/submissions/starter-vue-app/src/exercises/02-list-01.vue',
        rubric: ['key 使用 user.id', '能显示角色和启用状态', '没有使用数组下标作为 key']
      },
      {
        id: '02-computed-01',
        type: '编码题',
        title: '筛选和排序',
        requirement: '用 computed 根据 keyword 和 role 过滤用户，并按 score 降序排序。',
        target: 'exercises/02-core-syntax/submissions/starter-vue-app/src/exercises/02-computed-01.vue',
        rubric: ['过滤逻辑写在 computed 中', '排序前复制数组', 'keyword 会去除首尾空格']
      },
      {
        id: '02-watch-01',
        type: '编码题',
        title: '监听搜索关键字',
        requirement: '监听 keyword 的变化，打印新值和旧值，并设置 immediate。',
        target: 'exercises/02-core-syntax/submissions/starter-vue-app/src/exercises/02-watch-01.vue',
        rubric: ['watch 能拿到 newValue 和 oldValue', '使用 immediate', '不把派生展示值写进 watch']
      },
      {
        id: '02-form-01',
        type: '综合题',
        title: '新增用户表单',
        requirement: '使用 v-model.trim 和 v-model.number 完成新增用户表单，提交时校验用户名不能为空。',
        target: 'exercises/02-core-syntax/submissions/starter-vue-app/src/exercises/02-form-01.vue',
        rubric: ['用户名使用 trim', '年龄使用 number', '提交后能重置表单', '空用户名会提示错误']
      },
      {
        id: '02-event-01',
        type: '综合题',
        title: '删除用户',
        requirement: '在用户行中添加删除按钮，删除按钮使用事件修饰符阻止行点击事件冒泡。',
        target: 'exercises/02-core-syntax/submissions/starter-vue-app/src/exercises/02-event-01.vue',
        rubric: ['使用 @click.stop', '删除后列表响应式更新', '行点击和按钮点击互不干扰']
      }
    ],
    qaNotes: [
      {
        id: 'qa-2026-06-17-computed-syntax',
        topic: 'computed 语法拆解',
        date: '2026-06-17',
        question:
          'const keyword = this.keyword.trim() 是引用赋值还是变量拷贝？[...this.visibleUsers].sort((a, b) => b.score - a.score) 和 visibleUsers() 语法分别是什么意思？',
        answer:
          'const keyword = this.keyword.trim() 是把 trim() 返回的新字符串绑定到局部常量 keyword，不是引用 this.keyword；字符串是基本类型且不可变，trim() 不会修改原字符串。[...this.visibleUsers] 使用数组展开语法浅拷贝一个新数组，sort() 会原地排序这个新数组，比较函数 (a, b) => b.score - a.score 表示按 score 从大到小排序。visibleUsers() 写在 computed 对象中是 JavaScript 对象字面量的方法简写，不是 JSON；等价于 visibleUsers: function () { ... }，Vue 会把它注册成计算属性，模板中按 visibleUsers 读取。'
      }
    ]
  },
  {
    id: '03',
    slug: 'composition-api',
    title: 'Vue3 新语法',
    priority: '必学',
    status: '已生成',
    summary: '掌握 Composition API、script setup、响应式 API、watch 和生命周期。',
    submission: vueStarter('03-composition-api'),
    courseware: [
      {
        title: 'Composition API 解决什么问题',
        points: [
          'Options API 按 data、computed、methods 分区，业务逻辑容易被拆散。',
          'Composition API 按业务能力组织代码，适合抽取 useUserList、usePagination 这类 hook。',
          '`<script setup>` 让顶层变量和函数直接暴露给模板，减少样板代码。'
        ],
        details: [
          '大型后台页面通常包含列表、查询、分页、编辑弹窗、权限按钮等多组逻辑。Composition API 可以让每组逻辑集中在一起，再拆成 hook。',
          '它不是为了替代所有 Options API 旧项目，而是让新项目在逻辑复用和 TypeScript 类型推断上更自然。'
        ],
        examples: [
          {
            title: 'script setup 基础写法',
            description: '无需 return，模板可以直接使用 count 和 increment。',
            code: `<script setup>
import { ref } from 'vue'

const count = ref(0)

function increment() {
  count.value++
}
</script>`
          }
        ]
      },
      {
        title: 'ref 和 reactive 的选择',
        points: [
          '`ref` 适合基本类型，也适合需要整体替换的对象或数组。',
          '`reactive` 适合表单对象、多字段查询条件和局部复杂状态。',
          'JavaScript 中访问 ref 要写 `.value`，模板中会自动解包。'
        ],
        details: [
          '如果状态需要整体替换，例如接口返回新列表，`ref([])` 很自然。若是表单对象逐字段修改，`reactive({})` 可读性更好。',
          '不要整体替换 reactive 对象。重置表单时使用 `Object.assign(form, initialForm)`，保持原响应式引用。'
        ],
        examples: [
          {
            title: '表单对象重置',
            description: '保留 reactive 引用，只替换字段值。',
            code: `const form = reactive({
  username: '',
  role: 'user',
  enabled: true
})

function resetForm() {
  Object.assign(form, {
    username: '',
    role: 'user',
    enabled: true
  })
}`
          }
        ]
      },
      {
        title: 'toRefs、computed 和 watch',
        points: [
          '直接解构 reactive 对象会丢失响应式，返回给组件时可使用 toRefs。',
          'computed 负责派生状态，例如过滤后的用户列表、统计数量和页面标题。',
          'watch 负责副作用，可以监听 ref、getter 或多个来源数组。'
        ],
        details: [
          '自定义 hook 常见返回方式是 `return { ...toRefs(state), reset }`，这样组件中解构后仍能保持响应式。',
          'watch 多来源时，回调拿到的是新值数组和旧值数组。真实请求接口时还要考虑防抖、取消过期请求和错误处理。'
        ],
        examples: [
          {
            title: '多来源监听',
            description: 'keyword、page、pageSize 任一变化都重新拉取列表。',
            code: `watch([keyword, page, pageSize], ([newKeyword, newPage, newSize]) => {
  fetchUsers({
    keyword: newKeyword,
    page: newPage,
    pageSize: newSize
  })
}, { immediate: true })`
          }
        ]
      },
      {
        title: 'shallowRef、markRaw 和生命周期',
        points: [
          '`shallowRef` 常用于第三方库实例或大型不可变对象。',
          '`markRaw` 可以标记对象不被 Vue 转成响应式，常见于编辑器、图表和地图实例。',
          '`onMounted` 适合初始化请求，`onBeforeUnmount` 适合清理定时器和外部资源。'
        ],
        details: [
          '普通业务数据不要过早使用 shallow API。只有你明确不需要深层响应式，或者第三方实例被代理后会出问题时再用。',
          '生命周期清理非常重要。比如定时器、window 事件、图表实例不清理，页面切换多次后会产生重复监听和内存泄漏。'
        ],
        examples: [
          {
            title: '定时器清理',
            description: '组件销毁前清理 interval。',
            code: `let timer = 0

onMounted(() => {
  timer = window.setInterval(fetchLatestStatus, 3000)
})

onBeforeUnmount(() => {
  window.clearInterval(timer)
})`
          }
        ]
      },
      {
        title: '自定义 hook 的最小形态',
        points: [
          'hook 是普通函数，不依赖组件实例 this。',
          'hook 可以返回状态、派生状态和修改函数。',
          '后续组件通信和项目章节会大量使用 hook 抽取业务逻辑。'
        ],
        details: [
          'Java 后端可以把 hook 粗略类比为可复用的业务能力函数，但它运行在浏览器端，服务于组件状态和交互。',
          '一个好的 hook 应该边界清晰，例如只处理分页、只处理用户查询、只处理弹窗状态。'
        ],
        examples: [
          {
            title: 'useCounter',
            description: '返回响应式状态、派生值和操作函数。',
            code: `export function useCounter(initialValue = 0) {
  const count = ref(initialValue)
  const doubleCount = computed(() => count.value * 2)

  function increment() {
    count.value++
  }

  function reset() {
    count.value = initialValue
  }

  return { count, doubleCount, increment, reset }
}`
          }
        ]
      }
    ],
    exercises: [
      {
        id: '03-ref-01',
        type: '编码题',
        title: 'ref 计数器',
        requirement: '使用 ref 创建 count，编写 increment 和 reset，并在模板中展示 count。',
        target: 'exercises/03-composition-api/submissions/starter-vue-app/src/exercises/03-ref-01.vue',
        rubric: ['JS 中使用 count.value', '模板中直接使用 count', 'reset 能恢复初始值']
      },
      {
        id: '03-reactive-01',
        type: '编码题',
        title: 'reactive 查询表单',
        requirement: '使用 reactive 创建 keyword、role、enabledOnly，并使用 Object.assign 重置。',
        target: 'exercises/03-composition-api/submissions/starter-vue-app/src/exercises/03-reactive-01.vue',
        rubric: ['没有整体替换 reactive 对象', '重置后模板同步更新', '字段命名清晰']
      },
      {
        id: '03-computed-01',
        type: '编码题',
        title: 'visibleUsers',
        requirement: '用 computed 根据 keyword、role、enabledOnly 计算展示列表。',
        target: 'exercises/03-composition-api/submissions/starter-vue-app/src/exercises/03-computed-01.vue',
        rubric: ['使用 computed', '同时处理关键字、角色和启用状态', '没有在模板里堆复杂表达式']
      },
      {
        id: '03-watch-01',
        type: '编码题',
        title: '监听多个来源',
        requirement: '使用 watch 监听 keyword、page、pageSize，变化时模拟 fetchUsers。',
        target: 'exercises/03-composition-api/submissions/starter-vue-app/src/exercises/03-watch-01.vue',
        rubric: ['watch 来源是数组', '能拿到新值数组', '设置 immediate']
      },
      {
        id: '03-hook-01',
        type: '综合题',
        title: '抽取 useCounter',
        requirement: '补全 useCounter，返回 count、doubleCount、increment、reset。',
        target: 'exercises/03-composition-api/submissions/starter-vue-app/src/composables/useCounter.js',
        rubric: ['doubleCount 使用 computed', 'hook 不依赖组件实例 this', '返回值能在组件中直接使用']
      }
    ],
    qaNotes: []
  },
  {
    id: '04',
    slug: 'components',
    title: '组件详解',
    priority: '必学',
    status: '目录已建立',
    summary: '下一批生成组件通信、插槽、内置组件和代码封装。',
    submission: vueStarter('04-components'),
    courseware: [
      {
        title: '本章生成计划',
        points: [
          '组件定义与样式隔离。',
          'props、emits、ref、defineExpose、attrs。',
          'provide、inject、mitt、slot、KeepAlive、Teleport、自定义 directive、hook、plugin。'
        ],
        details: ['生成本章时会同步补齐组件示例和对应 starter-vue-app 的目标文件。']
      }
    ],
    exercises: [
      {
        id: '04-plan-01',
        type: '预习题',
        title: '组件通信场景归类',
        requirement: '列举父传子、子传父、跨层共享、兄弟组件通信分别适合的 Vue 能力。',
        target: '后续生成第 4 章时补齐目标文件',
        rubric: ['能区分 props、emits、provide/inject、Pinia 或 mitt']
      }
    ],
    qaNotes: [
      {
        id: 'qa-2026-06-17-sfc',
        topic: '组件基础',
        date: '2026-06-17',
        question: '什么是单文件组件？',
        answer:
          '单文件组件（Single File Component，SFC）是 Vue 项目中以 .vue 结尾的组件文件，通常把一个组件的模板 template、逻辑 script 和样式 style 写在同一个文件里。它不是浏览器原生能直接运行的文件，而是由 Vite、Vue 编译器等构建工具编译成浏览器可执行的 JavaScript 和 CSS。'
      }
    ]
  },
  {
    id: '05',
    slug: 'router',
    title: 'Vue 路由',
    priority: '必学',
    status: '目录已建立',
    summary: '后续生成路由配置、动态传参、路由守卫、缓存和懒加载。',
    submission: vueStarter('05-router'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['简单路由、嵌套路由、动态路由和命名路由。', 'params、query、props 映射、编程式导航。', '路由守卫、懒加载、滚动行为和缓存页面。'],
        details: ['生成本章时会把 Router 项目结构加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '05-plan-01',
        type: '预习题',
        title: 'params 和 query',
        requirement: '说明 params 和 query 的 URL 表现、刷新保留情况和适用场景。',
        target: '后续生成第 5 章时补齐目标文件',
        rubric: ['能从 URL 和业务场景区分两者']
      }
    ],
    qaNotes: []
  },
  {
    id: '06',
    slug: 'data-request',
    title: '数据请求',
    priority: '必学',
    status: '目录已建立',
    summary: '后续生成接口调试、fetch、axios、拦截器和错误处理。',
    submission: vueStarter('06-data-request'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['接口类型、Swagger、本地接口调试。', 'fetch 和 axios 的差异。', 'axios 封装、token 携带、401 处理、错误提示。'],
        details: ['生成本章时会把请求封装示例加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '06-plan-01',
        type: '预习题',
        title: '接口职责',
        requirement: '说明前端接口封装层应该处理哪些公共逻辑。',
        target: '后续生成第 6 章时补齐目标文件',
        rubric: ['能提到 baseURL、headers、token、错误处理、响应解包']
      }
    ],
    qaNotes: []
  },
  {
    id: '07',
    slug: 'state-management',
    title: '状态管理',
    priority: '必学',
    status: '目录已建立',
    summary: '后续以 Pinia 为主，Vuex 作为旧项目阅读内容。',
    submission: vueStarter('07-state-management'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['常规组件通信的弊端。', 'Vuex 旧项目阅读。', 'Pinia state、getters、actions 和持久化。'],
        details: ['生成本章时会把 Pinia store 结构加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '07-plan-01',
        type: '预习题',
        title: '状态归属',
        requirement: '区分哪些状态应该放组件内部，哪些状态应该放 Pinia。',
        target: '后续生成第 7 章时补齐目标文件',
        rubric: ['能说明局部状态和跨页面共享状态的区别']
      }
    ],
    qaNotes: [
      {
        id: 'qa-2026-06-17-local-storage',
        topic: '状态持久化',
        date: '2026-06-17',
        question: 'localStorage 是什么？',
        answer:
          'localStorage 是浏览器提供的本地持久化存储 API，按同源站点保存 key/value 字符串数据。页面刷新或浏览器关闭后数据仍然保留，除非主动删除、用户清理浏览器数据或站点存储被浏览器策略清掉。Vue 项目常用它保存主题、侧边栏折叠状态、简单偏好设置，或配合 Pinia 做状态持久化；但不应该把密码、长期敏感凭证等高风险数据直接放进去。'
      },
      {
        id: 'qa-2026-06-17-local-storage-vs-pinia',
        topic: '状态归属',
        date: '2026-06-17',
        question: 'localStorage 和 Pinia 分别适合存储什么数据？',
        answer:
          'Pinia 适合存放应用运行期间需要多个组件或多个页面共享的响应式业务状态，例如当前用户信息、权限菜单、购物车、当前选中的组织、列表查询条件等；这些状态变化后页面会自动更新。localStorage 适合存放刷新页面后仍希望保留的少量字符串化数据，例如主题、侧边栏折叠状态、用户偏好、Pinia 状态持久化后的快照。简单判断：要让页面响应式更新，用 Pinia；要跨刷新保留，用 localStorage；既要共享又要刷新保留，就让 Pinia 管状态，再把必要字段持久化到 localStorage。'
      }
    ]
  },
  {
    id: '08',
    slug: 'ui-framework',
    title: 'UI 框架',
    priority: '必学',
    status: '目录已建立',
    summary: '后续以 Element Plus 后台页面为主，Vant4 只做了解。',
    submission: vueStarter('08-ui-framework'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['Element Plus 安装和引入。', '表格、表单、分页、弹窗、消息提示。', 'Vant4 移动端场景了解。'],
        details: ['生成本章时会把 Element Plus 后台页面骨架加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '08-plan-01',
        type: '预习题',
        title: '后台页面组件拆分',
        requirement: '列出用户管理页面通常需要哪些 Element Plus 组件。',
        target: '后续生成第 8 章时补齐目标文件',
        rubric: ['能提到表格、表单、分页、弹窗、按钮、消息确认']
      }
    ],
    qaNotes: []
  },
  {
    id: '09',
    slug: 'typescript',
    title: 'TypeScript',
    priority: '可学',
    status: '目录已建立',
    summary: '后续生成 TypeScript 基础类型、接口、类、泛型和常用操作符。',
    submission: vueStarter('09-typescript'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['基础类型、类型推断、联合类型、类型断言。', '数组、元组、枚举、函数、接口、类、泛型。', '类型别名、keyof、typeof、声明文件。'],
        details: ['生成本章时会把 TypeScript 练习结构加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '09-plan-01',
        type: '预习题',
        title: '接口建模',
        requirement: '为用户列表接口设计 User 类型，字段包含 id、name、role、enabled。',
        target: '后续生成第 9 章时补齐目标文件',
        rubric: ['字段类型准确', 'role 可以考虑联合类型']
      }
    ],
    qaNotes: []
  },
  {
    id: '10',
    slug: 'vue-ts',
    title: 'Vue3 + TypeScript',
    priority: '必学',
    status: '目录已建立',
    summary: '后续生成 Vue、Router、Pinia、axios 与 TypeScript 的组合实践。',
    submission: vueStarter('10-vue-ts'),
    courseware: [
      {
        title: '本章生成计划',
        points: ['ref、reactive、computed 的类型写法。', 'defineProps、defineEmits 类型。', 'Vue Router、Pinia、axios 的类型封装。'],
        details: ['生成本章时会把 TypeScript 版综合练习加入 starter-vue-app。']
      }
    ],
    exercises: [
      {
        id: '10-plan-01',
        type: '预习题',
        title: 'props 类型',
        requirement: '说明 defineProps 如何给组件 props 添加类型。',
        target: '后续生成第 10 章时补齐目标文件',
        rubric: ['能写出泛型形式', '能区分必填和可选字段']
      }
    ],
    qaNotes: []
  }
]

export const projectPlan = {
  title: 'Element Plus 后台管理系统项目',
  status: '规划已建立',
  goals: [
    '登录页、首页仪表盘、用户管理、角色管理、菜单与权限。',
    '表格查询、新增、编辑、删除、路由守卫和 Pinia 用户状态。',
    'axios 请求封装、Element Plus 表单校验、TypeScript 接口类型。'
  ],
  stack: ['Vue3', 'Vite', 'TypeScript', 'Vue Router', 'Pinia', 'axios', 'Element Plus']
}

const learningGoalsByChapter = {
  '01': [
    '能用自己的话说明 Vue 在前端项目中的职责，而不是只背“渐进式框架”这个词。',
    '能把 Vue、Vue Router、Pinia、axios、Element Plus 分别放到后台管理系统的正确位置。',
    '能解释为什么新项目优先选择 Vue3、Vite、Composition API 和 Pinia。',
    '能根据 Java 后端经验建立前端学习路线，避免一开始陷入源码或工具细节。'
  ],
  '02': [
    '能独立写出用户列表、搜索表单、筛选排序、删除按钮和空状态。',
    '能区分模板表达式、computed、watch、methods 各自承担的职责。',
    '能说明 v-if 与 v-show、computed 与 watch、v-for key 的常见面试点。',
    '能把表单输入、事件修饰符和生命周期钩子用于后台管理系统的真实页面。'
  ],
  '03': [
    '能用 script setup 写出 Composition API 组件。',
    '能根据场景选择 ref、reactive、computed、watch、toRefs。',
    '能避免 reactive 整体替换、ref 忘记 .value、watch 滥用等常见错误。',
    '能抽取最小自定义 hook，为后续组件封装和项目实战做准备。'
  ]
}

const textbookSupplements = {
  '01:Vue 的定位': {
    lead:
      '学习 Vue 前先要弄清楚它解决的问题：浏览器页面需要根据数据变化不断更新，如果每次都手动查询 DOM、拼接 HTML、绑定事件，代码很快会失控。Vue 的价值在于让开发者描述“状态是什么、界面应该长什么样”，更新细节交给框架处理。',
    details: [
      '在传统后端渲染页面中，服务端生成 HTML 后返回给浏览器；而 Vue 单页应用通常先加载一个前端应用，再通过接口请求数据并在浏览器中完成页面更新。',
      'Vue 不是数据库、不是接口层，也不负责后端权限校验。前端可以根据权限隐藏按钮，但真正的权限判断必须由后端接口兜底。',
      '后台系统中的“用户管理页面”通常由查询表单、按钮区、表格、分页、弹窗表单组成，这些都可以拆成 Vue 组件。'
    ],
    steps: [
      '先识别页面上会变化的数据，例如用户列表、搜索关键字、加载状态、弹窗开关。',
      '再把这些数据绑定到模板中，让模板根据状态自动显示不同内容。',
      '最后把用户点击、输入、提交等动作绑定到函数中，由函数修改状态或请求接口。'
    ],
    pitfalls: [
      '把 Vue 当成 jQuery 的替代品，只在组件里继续大量手动操作 DOM。',
      '认为前端隐藏按钮就等于完成权限控制，忽略后端接口鉴权。',
      '把所有状态都放全局，导致简单页面也难以追踪数据来源。'
    ],
    interview: [
      'Vue 的核心不是“语法糖”，而是响应式数据驱动视图更新。',
      'Vue 组件负责浏览器端交互，后端 Controller 负责服务端请求处理，两者职责不同。'
    ]
  },
  '01:Vue3 新项目默认选择': {
    lead:
      'Vue3 项目通常不是只安装一个 vue 包就结束，而是一组工程能力配合使用。Vite 负责开发服务器和构建，Vue Router 负责页面路由，Pinia 负责共享状态，Element Plus 提供后台 UI 组件。',
    details: [
      'Composition API 不是必须写在所有项目里，但它已经是 Vue3 新项目和 TypeScript 项目更常见的组织方式。',
      'Vuex 在许多旧项目里仍会出现，所以面试时要能读懂它；但新项目状态管理优先学 Pinia。',
      'Element Plus 的价值不只是“好看”，更重要的是表格、表单校验、弹窗、分页、消息确认等后台系统高频组件已经封装好了。'
    ],
    steps: [
      '创建项目时优先使用 Vite 或 create-vue。',
      '先完成页面组件，再接入 Router、Pinia 和 axios。',
      '等基础页面跑通后，再逐步引入 Element Plus 和 TypeScript。'
    ],
    pitfalls: [
      '还没掌握模板和响应式，就先堆路由、状态管理、UI 框架。',
      '把 Vuex 当作 Vue3 新项目默认方案，忽略 Pinia 的简化写法。',
      '复制旧项目 Options API 写法后，不理解 Composition API 的实际价值。'
    ],
    interview: [
      '回答技术选型时要说清楚职责：Vite 构建、Router 路由、Pinia 状态、axios 请求、Element Plus UI。',
      'Vue3 新项目优先 Composition API 的原因包括逻辑复用、类型推断和大型组件组织。'
    ]
  },
  '01:Java 后端转 Vue 的路线': {
    lead:
      'Java 后端学习 Vue 的最大优势是工程思维已经具备，最大障碍是容易用后端分层模型硬套前端。前端学习要先围绕用户界面和交互流转，而不是先纠结目录分层。',
    details: [
      '后端 DTO 与前端接口类型可以类比，但前端状态不等于 DTO。一个页面可能同时有接口数据、表单草稿、加载状态、校验错误和弹窗状态。',
      '后端 Service 强调业务规则复用，前端 hook 强调组件逻辑复用，两者都讲边界，但运行环境完全不同。',
      '能写后台管理系统是 Vue 全栈面试的核心证明，比单独背 API 更有效。'
    ],
    steps: [
      '先用静态数据写出列表、表单和按钮交互。',
      '再把静态数据替换为 axios 请求返回的数据。',
      '最后加上路由、登录态、权限菜单、Pinia 和 TypeScript。'
    ],
    pitfalls: [
      '上来就学源码和虚拟 DOM，结果不会写表单和表格。',
      '过早抽象目录和工具，页面业务还没跑通就开始重构。',
      '忽略浏览器调试能力，不会看 Network、Console 和 Vue Devtools。'
    ],
    interview: [
      '面试里可以用“用户管理页面”贯穿说明组件、路由、请求、状态和 UI 框架。',
      '讲学习路线时要体现项目落地能力，而不是只列 API 名称。'
    ]
  },
  '02:模板语法：把状态声明成界面': {
    lead:
      '模板语法是 Vue 的入口。它让你在 HTML 结构中声明数据如何显示、属性如何绑定、事件如何触发。后台系统中多数页面不是复杂动画，而是大量数据展示和表单交互，所以模板是否清晰直接影响维护成本。',
    details: [
      '插值语法适合显示简单值，例如用户名、数量、状态文案。只要表达式开始变长，就应该把它移到 computed 或函数中。',
      '动态属性绑定可以控制按钮禁用、链接地址、表格行样式、图片地址和表单控件状态。',
      '事件绑定要尽量读得像业务动作，例如 `saveUser`、`removeUser`、`openEditDialog`，不要写成含糊的 `handleClick1`。'
    ],
    steps: [
      '先在 data 或响应式变量中准备页面状态。',
      '用插值语法显示文本值，用 `:` 绑定动态属性。',
      '用 `@` 绑定用户动作，并让动作函数修改状态或调用接口。'
    ],
    pitfalls: [
      '在模板里写过长的三元表达式，导致结构难读。',
      '把接口请求直接塞到模板表达式里。',
      '事件函数命名不表达业务含义，后续排查时不知道按钮真正做什么。'
    ],
    interview: [
      '`v-bind` 的简写是 `:`，`v-on` 的简写是 `@`。',
      '模板表达式应该保持简单，复杂派生值交给 computed。'
    ]
  },
  '02:computed：把派生状态集中管理': {
    lead:
      'computed 的核心用途是管理“由已有状态计算出来的值”。它不是为了少写一个函数，而是为了让页面中的派生逻辑有明确位置，并获得缓存能力。',
    details: [
      '在用户管理页面中，原始用户列表来自接口，展示列表则由关键字、角色、启用状态、排序规则共同决定。展示列表就是派生状态。',
      'computed 依赖的响应式数据不变时会复用上次结果。相比 methods，它更适合被模板频繁读取。',
      'computed 应该保持无副作用。不要在 computed 中请求接口、写 localStorage 或修改其他状态。'
    ],
    steps: [
      '先保留原始数据，例如 `users`。',
      '再声明筛选条件，例如 `keyword`、`role`、`enabledOnly`。',
      '最后用 computed 返回筛选和排序后的结果，模板只渲染这个结果。'
    ],
    pitfalls: [
      '在 computed 里直接调用 `this.users.sort()` 修改原数组。',
      '用 watch 手动维护一个 filteredUsers，导致状态重复且容易不同步。',
      'computed 中混入接口请求，破坏“派生值”的定位。'
    ],
    interview: [
      'computed 有缓存，methods 每次渲染调用都会重新执行。',
      'computed 适合派生状态，watch 适合副作用。'
    ]
  },
  '02:watch：只处理副作用': {
    lead:
      'watch 的定位是“观察状态变化，然后执行一段副作用逻辑”。它适合连接 Vue 响应式状态与外部世界，例如接口、本地缓存、浏览器事件或路由参数。',
    details: [
      '搜索条件变化后重新请求列表，是 watch 的典型场景；但根据搜索条件过滤本地数组，不应该用 watch。',
      '`immediate: true` 常用于页面初始化时立即执行一次加载逻辑，避免 mounted 里再写一份重复代码。',
      '监听对象时要谨慎使用 `deep: true`。后台表单字段很多时，深度监听会让变化来源变得模糊。'
    ],
    steps: [
      '明确要监听哪个状态，优先监听具体字段。',
      '在回调中执行副作用，例如请求接口或重置分页。',
      '如果需要初始化执行，添加 `immediate: true`。'
    ],
    pitfalls: [
      '用 watch 维护本可以由 computed 得到的展示值。',
      '对整个大表单 deep watch，却无法判断到底哪个字段变化。',
      '搜索框请求没有防抖，导致输入过程中发出大量接口请求。'
    ],
    interview: [
      'watch 可以拿到 newValue 和 oldValue。',
      'watchEffect 会自动收集依赖，watch 更明确，业务项目中监听具体来源更可控。'
    ]
  },
  '02:条件渲染、列表渲染和事件': {
    lead:
      '后台页面离不开条件、列表和事件。加载中、空状态、权限按钮、表格行、删除确认，本质上都是这些基础语法的组合。',
    details: [
      '`v-if` 适合“是否应该存在”的场景，例如无权限按钮、不同业务分支。',
      '`v-show` 适合“只是临时隐藏”的场景，例如高级筛选区域展开收起。',
      '列表 key 是 Vue 判断节点身份的重要依据。用 index 当 key 可能导致输入框状态错位、动画异常或组件复用错误。'
    ],
    steps: [
      '先处理加载、错误、空数据三类状态。',
      '再用 v-for 渲染业务列表，并绑定稳定 key。',
      '最后补充行点击、按钮点击和事件修饰符。'
    ],
    pitfalls: [
      '列表使用 index 作为 key。',
      '删除按钮不阻止冒泡，导致同时触发行点击。',
      '权限控制只用 disabled，不考虑是否应该完全不渲染按钮。'
    ],
    interview: [
      '`v-if` 切换成本高，`v-show` 初始渲染成本高。',
      'key 应该稳定且唯一，优先使用业务 ID。'
    ]
  },
  '02:表单和生命周期': {
    lead:
      '表单是后台系统最高频也最容易出错的部分。前端表单不仅要收集数据，还要处理初始值、校验、提交状态、错误提示和编辑回显。',
    details: [
      '`v-model` 会根据表单控件类型处理值同步。文本框、复选框、单选框、下拉框的绑定行为不同。',
      '新增表单和编辑表单都要有完整初始结构。不要等接口回来后才临时增加字段。',
      '生命周期钩子用来处理组件进入和离开时的动作，最常见是初始化请求和资源清理。'
    ],
    steps: [
      '先定义完整 form 对象，并设置合理默认值。',
      '用 `v-model` 绑定输入控件，并根据字段类型选择修饰符。',
      '提交时先做基础校验，再调用接口，成功后重置或关闭弹窗。',
      '组件卸载前清理定时器、窗口事件和第三方实例。'
    ],
    pitfalls: [
      '表单字段初始值缺失，导致模板访问 undefined。',
      '数字字段不用 `.number`，提交给后端时仍是字符串。',
      '组件销毁后定时器还在运行，造成重复请求或内存泄漏。'
    ],
    interview: [
      '`v-model.trim` 去首尾空格，`.number` 尝试转数字，`.lazy` 在 change 后同步。',
      '`mounted` 常用于首屏请求，`beforeUnmount` 常用于清理资源。'
    ]
  },
  '03:Composition API 解决什么问题': {
    lead:
      'Composition API 的重点不是“新语法更高级”，而是当一个组件包含多组业务逻辑时，可以按逻辑主题组织代码，而不是被 data、methods、watch 等选项拆散。',
    details: [
      '一个用户管理页面可能同时有查询条件、分页、表格选择、编辑弹窗、角色选项、权限按钮。Options API 会把这些逻辑分散到多个选项里。',
      'Composition API 可以把“用户查询”相关的状态、computed、watch 和函数放在一起，后续还能抽成 `useUserSearch`。',
      '`<script setup>` 是编译期语法，减少了 setup return 的样板代码。'
    ],
    steps: [
      '先在 script setup 中声明状态。',
      '围绕某个业务主题组织 computed、watch 和函数。',
      '当某组逻辑被多个组件复用时，再抽成 hook。'
    ],
    pitfalls: [
      '把 Composition API 写成一堆无序变量，反而比 Options API 更难读。',
      '为了抽 hook 而抽 hook，导致简单逻辑跨多个文件跳转。',
      '在 script setup 里继续使用 this。'
    ],
    interview: [
      'Composition API 的优势是逻辑复用、逻辑聚合和 TypeScript 类型推断。',
      'script setup 顶层声明可直接用于模板。'
    ]
  },
  '03:ref 和 reactive 的选择': {
    lead:
      'ref 和 reactive 都能创建响应式数据，但使用习惯不同。选择它们时，不要只看数据是不是对象，而要看这个状态未来如何更新。',
    details: [
      '`ref` 返回包装对象，所以在 JavaScript 中读写要通过 `.value`。模板中 Vue 会自动解包。',
      '`reactive` 返回代理对象，适合逐字段修改。它不能像普通变量一样整体替换，否则原来的响应式引用就丢了。',
      '数组在项目里经常用 `ref([])`，因为接口返回列表时通常会整体替换数组。'
    ],
    steps: [
      '基本类型优先 ref。',
      '需要整体替换的对象或数组优先 ref。',
      '表单对象、查询条件对象优先 reactive。',
      '如果要解构 reactive 返回值，使用 toRefs。'
    ],
    pitfalls: [
      '在 JavaScript 中忘记写 `.value`。',
      '整体替换 reactive 对象。',
      '把 reactive 解构成普通变量后还以为它是响应式。'
    ],
    interview: [
      'ref 在 JS 中用 `.value`，模板中自动解包。',
      'reactive 适合对象代理，但直接解构会丢失响应式。'
    ]
  },
  '03:toRefs、computed 和 watch': {
    lead:
      '组合式 API 中，响应式数据经常需要在函数之间传递。toRefs 解决 reactive 解构问题，computed 和 watch 则分别承担派生状态和副作用。',
    details: [
      '自定义 hook 如果直接返回 reactive 对象，使用者可能想解构它；这时返回 `toRefs(state)` 更稳。',
      'computed 返回的也是 ref，因此 JavaScript 中读取 computed 值同样是 `.value`，模板中自动解包。',
      'watch 监听 getter 时可以精确观察某个 reactive 字段，例如 `() => form.role`。'
    ],
    steps: [
      '在 hook 内部用 reactive 管理一组状态。',
      '返回时用 `...toRefs(state)` 暴露字段。',
      '用 computed 表达派生值，用 watch 连接接口请求或缓存写入。'
    ],
    pitfalls: [
      '解构 reactive 后页面不更新。',
      'watch 整个对象导致回调过于频繁。',
      'computed 中产生副作用。'
    ],
    interview: [
      'toRef 用于单个字段，toRefs 用于对象多个字段。',
      'watch 可以监听 ref、getter、reactive 对象和数组来源。'
    ]
  },
  '03:shallowRef、markRaw 和生命周期': {
    lead:
      '普通业务数据优先使用 ref 和 reactive。shallowRef、markRaw、toRaw 属于更偏工程边界和性能优化的 API，通常在接入第三方库时出现。',
    details: [
      '图表、地图、富文本编辑器实例通常不是业务数据，不需要 Vue 深度代理。',
      '如果第三方实例被 Vue 代理后行为异常，可以用 markRaw 跳过响应式转换。',
      '第三方实例往往需要在组件卸载前销毁，这也是生命周期清理的典型场景。'
    ],
    steps: [
      '创建第三方实例时，判断它是否需要响应式追踪。',
      '不需要深度追踪时，用 shallowRef 或 markRaw 保存。',
      '在 onBeforeUnmount 中移除事件监听并销毁实例。'
    ],
    pitfalls: [
      '普通表单数据滥用 shallowReactive，导致深层字段变化不更新。',
      '长期持有 toRaw 返回的原始对象并修改它，绕过 Vue 更新。',
      '组件销毁后不清理第三方实例。'
    ],
    interview: [
      'shallowRef 只追踪 value 替换，不深度追踪内部对象。',
      'markRaw 常用于不希望被代理的第三方实例。'
    ]
  },
  '03:自定义 hook 的最小形态': {
    lead:
      '自定义 hook 是 Composition API 最重要的工程收益之一。它让你把可复用的状态逻辑抽成函数，而不是复制粘贴到多个组件里。',
    details: [
      '一个 hook 应该围绕单一能力命名，例如 `usePagination`、`useDialog`、`useUserSearch`。',
      'hook 返回的内容要清晰：哪些是状态，哪些是派生值，哪些是动作函数。',
      'hook 不应该偷偷操作页面 DOM，也不应该依赖某个具体组件实例。'
    ],
    steps: [
      '先在组件里把逻辑写通。',
      '发现多个组件需要同样逻辑时，再抽成 useXxx 函数。',
      '在 hook 中返回状态和操作函数，由组件决定如何渲染。'
    ],
    pitfalls: [
      'hook 做得过大，一个函数里塞查询、分页、弹窗、权限全部逻辑。',
      'hook 命名不表达业务能力。',
      'hook 返回太多散乱字段，调用方难以理解。'
    ],
    interview: [
      'hook 本质是复用组合式逻辑的普通函数。',
      'hook 不依赖 this，适合跨组件复用。'
    ]
  }
}

for (const chapter of chapters) {
  chapter.learningGoals = learningGoalsByChapter[chapter.id] ?? chapter.learningGoals ?? []

  for (const section of chapter.courseware) {
    const supplement = textbookSupplements[`${chapter.id}:${section.title}`]
    if (!supplement) {
      continue
    }

    Object.assign(section, {
      ...supplement,
      details: [...(section.details ?? []), ...(supplement.details ?? [])]
    })
  }
}
