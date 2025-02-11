<script setup>
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();

const showRouteInfo = () => {
  console.log("route.params", route.params)
  console.log("route.query", route.query)
  console.log("route.hash", route.hash)
  console.log("route.fullPath", route.fullPath)
  console.log("route.matched", route.matched)
  console.log("route.name", route.name)
}

const goToHome = () => {
  // router.push({name: 'Home'})
  router.push({path: '/'})
}
</script>

<template>
  <h3>User Profile</h3>
  <button @click="showRouteInfo">showRouteInfo</button>
  <button @click="goToHome">Go Home</button>
  <button @click="router.go(-1)">goBack</button>
</template>

<style scoped>

</style>