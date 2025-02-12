<script setup>
import {useMoneyStore} from '../stores/Money.js'

const moneyStore = useMoneyStore();
</script>

<template>
  <h2>RMB: {{ moneyStore.amountInRmb }}</h2>
  <h2>USD: {{ moneyStore.amountInUsd }}</h2>
  <h2>JPY: {{ moneyStore.amountInJpy }}</h2>
</template>

<style scoped>

</style>