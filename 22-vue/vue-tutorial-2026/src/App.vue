<script setup>
const chapters = [
  { id: '01', title: 'Vue.js 概述', status: '已生成', priority: '了解' },
  { id: '02', title: '核心语法', status: '已生成', priority: '必学' },
  { id: '03', title: 'Vue3 新语法', status: '已生成', priority: '必学' },
  { id: '04', title: '组件详解', status: '待生成', priority: '必学' },
  { id: '05', title: 'Vue 路由', status: '待生成', priority: '必学' },
  { id: '06', title: '数据请求', status: '待生成', priority: '必学' },
  { id: '07', title: '状态管理', status: '待生成', priority: '必学' },
  { id: '08', title: 'UI 框架', status: '待生成', priority: '必学' },
  { id: '09', title: 'TypeScript', status: '待生成', priority: '可学' },
  { id: '10', title: 'Vue3 + TypeScript', status: '待生成', priority: '必学' }
]
</script>

<template>
  <main class="page-shell">
    <section class="hero">
      <p class="eyebrow">Vue3 全栈课程笔记</p>
      <h1>Java 后端转 Vue3 的分批学习路线</h1>
      <p class="summary">
        当前已完成第 1 到第 3 章，后续将继续补齐组件、路由、请求、Pinia、Element Plus 和 TypeScript。
      </p>
    </section>

    <section class="chapter-grid" aria-label="章节生成状态">
      <article v-for="chapter in chapters" :key="chapter.id" class="chapter-card">
        <div class="chapter-card__header">
          <span class="chapter-card__id">第 {{ chapter.id }} 章</span>
          <span class="chapter-card__status">{{ chapter.status }}</span>
        </div>
        <h2>{{ chapter.title }}</h2>
        <p>{{ chapter.priority }}</p>
      </article>
    </section>
  </main>
</template>

