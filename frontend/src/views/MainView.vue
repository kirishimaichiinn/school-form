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
        这是主页
        <el-table :data="tableData" stripe border @row-click="tableRowClick" style="width: 100%">
          <el-table-column prop="title" label="title" />
          <el-table-column prop="author_name" label="author_name" width="180" />
          <el-table-column prop="last_reply" label="last_reply" :formatter="formatLast_reply" width="180" />
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";
import {ElMessage} from "element-plus";
import {getPostHeadList} from "@/net/post/getPostHead.js";
import {formatLast_reply} from "@/net/mainView/formatLast_reply.js";
import {tableRowClick} from "@/net/mainView/tableRowClick.js";

let logged = ref(false);
let nickname;
let page = ref(1);
onBeforeMount(() => {
  checkMe(()=>{logged.value = true});
})
onMounted(() => {
  nickname = localStorage.getItem('nickname')
  getPostHeadList(page,tableData)
})
const test = function () {
  getPostHeadList(ref(1))
}

const tableData = ref([])
</script>

<style>
.el-table__header-wrapper{
  display: none;
}
</style>