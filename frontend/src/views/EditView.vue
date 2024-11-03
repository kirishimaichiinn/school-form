<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto;">
        <el-button @click="goBackRead">回到主题</el-button>
        这是头部
      </el-header>
      <el-main>
        <div>
          <el-input v-if="form.title" type="text" v-model="form.title" style="margin-top: 15px" placeholder="标题"/>
          <el-input type="textarea" v-model="form.text" :rows="30" resize="none" style="margin-top: 15px"/>
        </div>
      </el-main>
      <el-footer>
        <button @click="update(form)">更新</button>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router";
import {goBack} from "@/js/goBack.js";
import {update} from "@/net/post/update.js";

const form = reactive({
  title: '',
  text: ''
})

onMounted(()=>{
  form.text = useRoute().query.text
  form.title = useRoute().query.title
})

const goBackRead = ()=>{
  localStorage.removeItem('editId')
  goBack()
}
</script>

<style scoped>

</style>