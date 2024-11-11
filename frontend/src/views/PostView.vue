<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto;">
        <el-button @click="backToIndex">回到主页</el-button>
        这是头部
      </el-header>
      <el-main>
        <div>
          <el-input type="text" v-model="form.title" style="margin-top: 15px" placeholder="标题"/>
          <el-input type="textarea" v-model="form.text" :rows="30" resize="none" style="margin-top: 15px"/>
        </div>
      </el-main>
      <el-footer>
        <el-button @click="putPost(form)">提交</el-button>
        <InsertNote :text="form.text" @update:text="updateText"></InsertNote>
      </el-footer>
    </el-container>
  </div>
</template>


<script setup>
import {putPost} from "@/net/post/putPost.js";
import {backToIndex} from "@/js/backToIndex.js";
import InsertNote from "@/components/InsertNote.vue";

const form = reactive({
  title: '',
  text: ''
})
const updateText = (newText) => {
  form.text = newText;
};
</script>

<style scoped>

</style>