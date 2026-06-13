<script>
export default {
  name: 'CoreSyntaxPlayground',
  data() {
    return {
      keyword: '',
      role: 'all',
      sortByScore: true,
      showAdvanced: true,
      users: [
        { id: 1, name: '张三', role: 'admin', enabled: true, score: 92 },
        { id: 2, name: '李四', role: 'user', enabled: false, score: 76 },
        { id: 3, name: '王五', role: 'user', enabled: true, score: 88 },
        { id: 4, name: '赵六', role: 'auditor', enabled: true, score: 81 }
      ]
    }
  },
  computed: {
    enabledCount() {
      return this.users.filter(user => user.enabled).length
    },
    pageTitle() {
      return `用户管理（启用 ${this.enabledCount} 人）`
    },
    visibleUsers() {
      const keyword = this.keyword.trim()
      const filtered = this.users.filter(user => {
        const matchKeyword = keyword === '' || user.name.includes(keyword)
        const matchRole = this.role === 'all' || user.role === this.role
        return matchKeyword && matchRole
      })

      if (!this.sortByScore) {
        return filtered
      }

      return [...filtered].sort((a, b) => b.score - a.score)
    }
  },
  watch: {
    keyword: {
      immediate: true,
      handler(newValue, oldValue) {
        console.log('搜索关键字变化', { newValue, oldValue })
      }
    }
  },
  methods: {
    toggleUser(user) {
      user.enabled = !user.enabled
    },
    removeUser(id) {
      this.users = this.users.filter(user => user.id !== id)
    },
    resetFilters() {
      this.keyword = ''
      this.role = 'all'
      this.sortByScore = true
    }
  }
}
</script>

<template>
  <section class="panel">
    <header class="toolbar">
      <div>
        <h2>{{ pageTitle }}</h2>
        <p>演示插值、指令、计算属性、监听、动态样式和列表渲染。</p>
      </div>
      <button @click="showAdvanced = !showAdvanced">
        {{ showAdvanced ? '收起筛选' : '展开筛选' }}
      </button>
    </header>

    <form v-show="showAdvanced" class="filters" @submit.prevent>
      <input v-model.trim="keyword" placeholder="按姓名搜索">
      <select v-model="role">
        <option value="all">全部角色</option>
        <option value="admin">管理员</option>
        <option value="user">普通用户</option>
        <option value="auditor">审计员</option>
      </select>
      <label>
        <input v-model="sortByScore" type="checkbox">
        按分数排序
      </label>
      <button type="button" @click="resetFilters">重置</button>
    </form>

    <p v-if="visibleUsers.length === 0" class="empty">暂无匹配用户</p>

    <ul v-else class="user-list">
      <li
        v-for="user in visibleUsers"
        :key="user.id"
        :class="{ disabled: !user.enabled }"
        @click="toggleUser(user)"
      >
        <strong>{{ user.name }}</strong>
        <span>{{ user.role }}</span>
        <span>{{ user.score }} 分</span>
        <span class="status">{{ user.enabled ? '启用' : '禁用' }}</span>
        <button @click.stop="removeUser(user.id)">删除</button>
      </li>
    </ul>
  </section>
</template>

<style scoped>
.panel {
  display: grid;
  gap: 16px;
  max-width: 860px;
  padding: 20px;
  border: 1px solid #d8e0ea;
  border-radius: 8px;
}

.toolbar,
.filters,
.user-list li {
  display: flex;
  align-items: center;
  gap: 12px;
}

.toolbar {
  justify-content: space-between;
}

.toolbar h2,
.toolbar p {
  margin: 0;
}

.toolbar p {
  margin-top: 6px;
  color: #667085;
}

.filters {
  flex-wrap: wrap;
}

.filters input,
.filters select {
  min-height: 34px;
}

.empty {
  color: #8a4b18;
}

.user-list {
  display: grid;
  gap: 8px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.user-list li {
  justify-content: space-between;
  padding: 12px;
  border: 1px solid #e4e7ec;
  border-radius: 6px;
  cursor: pointer;
}

.user-list li.disabled {
  color: #98a2b3;
  background: #f2f4f7;
}

.status {
  min-width: 44px;
}
</style>

