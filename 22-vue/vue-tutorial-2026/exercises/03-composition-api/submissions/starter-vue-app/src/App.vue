<script setup>
import { computed, ref } from 'vue'
import RefExercise from './exercises/03-ref-01.vue'
import ReactiveExercise from './exercises/03-reactive-01.vue'
import ComputedExercise from './exercises/03-computed-01.vue'
import WatchExercise from './exercises/03-watch-01.vue'
import HookExercise from './exercises/03-hook-01.vue'

const exercises = [
  { id: '03-ref-01', title: 'ref 计数器', component: RefExercise },
  { id: '03-reactive-01', title: 'reactive 查询表单', component: ReactiveExercise },
  { id: '03-computed-01', title: 'visibleUsers', component: ComputedExercise },
  { id: '03-watch-01', title: '监听多个来源', component: WatchExercise },
  { id: '03-hook-01', title: '抽取 useCounter', component: HookExercise }
]

const activeId = ref(exercises[0].id)

const activeExercise = computed(() => {
  return exercises.find(exercise => exercise.id === activeId.value) ?? exercises[0]
})
</script>

<template>
  <main class="practice-shell">
    <aside class="practice-nav">
      <h1>第 3 章练习</h1>
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
