<script setup>
import {useMoneyStore} from '../stores/Money.js'

const moneyStore = useMoneyStore();
const buy = () => {
  moneyStore.buy(1000)
}

const topUp = () => {
  moneyStore.topUp(1000)
}
</script>

<template>
  <button @click="buy">buy</button>
  <button @click="topUp">top up</button>
</template>

<style scoped>

</style>