<template>
  <div ref="noteMenu" style="position: relative; display: inline-block;">
    <el-button @click="toggleMenu" @mousedown="handleMousedown">插入笔记</el-button>
    <div v-if="menuVisible" class="note-menu" @mousedown="handleMousedown">
      <el-table :data="noteData" stripe border @row-click="insertNoteOnText" style="width: 100%">
        <el-table-column prop="title" label="title"/>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import {getPersonal} from "@/net/post/getPersonal.js";

const props = defineProps({
  text: {
    type: String,
    required: true,
    default: () => ('')
  }
})
const noteData = ref([])

let menuVisible = ref(false)
const toggleMenu = function () {
  menuVisible.value = !menuVisible.value
  getPersonal('note', 1, noteData)
}
const noteMenu = ref(null);
const handleMousedown = (event) => {
  event.stopPropagation();
};
const handleClickOutside = (event) => {
  if (noteMenu.value && !noteMenu.value.contains(event.target)) {
    menuVisible.value = false;
  }
};
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});
onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside);
});

const emit = defineEmits(['update:text']);
const insertNoteOnText = (row) => {
  emit('update:text', `${props.text}<note id="${row.id}" title="${row.title}" />`);
}

</script>

<style>
.note-menu {
  position: absolute;
  //left: 120px;
  background-color: white;
  border: 1px solid #ebeef5;
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  min-width: 160px;
  transform-origin: bottom left;
}

.el-table__header-wrapper {
  display: none;
}
</style>
