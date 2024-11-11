<template>
  <div v-if="reply" style="min-height: 100px;display: flex">
    <div style="width: 18%;border-right: 1px solid #ebeef5; position: relative;left: -0px">
      #{{ reply.floor }}
      <div v-if="isMe" style="position: absolute; top: 0; right: 3px;">
        <DropdownMenu>
          <el-button @click="gotoEdit('reply',reply)">编辑</el-button>
          <el-button @click="confirmDel('是否要删除这条回复',(success,failure)=>delReply(reply,success,failure))">删除</el-button>
        </DropdownMenu>
      </div>
      <div style="position:absolute;bottom: 5px;">
        {{ reply.speaker_name }}
      </div>
    </div>
    <div style="width: 80%;">
      <div v-for="(part, index) in formattedText" :key="index">
        <template v-if="part.type === 'text'"><span>{{ part.content }}</span></template>
        <template v-else-if="part.type === 'button'">
          <NoteButton :id="part.id" :title="part.title" @click="clickOpenNote(part.id)"/>
        </template>
      </div>
    </div>
  </div>
</template>

<script setup>
import {checkAuth} from "@/js/checkAuth.js";
import DropdownMenu from "@/components/DropdownMenu.vue";
import {confirmDel} from "@/js/confirmDel.js";
import {delReply} from "@/net/post/delReply.js";
import {gotoEdit} from "@/js/gotoEdit.js";
import {clickOpenNote} from "@/js/clickOpenNote.js";
import NoteButton from "@/components/NoteButton.vue";
import {formatText} from "@/js/formatText.js";

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

const formattedText = computed(() => formatText(props.reply.text))
</script>

<style scoped>

</style>