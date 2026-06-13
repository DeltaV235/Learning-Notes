<script>
let nextId = 4

export default {
  name: 'FormAndListPractice',
  data() {
    return {
      form: {
        name: '',
        age: 18,
        role: 'user',
        enabled: true
      },
      users: [
        { id: 1, name: 'Ada', age: 28, role: 'admin', enabled: true },
        { id: 2, name: 'Linus', age: 35, role: 'user', enabled: true },
        { id: 3, name: 'Grace', age: 31, role: 'auditor', enabled: false }
      ],
      errorMessage: ''
    }
  },
  methods: {
    submit() {
      if (!this.form.name) {
        this.errorMessage = '用户名不能为空'
        return
      }

      this.users.push({
        id: nextId++,
        ...this.form
      })

      this.form = {
        name: '',
        age: 18,
        role: 'user',
        enabled: true
      }
      this.errorMessage = ''
    },
    remove(id) {
      this.users = this.users.filter(user => user.id !== id)
    }
  }
}
</script>

<template>
  <section class="form-page">
    <form class="user-form" @submit.prevent="submit">
      <label>
        用户名
        <input v-model.trim="form.name" placeholder="请输入用户名">
      </label>

      <label>
        年龄
        <input v-model.number="form.age" type="number" min="1">
      </label>

      <label>
        角色
        <select v-model="form.role">
          <option value="admin">管理员</option>
          <option value="user">普通用户</option>
          <option value="auditor">审计员</option>
        </select>
      </label>

      <label class="inline">
        <input v-model="form.enabled" type="checkbox">
        启用账号
      </label>

      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <button type="submit">新增用户</button>
    </form>

    <ul class="users">
      <li v-for="user in users" :key="user.id">
        <span>{{ user.name }}</span>
        <span>{{ user.age }} 岁</span>
        <span>{{ user.role }}</span>
        <span>{{ user.enabled ? '启用' : '禁用' }}</span>
        <button @click="remove(user.id)">删除</button>
      </li>
    </ul>
  </section>
</template>

<style scoped>
.form-page {
  display: grid;
  gap: 20px;
  max-width: 720px;
}

.user-form,
.users {
  display: grid;
  gap: 12px;
}

.user-form {
  padding: 16px;
  border: 1px solid #d0d5dd;
  border-radius: 8px;
}

.user-form label {
  display: grid;
  gap: 6px;
}

.user-form .inline {
  display: flex;
  align-items: center;
}

.error {
  margin: 0;
  color: #b42318;
}

.users {
  padding: 0;
  margin: 0;
  list-style: none;
}

.users li {
  display: grid;
  grid-template-columns: 1fr 80px 100px 80px auto;
  gap: 10px;
  align-items: center;
  padding: 10px;
  border: 1px solid #e4e7ec;
  border-radius: 6px;
}
</style>

