<template>
  <div v-if="postHead" style="display: flex;border: 1px solid #ebeef5">
    <div style="width: 200px;border-right: 1px solid #ebeef5">
      #0
      <br>
      <br>
      {{postHead.author_name}}
    </div>
    <div>
      <el-text style="font-weight: bold; font-size: 30px;color: black">{{ postHead.title }}</el-text>
      <br>
      <el-text style=" font-size: 24px;color: black">{{postHead.text}}</el-text>
    </div>
  </div>
</template>

<script setup>
  const props = defineProps({
    postHead:{
      type: Object,
      required: true,
      default:()=>({})
    }
  })

</script>

<style scoped>

</style>