<script setup>
import {onBeforeMount, onBeforeUpdate, onMounted, onUpdated, ref} from "vue";

const count = ref(1);

onBeforeMount(
    () => {
      console.log("onBeforeMount");
      console.log("count", count.value);
      console.log(document.getElementById("btn"))
    }
);

onMounted(
    () => {
      console.log("onMounted");
      console.log("count", count.value);
      console.log(document.getElementById("btn"))
    }
);

onBeforeUpdate(
    () => {
      console.log("onBeforeUpdate");
      console.log("count", count.value);
      console.log(document.getElementById("btn").innerHTML)
    }
);

onUpdated(
    () => {
      console.log("onUpdated");
      console.log("count", count.value);
      console.log(document.getElementById("btn").innerHTML)
    }
);
</script>

<template>
  <button @click="count++" id="btn">click {{ count }}</button>
</template>

<style scoped>

</style>