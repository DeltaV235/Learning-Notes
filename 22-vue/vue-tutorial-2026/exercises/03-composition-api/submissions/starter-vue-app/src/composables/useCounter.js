import { computed, ref } from 'vue'

export function useCounter(initialValue = 0) {
  const count = ref(initialValue)
  const doubleCount = computed(() => count.value * 2)

  function increment() {
    // TODO: 让 count 增加 1。
  }

  function reset() {
    // TODO: 让 count 回到 initialValue。
  }

  return {
    count,
    doubleCount,
    increment,
    reset
  }
}
