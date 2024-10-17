<template>
  {{ count }}
  <button @click="click1">+</button>
  <button @click="click2">-</button>

  {{ val }}
  <button @click="click3">一个按钮</button>
</template>

<script setup>

import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";

let count = ref(0);
let val = ref("null")
const click1 = () => {
  count.value++
}
const click2 = () => {
  count.value--
}

const click3 = () => {
  post("/api/test/testReturn",
      {},
      data => {
        val.value = data.data
      },
      msg => {
        val.value = msg
      }
  )
}
</script>

<style scoped>

</style>