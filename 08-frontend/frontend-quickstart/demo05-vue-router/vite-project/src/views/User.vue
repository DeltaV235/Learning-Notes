<script setup>

import {useRoute} from 'vue-router';

const route = useRoute();
</script>

<template>
  <h2>Welcome, {{ route.params.uid }}, {{ $route.query.name }}, {{ $route.query.age }}</h2>
  <router-link :to="'/user/' + route.params.uid + '/profile'">Profile</router-link>
  <router-link :to="'/user/' + route.params.uid + '/mail'">Mail</router-link>
  <router-view/>
</template>

<style scoped>
a {
  margin-right: 10px;
}
</style>