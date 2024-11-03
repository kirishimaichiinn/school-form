<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto;">
        <el-button @click="backToIndex">回到主页</el-button>
        这是头部
        <div v-if="isMe">
          <DropdownMenu>
            <el-button @click="editOpen">{{ edit ? '取消编辑' : '编辑' }}</el-button>
            <el-button @click="confirmDel('是否要删除该贴笔记',(success,failure)=>delNote(nid,success,failure),'/')">删除</el-button>
          </DropdownMenu>
        </div>
      </el-header>
      <el-main>
        <div>
          <el-input type="text" v-model="form.title" :readonly="!edit" style="margin-top: 15px" placeholder="标题"/>
          <div style="display: flex; align-items: center; margin-top: 15px">
            <div v-if="edit">
              <el-button @click="addPage">增页</el-button>
              <el-button @click="delPage" :disabled="pageNum === 1">减页</el-button>
              <el-button @click="updateNote(nid,form)">保存</el-button>
            </div>
            <el-pagination background style="margin-left: 15px" layout="prev, pager, next" :current-page="currentPage"
                           :page-count="pageNum" @current-change="handlePageChange"/>
          </div>
          <el-input id="inputText" type="textarea" v-model="currentText" :readonly="!edit" :rows="30" resize="none"
                    style="margin-top: 15px"/>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import {backToIndex} from "@/js/backToIndex.js";
import {getNote} from "@/net/read/getNote.js";
import {gotoEdit} from "@/js/gotoEdit.js";
import {confirmDel} from "@/js/confirmDel.js";
import {delPost} from "@/net/post/delPost.js";
import DropdownMenu from "@/components/DropdownMenu.vue";
import {checkAuth} from "@/js/checkAuth.js";
import {updateNote} from "@/net/post/updateNote.js";
import {delNote} from "@/net/post/delNote.js";

const postHead = ref()
const replyList = ref([])
let nid = 0
onBeforeMount(() => {
  let query = new URLSearchParams(window.location.search)
  nid = query.get('nid');
  if (nid) {
    getNote(nid, postHead, replyList)
  }
})

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

watch([() => postHead.value, () => replyList.value],
    () => {
      pageNum.value = replyList.value.length + 1
      for(let str of replyList.value){
        form.text.push(str.text)
      }
      form.title = postHead.value.title
      form.text[0] = postHead.value.text
    })

let isMe = ref(false)
watch(
    ()=>postHead.value,
    (newPostHead) => {
      isMe.value = checkAuth(newPostHead.author_id)
    }
)

let edit = ref(false)
const editOpen = ()=>{
  edit.value = !edit.value
}
const addPage = () => {
  form.text.push('')
  pageNum.value++
  currentPage.value = pageNum.value
}
const delPage = () => {
  form.text.splice(currentPage.value - 1, 1)
  pageNum.value--
  if (currentPage.value - 1 === pageNum.value) currentPage.value--
}
</script>

<style scoped>

</style>