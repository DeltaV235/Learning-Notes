export const exerciseDirectories = [
  {
    chapterId: '01',
    title: 'Vue.js 概述',
    starterType: 'empty',
    root: 'exercises/01-vue-overview',
    submissions: 'exercises/01-vue-overview/submissions',
    reviews: 'exercises/01-vue-overview/reviews',
    attachments: 'exercises/01-vue-overview/attachments'
  },
  {
    chapterId: '02',
    title: '核心语法',
    starterType: 'basic-vue-project',
    root: 'exercises/02-core-syntax',
    submissions: 'exercises/02-core-syntax/submissions/starter-vue-app',
    reviews: 'exercises/02-core-syntax/reviews',
    attachments: 'exercises/02-core-syntax/attachments'
  },
  {
    chapterId: '03',
    title: 'Vue3 新语法',
    starterType: 'basic-vue-project',
    root: 'exercises/03-composition-api',
    submissions: 'exercises/03-composition-api/submissions/starter-vue-app',
    reviews: 'exercises/03-composition-api/reviews',
    attachments: 'exercises/03-composition-api/attachments'
  },
  {
    chapterId: '04',
    title: '组件详解',
    starterType: 'pending-vue-project',
    root: 'exercises/04-components',
    submissions: 'exercises/04-components/submissions',
    reviews: 'exercises/04-components/reviews',
    attachments: 'exercises/04-components/attachments'
  },
  {
    chapterId: '05',
    title: 'Vue 路由',
    starterType: 'pending-vue-project',
    root: 'exercises/05-router',
    submissions: 'exercises/05-router/submissions',
    reviews: 'exercises/05-router/reviews',
    attachments: 'exercises/05-router/attachments'
  },
  {
    chapterId: '06',
    title: '数据请求',
    starterType: 'pending-vue-project',
    root: 'exercises/06-data-request',
    submissions: 'exercises/06-data-request/submissions',
    reviews: 'exercises/06-data-request/reviews',
    attachments: 'exercises/06-data-request/attachments'
  },
  {
    chapterId: '07',
    title: '状态管理',
    starterType: 'pending-vue-project',
    root: 'exercises/07-state-management',
    submissions: 'exercises/07-state-management/submissions',
    reviews: 'exercises/07-state-management/reviews',
    attachments: 'exercises/07-state-management/attachments'
  },
  {
    chapterId: '08',
    title: 'UI 框架',
    starterType: 'pending-vue-project',
    root: 'exercises/08-ui-framework',
    submissions: 'exercises/08-ui-framework/submissions',
    reviews: 'exercises/08-ui-framework/reviews',
    attachments: 'exercises/08-ui-framework/attachments'
  },
  {
    chapterId: '09',
    title: 'TypeScript',
    starterType: 'pending-vue-project',
    root: 'exercises/09-typescript',
    submissions: 'exercises/09-typescript/submissions',
    reviews: 'exercises/09-typescript/reviews',
    attachments: 'exercises/09-typescript/attachments'
  },
  {
    chapterId: '10',
    title: 'Vue3 + TypeScript',
    starterType: 'pending-vue-project',
    root: 'exercises/10-vue-ts',
    submissions: 'exercises/10-vue-ts/submissions',
    reviews: 'exercises/10-vue-ts/reviews',
    attachments: 'exercises/10-vue-ts/attachments'
  }
]

export const reviewRubric = {
  '02-list-01': ['稳定 key', '字段展示完整', '没有把数组下标当 key'],
  '02-computed-01': ['computed 承担过滤和排序', '排序前复制数组', '模板保持清晰'],
  '02-watch-01': ['watch 能拿到新旧值', '使用 immediate', '没有用 watch 计算展示值'],
  '02-form-01': ['表单修饰符正确', '空用户名校验', '提交后重置状态'],
  '02-event-01': ['删除按钮阻止冒泡', '列表响应式更新', '行点击和按钮点击边界清楚'],
  '03-ref-01': ['JS 中使用 .value', '模板自动解包', 'reset 恢复初始值'],
  '03-reactive-01': ['使用 reactive', 'Object.assign 重置', '没有整体替换响应式对象'],
  '03-computed-01': ['computed 计算 visibleUsers', '条件完整', '模板不堆复杂表达式'],
  '03-watch-01': ['监听多个来源', '设置 immediate', '请求参数结构清晰'],
  '03-hook-01': ['hook 不依赖 this', 'doubleCount 使用 computed', '返回状态和操作函数']
}

export const submissionTemplate = {
  chapterId: '',
  exerciseId: '',
  submittedAt: '',
  changedFiles: [],
  selfCheck: []
}

export const reviewTemplate = {
  chapterId: '',
  exerciseId: '',
  reviewedAt: '',
  score: null,
  passed: false,
  findings: [],
  revisionTasks: []
}
