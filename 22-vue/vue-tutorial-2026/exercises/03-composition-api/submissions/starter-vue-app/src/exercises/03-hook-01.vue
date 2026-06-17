<script setup>
import { useCounter } from '../composables/useCounter'

const { count, doubleCount, increment, reset } = useCounter(1)
</script>

<template>
  <section class="exercise-card">
    <h3>抽取 useCounter</h3>
    <p class="hint">补全 composables/useCounter.js，让下面的数据和按钮正常工作。</p>
    <p>count: {{ count }} / double: {{ doubleCount }}</p>
    <div>
      <button @click="increment">+1</button>
      <button @click="reset">重置</button>
    </div>
  </section>
</template>
