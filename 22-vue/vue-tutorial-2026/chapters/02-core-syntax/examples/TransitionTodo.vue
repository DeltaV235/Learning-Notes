<script>
let nextId = 3

export default {
  name: 'TransitionTodo',
  data() {
    return {
      text: '',
      todos: [
        { id: 1, title: '整理接口文档' },
        { id: 2, title: '完成用户列表筛选' }
      ]
    }
  },
  methods: {
    addTodo() {
      const title = this.text.trim()
      if (!title) {
        return
      }
      this.todos.push({ id: nextId++, title })
      this.text = ''
    },
    removeTodo(id) {
      this.todos = this.todos.filter(todo => todo.id !== id)
    }
  }
}
</script>

<template>
  <section class="todo-page">
    <form class="todo-form" @submit.prevent="addTodo">
      <input v-model.trim="text" placeholder="输入待办事项">
      <button type="submit">添加</button>
    </form>

    <Transition name="fade">
      <p v-if="todos.length === 0" class="empty">待办事项已清空</p>
    </Transition>

    <TransitionGroup name="list" tag="ul" class="todos">
      <li v-for="todo in todos" :key="todo.id">
        <span>{{ todo.title }}</span>
        <button @click="removeTodo(todo.id)">完成</button>
      </li>
    </TransitionGroup>
  </section>
</template>

<style scoped>
.todo-page {
  display: grid;
  gap: 16px;
  max-width: 560px;
}

.todo-form {
  display: flex;
  gap: 8px;
}

.todo-form input {
  flex: 1;
}

.todos {
  display: grid;
  gap: 8px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.todos li {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px;
  border: 1px solid #d0d5dd;
  border-radius: 6px;
}

.empty {
  margin: 0;
  color: #667085;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.list-enter-active,
.list-leave-active {
  transition: all 0.2s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateY(8px);
}
</style>

