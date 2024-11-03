<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: auto;">
        <div>
          <el-button @click="backToIndex">回到主页</el-button>
          你是{{ nickname }}
          <br>
        </div>
      </el-header>
      <el-main>
        <div class="custom-style">
          <el-segmented v-model="selectPage" :options="pageName" size="large" block @change="changePage"/>
        </div>
        <el-table :data="showTable" stripe border @row-click="tableRowClick" style="width: 100%">
          <el-table-column v-for="column in tableColumns" :key="column.prop" :prop="column.prop" :label="column.label" :width="column.width" :formatter="column.formatter|| undefined" />
        </el-table>
      </el-main>
      <el-footer style="position: fixed; bottom: 120px; width: 100%; text-align: center;">

      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import {checkMe} from "@/net/auth/checkMe.js";
import {getPersonal} from "@/net/post/getPersonal.js";
import {tableRowClick} from "@/js/tableRowClick.js";
import {formatLast_reply} from "@/js/formatLast_reply.js";
import {backToIndex} from "@/js/backToIndex.js";

const selectPage = ref('发贴')

const pageName = ['发贴', '回复','笔记']

const changePage = function (str) {
  switch (str) {
    case '笔记':
      showTable.value = tableData.noteList
          break
    case '发贴':
      showTable.value = tableData.postList
          break
    case '回复':
      showTable.value = tableData.replyList
          break
  }
}
const tableColumns = computed(() => {
  if (selectPage.value === '发贴') {
    return [
      { prop: 'title', label: '标题' },
      { prop: 'last_reply', label: '最后回复时间', width: 180, formatter: formatLast_reply }
    ];
  }
  if (selectPage.value === '回复') {
    return [
      { prop: 'head_title', label: '标题',width: 180 },
      { prop: 'text', label: '回复' }
    ];
  }
  if (selectPage.value === '笔记') {
    return [
      { prop: 'title', label: '标题' },
      { prop: 'last_reply', label: '最后回复时间', width: 180, formatter: formatLast_reply }
    ];
  }
});

const tableData = reactive({
  postList: [],
  noteList: [],
  replyList: []
})
let showTable = ref(tableData.postList)

let nickname = ref()

onBeforeMount(() => {
  checkMe(true,
      () => {
        nickname.value = localStorage.getItem('nickname')

        getPersonal('all', 1, tableData)
      }
  )
})
watchEffect(() => {
  changePage(selectPage.value);
});
</script>

<style scoped>

</style>