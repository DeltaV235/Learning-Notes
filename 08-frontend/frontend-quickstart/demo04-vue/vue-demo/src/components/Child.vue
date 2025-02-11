<script setup>

// const props = defineProps(["money", "sco"]);
import {onMounted} from "vue";

const props = defineProps({
  money: {
    type: Number,
    default: 5000,
    required: true
  },
  score: Array
});

// let score = [20];

const emits = defineEmits(["update"]);

let updateMoney = () => {
  emits("update", -100);
}

const data = defineModel();
onMounted(() => console.log(data))
</script>

<template>
  <div>
    <h3>
      <slot name="title">
        default slot
      </slot>
    </h3>
    <p>money: {{ data.money }}</p>
    <span v-for="s in data.score">{{ s }}</span>
    <hr>
    <!--    <button @click="updateMoney">&#45;&#45;</button>-->
    <button @click="data.money+=-100">
      <slot name="btn"/>
    </button>
  </div>
</template>

<style scoped>
div {
  width: 100px;
  margin: 0 auto;
  background-color: #cccccc;
}

span {
  margin: 0 2px;
}
</style>