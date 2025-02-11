<script setup>
// 1. 插值
import {computed, reactive, ref, watch, watchEffect} from "vue";

let name = "deltav";
let person = reactive({
      age: 20,
      gender: "male"
    }
);

// 2. 指令
let buy = () => alert("buy success")
let arr = ['a', 'b', 'c'];
let html = '<h5 style="color: #747bff">hello</h5>'

// 3. 属性绑定
let url = ref('https://www.baidu.com');

let changeUrl = () => {
  url.value = 'https://www.google.com';
}

// 4. 响应式
let addAge = () => {
  person.age++;
}

// 5. 计数属性
let price = ref(100);
let count = ref(1);
let totalPrice = computed(() => price.value * count.value);

// 6. watch
// watch(url, (newVal, oldVal) => {
//   console.log("new value: ", newVal, "old value:", oldVal);
// })

// 7. watchEffect

watchEffect(
    () => {
      if (person.age > 23) {
        alert(
            "you are old enough to buy")
      }

      if (price.value > 1000) {
        alert(
            "you are rich enough to buy")
        price.value = 1000;
      }
    }
)

</script>

<template>
  <h2>username: {{ name }}</h2>
  <h2>age: {{ person.age }}</h2>
  <h2>gender: {{ person.gender }}</h2>
  <button @click="addAge">age + 1</button>

  <!--  <button v-on:click="buy">buy</button>-->
  <button @click.once="buy">buy</button>
  <span v-if="person.age>18">true</span>
  <span v-else>false</span>
  <hr>
  <span>price: {{ price }}</span>
  <span>count: {{ count }}</span>
  <span>totalPrice: {{ totalPrice }}</span>
  <button @click="price+=100">price+100</button>
  <button @click="count++">count+1</button>
  <hr>
  <ul>
    <li v-for="(value, ind) in arr">{{ value }} - {{ ind }}</li>
  </ul>
  <div v-html="html"></div>

  <a :href="url">MyGO!!!!! {{ url }}</a>
  <button @click="changeUrl">changeUrl</button>
</template>

<style scoped>
span {
  margin-right: 10px;
}
</style>