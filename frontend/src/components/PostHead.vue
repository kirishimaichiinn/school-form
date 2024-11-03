<template>
  <div v-if="postHead" style="display: flex;border: 1px solid #ebeef5">
    <div style="width: 200px;border-right: 1px solid #ebeef5;position: relative;top: 5px">
      #0
      <div v-if="isMe" style="position: absolute; top: 0; right: 3px;">
        <DropdownMenu>
          <el-button @click="gotoEdit('postHead',postHead)">编辑</el-button>
          <el-button @click="confirmDel('是否要删除该贴',(success,failure)=>delPost(postHead,success,failure),'/')">删除</el-button>
        </DropdownMenu>
      </div>
      <br>
      <br>
      {{ postHead.author_name }}
    </div>
    <div>
      <el-text style="font-weight: bold; font-size: 30px;color: black">{{ postHead.title }}</el-text>
      <br>
      <el-text style=" font-size: 24px;color: black">{{ postHead.text }}</el-text>
    </div>
  </div>
</template>

<script setup>
import {checkAuth} from "@/js/checkAuth.js";
import DropdownMenu from "@/components/DropdownMenu.vue";
import {confirmDel} from "@/js/confirmDel.js";
import {delPost} from "@/net/post/delPost.js";
import {gotoEdit} from "@/js/gotoEdit.js";

const props = defineProps({
  postHead: {
    type: Object,
    required: true,
    default: () => ({})
  }
})

let isMe = ref(false)

watch(
    () => props.postHead,
    (newPostHead) => {
      isMe.value = checkAuth(newPostHead.author_id)
    }
)
</script>

<style scoped>

</style>