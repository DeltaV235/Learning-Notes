<script setup>
import http from "./utils/http";

const get = async () => {
  const resp = await http.get("/demo/get")
  console.log(resp);
};

const getWithQueryParam = () => {
  http.get("/demo/get", {
    params: {
      id: 1,
      name: "test"
    }
  }).then(
  )
}

const post = () => {
  http.post("/demo/post", {
    id: 1,
    name: "test"
  }).then(res => {
    console.log(res);
  })
}

const delay = async (second) => {
  await http.get("/demo/delay/" + second, {
    timeout: 5000
  })
}
</script>

<template>
  <button @click="get">GET</button>
  <button @click="getWithQueryParam">GET Query Param</button>
  <button @click="post">POST</button>
  <button @click="delay(10)">delay 10</button>
</template>

<style scoped>

</style>
