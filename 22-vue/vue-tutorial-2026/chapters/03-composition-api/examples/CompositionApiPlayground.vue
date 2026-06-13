<script setup>
import { computed, onBeforeUnmount, onMounted, reactive, ref, toRefs, watch } from 'vue'
import { useCounter } from './useCounter'

const { count, doubleCount, increment, reset: resetCount } = useCounter(1)

const keyword = ref('')
const clock = ref('')
const users = ref([])
const query = reactive({
  role: 'all',
  enabledOnly: false
})

const { role, enabledOnly } = toRefs(query)

const visibleUsers = computed(() => {
  const value = keyword.value.trim().toLowerCase()

  return users.value.filter(user => {
    const matchKeyword = value === '' || user.name.toLowerCase().includes(value)
    const matchRole = role.value === 'all' || user.role === role.value
    const matchEnabled = !enabledOnly.value || user.enabled

    return matchKeyword && matchRole && matchEnabled
  })
})

const enabledCount = computed(() => users.value.filter(user => user.enabled).length)

watch(
  [keyword, role, enabledOnly],
  ([newKeyword, newRole, newEnabledOnly]) => {
    console.log('查询条件变化', {
      keyword: newKeyword,
      role: newRole,
      enabledOnly: newEnabledOnly
    })
  },
  { immediate: true }
)

let timer = 0

onMounted(() => {
  users.value = [
    { id: 1, name: 'Ada', role: 'admin', enabled: true },
    { id: 2, name: 'Linus', role: 'user', enabled: true },
    { id: 3, name: 'Grace', role: 'auditor', enabled: false },
    { id: 4, name: 'Brendan', role: 'user', enabled: true }
  ]

  timer = window.setInterval(() => {
    clock.value = new Date().toLocaleTimeString()
  }, 1000)
})

onBeforeUnmount(() => {
  window.clearInterval(timer)
})

function resetQuery() {
  keyword.value = ''
  Object.assign(query, {
    role: 'all',
    enabledOnly: false
  })
}
</script>

<template>
  <section class="composition-page">
    <header class="header">
      <div>
        <h2>组合式 API 用户查询</h2>
        <p>当前时间：{{ clock || '初始化中' }}</p>
      </div>
      <div class="counter">
        <span>计数：{{ count }} / 双倍：{{ doubleCount }}</span>
        <button @click="increment">+1</button>
        <button @click="resetCount">重置</button>
      </div>
    </header>

    <form class="filters" @submit.prevent>
      <input v-model.trim="keyword" placeholder="搜索英文名">
      <select v-model="role">
        <option value="all">全部角色</option>
        <option value="admin">管理员</option>
        <option value="user">普通用户</option>
        <option value="auditor">审计员</option>
      </select>
      <label>
        <input v-model="enabledOnly" type="checkbox">
        只看启用
      </label>
      <button type="button" @click="resetQuery">重置查询</button>
    </form>

    <p class="summary">启用用户 {{ enabledCount }} 人，当前展示 {{ visibleUsers.length }} 人。</p>

    <ul class="users">
      <li v-for="user in visibleUsers" :key="user.id">
        <strong>{{ user.name }}</strong>
        <span>{{ user.role }}</span>
        <span>{{ user.enabled ? '启用' : '禁用' }}</span>
      </li>
    </ul>
  </section>
</template>

<style scoped>
.composition-page {
  display: grid;
  gap: 16px;
  max-width: 860px;
  padding: 20px;
  border: 1px solid #d8e0ea;
  border-radius: 8px;
}

.header,
.filters,
.counter,
.users li {
  display: flex;
  align-items: center;
  gap: 12px;
}

.header {
  justify-content: space-between;
}

.header h2,
.header p,
.summary {
  margin: 0;
}

.header p,
.summary {
  color: #667085;
}

.filters {
  flex-wrap: wrap;
}

.filters input,
.filters select {
  min-height: 34px;
}

.users {
  display: grid;
  gap: 8px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.users li {
  justify-content: space-between;
  padding: 12px;
  border: 1px solid #e4e7ec;
  border-radius: 6px;
}
</style>

