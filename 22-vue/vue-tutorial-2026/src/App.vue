<script setup>
import { computed, ref } from 'vue'
import { chapters } from './course/courseData'

const selectedChapterId = ref('02')
const selectedTab = ref('courseware')

const selectedChapter = computed(() => {
  return chapters.find(chapter => chapter.id === selectedChapterId.value) ?? chapters[0]
})

const tabs = [
  { id: 'courseware', label: '课件' },
  { id: 'exercises', label: '练习' }
]

function selectChapter(id) {
  selectedChapterId.value = id
  selectedTab.value = 'courseware'
}
</script>

<template>
  <main class="app-shell">
    <aside class="sidebar" aria-label="课程章节">
      <div class="brand-block">
        <p class="eyebrow">Vue3 全栈课程</p>
        <h1>课程工作台</h1>
      </div>

      <nav class="chapter-nav">
        <button
          v-for="chapter in chapters"
          :key="chapter.id"
          class="chapter-button"
          :class="{ active: selectedChapter.id === chapter.id }"
          type="button"
          @click="selectChapter(chapter.id)"
        >
          <span>第 {{ chapter.id }} 章</span>
          <strong>{{ chapter.title }}</strong>
          <small>{{ chapter.priority }} · {{ chapter.status }}</small>
        </button>
      </nav>
    </aside>

    <section class="workspace">
      <header class="workspace-header">
        <div>
          <p class="eyebrow">第 {{ selectedChapter.id }} 章</p>
          <h2>{{ selectedChapter.title }}</h2>
          <p>{{ selectedChapter.summary }}</p>
        </div>
        <div class="chapter-meta">
          <span>{{ selectedChapter.priority }}</span>
          <span>{{ selectedChapter.status }}</span>
        </div>
      </header>

      <div class="tab-bar" role="tablist" aria-label="内容类型">
        <button
          v-for="tab in tabs"
          :key="tab.id"
          type="button"
          :class="{ active: selectedTab === tab.id }"
          @click="selectedTab = tab.id"
        >
          {{ tab.label }}
        </button>
      </div>

      <section v-if="selectedTab === 'courseware'" class="content-stack" aria-label="课件内容">
        <article v-if="selectedChapter.learningGoals?.length" class="lesson-section chapter-goals">
          <h3>本章学习目标</h3>
          <ol>
            <li v-for="goal in selectedChapter.learningGoals" :key="goal">{{ goal }}</li>
          </ol>
        </article>

        <article
          v-for="section in selectedChapter.courseware"
          :key="section.title"
          class="lesson-section"
        >
          <h3>{{ section.title }}</h3>

          <p v-if="section.lead" class="section-lead">{{ section.lead }}</p>

          <ul>
            <li v-for="point in section.points" :key="point">{{ point }}</li>
          </ul>

          <div v-if="section.details?.length" class="detail-list">
            <p v-for="detail in section.details" :key="detail">{{ detail }}</p>
          </div>

          <div v-if="section.steps?.length" class="textbook-block">
            <h4>操作步骤</h4>
            <ol>
              <li v-for="step in section.steps" :key="step">{{ step }}</li>
            </ol>
          </div>

          <div v-if="section.pitfalls?.length" class="textbook-block">
            <h4>常见错误</h4>
            <ul>
              <li v-for="pitfall in section.pitfalls" :key="pitfall">{{ pitfall }}</li>
            </ul>
          </div>

          <div v-if="section.interview?.length" class="textbook-block">
            <h4>面试提示</h4>
            <ul>
              <li v-for="item in section.interview" :key="item">{{ item }}</li>
            </ul>
          </div>

          <div v-if="section.examples?.length" class="example-list">
            <section v-for="example in section.examples" :key="example.title" class="example-box">
              <div>
                <strong>{{ example.title }}</strong>
                <p>{{ example.description }}</p>
              </div>
              <pre><code>{{ example.code }}</code></pre>
            </section>
          </div>
        </article>

        <article class="lesson-section qa-inline">
          <h3>本章答疑补充</h3>
          <p v-if="selectedChapter.qaNotes.length === 0" class="empty-text">
            当前章节还没有答疑记录。后续你提问后，我会把问题和解答直接补到这里。
          </p>
          <section v-for="note in selectedChapter.qaNotes" v-else :key="note.id" class="qa-note">
            <span>{{ note.topic }} · {{ note.date }}</span>
            <h4>{{ note.question }}</h4>
            <p>{{ note.answer }}</p>
          </section>
        </article>
      </section>

      <section v-else-if="selectedTab === 'exercises'" class="content-stack" aria-label="练习题">
        <div class="exercise-path">
          <span>提交目录</span>
          <strong>{{ selectedChapter.submission.path }}</strong>
          <p>{{ selectedChapter.submission.hint }}</p>
        </div>

        <div class="submission-note">
          <div>
            <span>起始结构</span>
            <strong>{{ selectedChapter.submission.label }}</strong>
          </div>
          <p>
            完成练习后把答案保留在对应 submissions 目录；我会读取答案并把批改结果写到同章 reviews 目录。
          </p>
        </div>

        <article
          v-for="exercise in selectedChapter.exercises"
          :key="exercise.id"
          class="exercise-row"
        >
          <div class="exercise-row__main">
            <span>{{ exercise.type }} · {{ exercise.id }}</span>
            <h3>{{ exercise.title }}</h3>
            <p>{{ exercise.requirement }}</p>
          </div>
          <div class="exercise-row__target">
            <strong>目标文件</strong>
            <code>{{ exercise.target }}</code>
          </div>
        </article>
      </section>
    </section>
  </main>
</template>
