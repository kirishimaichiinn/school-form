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
          <div style="display: flex; align-items: center; margin-top: 15px">
            <el-button @click="addPage">增页</el-button>
            <el-button @click="delPage" :disabled="pageNum === 1">减页</el-button>
            <el-pagination background style="margin-left: 15px" layout="prev, pager, next" :current-page="currentPage"
                           :page-count="pageNum" @current-change="handlePageChange"/>
          </div>
          <el-input id="inputText" type="textarea" v-model="currentText" :rows="30" resize="none"
                    style="margin-top: 15px"/>
        </div>
      </el-main>
      <el-footer>
        <button @click="putNote(form)">提交</button>
      </el-footer>
    </el-container>
  </div>
</template>


<script setup>
import {backToIndex} from "@/js/backToIndex.js";
import {putNote} from "@/net/post/putNote.js";

let pageNum = ref(1)
let currentPage = ref(1)
const form = reactive({
  title: '',
  text: ['']
})
const currentText = computed({
  get() {
    return form.text[currentPage.value - 1] || '';
  }, set(value) {
    form.text[currentPage.value - 1] = value;
  }
});

const handlePageChange = (page) => {
  currentPage.value = page
  nextTick(() => {
    document.querySelector('#inputText').focus();
  });
}

const addPage = () => {
  form.text.push('')
  pageNum.value++
  currentPage.value = pageNum.value

  nextTick(() => {
    document.querySelector('#inputText').focus();
  });
}
const delPage = () => {
  form.text.splice(currentPage.value - 1, 1)
  pageNum.value--
  if (currentPage.value - 1 === pageNum.value) currentPage.value--

  nextTick(() => {
    document.querySelector('#inputText').focus();
  });
}
</script>

<style scoped>

</style>