<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto">这是头部
        <br>
        <button @click="router.push('/register')">注册</button>
        <button @click="router.push('/login')">登录</button>
        <button @click="test">测试</button>
        <br>
        <div v-if="logged">
          你是{{ nickname }}
          <br>
          <button @click="router.push('/post')">发帖</button>
        </div>
      </el-header>
      <el-main>
        <br>
        这是主页
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";
import {ElMessage} from "element-plus";

let logged = ref(false);
let nickname;
onBeforeMount(() => {
  checkMe(()=>{logged.value = true});
})
onMounted(() => {
  nickname = localStorage.getItem('nickname')
})
const test = function () {
}
</script>

<style scoped>

</style>