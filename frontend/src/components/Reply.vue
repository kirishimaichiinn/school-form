<template>
  <div v-if="reply" style="display: flex">
    <div style="width: 200px;border-right: 1px solid #ebeef5; position: relative;left: -10px">
      #{{ reply.floor }}
      <div v-if="isMe" style="position: absolute; top: 0; right: 3px;">
        <DropdownMenu>
          <el-button @click="gotoEdit('reply',reply)">编辑</el-button>
          <el-button @click="confirmDel('是否要删除这条回复',(success,failure)=>delReply(reply,success,failure))">删除</el-button>
        </DropdownMenu>
      </div>
      <br>
      <br>
      {{ reply.speaker_name }}
    </div>
    <div>
      <el-text style=" font-size: 24px;color: black">{{ reply.text }}</el-text>
    </div>
  </div>
</template>

<script setup>
import {checkAuth} from "@/js/checkAuth.js";
import DropdownMenu from "@/components/DropdownMenu.vue";
import {confirmDel} from "@/js/confirmDel.js";
import {delReply} from "@/net/post/delReply.js";
import {gotoEdit} from "@/js/gotoEdit.js";

const props = defineProps({
  reply: {
    type: Object,
    required: true,
    default: () => ({})
  }
})

let isMe = ref(false)

onMounted(()=>{
  isMe.value = checkAuth(props.reply.speaker_id)
})
</script>

<style scoped>

</style>