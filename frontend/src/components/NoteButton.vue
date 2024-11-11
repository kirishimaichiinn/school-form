<template>
  <el-button @click="clickOpenNote(id)">{{title}}</el-button>
</template>

<script setup>
import {clickOpenNote} from "@/js/clickOpenNote.js";

const props = defineProps({
  id: {
    type: String,
    required: true,
    default: () => ('')
  },
  title: {
    type: String,
    required: true,
    default: () => ('')
  }
})
</script>

<style scoped>

</style>