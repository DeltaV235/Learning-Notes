<script setup>

import Child from "./Child.vue";
import {reactive, ref} from "vue";

const money = ref(1000);
const score = ref([120, 190, 290]);

const data = reactive({
  money: 1000,
  score: [120, 190, 290]
})

let updateMoney = (money) => {
  data.money += money;
}
</script>

<template>
  <div>
    <h2>parent</h2>
    <button @click="data.money+=100">++</button>
    <hr>
    <!--    <Child :money="money" :sco="score"/>-->
    <!--    <Child :money="data.money" :sco="data.score"/>-->
    <!--    <Child v-bind="data" @update="updateMoney"/>-->
    <Child v-model="data">
      <template #btn>
        +_+_+_
      </template>
      <template v-slot:title>
        <button>title</button>
      </template>
    </Child>
  </div>
</template>

<style scoped>
div {
  background-color: #888888;
  width: 200px;
}
</style>