<template>
  <div v-if="postHead" style="min-height: 120px;height: auto; display: flex; border: 1px solid #ebeef5;">
    <div style="width: 20%; border-right: 1px solid #ebeef5; position: relative; top: 5px;"> #0
      <div v-if="isMe" style="position: absolute; top: 0; right: 3px;">
        <DropdownMenu>
          <el-button @click="gotoEdit('postHead',postHead)">编辑</el-button>
          <el-button @click="confirmDel('是否要删除该贴',(success,failure)=>delPost(postHead,success,failure),'/')">
            删除
          </el-button>
        </DropdownMenu>
      </div>
      <div style="position:absolute;bottom: 5px;">
        {{ postHead.author_name }}
      </div>
    </div>
    <div style="width: 90%;">
      <el-text style="font-weight: bold; font-size: 30px; color: black;">{{ postHead.title }}</el-text>
      <br>
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
import {delPost} from "@/net/post/delPost.js";
import {gotoEdit} from "@/js/gotoEdit.js";
import {formatText} from "@/js/formatText.js";
import {clickOpenNote} from "@/js/clickOpenNote.js";
import NoteButton from "@/components/NoteButton.vue";

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

const formattedText = computed(() => formatText(props.postHead.text))
</script>

<style scoped>

</style>