<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto;">这是头部</el-header>
      <el-main>
        <div>
          <el-input type="text" v-model="form.title" style="margin-top: 15px" placeholder="标题"/>
          <el-input type="textarea" v-model="form.text" :rows="30" resize="none" style="margin-top: 15px"/>
        </div>
      </el-main>
      <el-footer>
      <button @click="putPost(form)">提交</button>
      </el-footer>
    </el-container>
  </div>
</template>


<script setup>
import {putPost} from "@/net/post/putPost.js";

const form = reactive({
  title: '',
  text: ''
})
</script>

<style scoped>

</style>