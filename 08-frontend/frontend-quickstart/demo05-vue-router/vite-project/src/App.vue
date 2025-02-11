<script setup>
</script>

<template>
  <router-link to="/">Home</router-link>
  <br>
  <router-link to="/summary">Summary</router-link>
  <br>
  <router-link to="/login/20838">Login</router-link>
  <br>
  <router-link to="/user/108">User</router-link>
  <hr>
  <router-link :to="{
    name: 'User',
    params: {
      uid: 9088,
    },
    query: {
      name: 'zhangsan',
      age: 18,
    }
  }">
    User-With-Param-Query
  </router-link>
  <hr>
  <router-view></router-view>
</template>


<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
