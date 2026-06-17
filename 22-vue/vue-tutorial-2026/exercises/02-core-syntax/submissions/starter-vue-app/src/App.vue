<script setup>
import { computed, ref } from 'vue'
import ListExercise from './exercises/02-list-01.vue'
import ComputedExercise from './exercises/02-computed-01.vue'
import WatchExercise from './exercises/02-watch-01.vue'
import FormExercise from './exercises/02-form-01.vue'
import EventExercise from './exercises/02-event-01.vue'

const exercises = [
  { id: '02-list-01', title: '用户列表渲染', component: ListExercise },
  { id: '02-computed-01', title: '筛选和排序', component: ComputedExercise },
  { id: '02-watch-01', title: '监听搜索关键字', component: WatchExercise },
  { id: '02-form-01', title: '新增用户表单', component: FormExercise },
  { id: '02-event-01', title: '删除用户', component: EventExercise }
]

const activeId = ref(exercises[0].id)

const activeExercise = computed(() => {
  return exercises.find(exercise => exercise.id === activeId.value) ?? exercises[0]
})
</script>

<template>
  <main class="practice-shell">
    <aside class="practice-nav">
      <h1>第 2 章练习</h1>
      <button
        v-for="exercise in exercises"
        :key="exercise.id"
        type="button"
        :class="{ active: activeId === exercise.id }"
        @click="activeId = exercise.id"
      >
        <span>{{ exercise.id }}</span>
        <strong>{{ exercise.title }}</strong>
      </button>
    </aside>

    <section class="practice-stage">
      <header>
        <p>当前练习</p>
        <h2>{{ activeExercise.title }}</h2>
      </header>
      <component :is="activeExercise.component" />
    </section>
  </main>
</template>
