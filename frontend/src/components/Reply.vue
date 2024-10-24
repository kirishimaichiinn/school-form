<template>
  <div v-if="reply" style="display: flex">
    <div style="width: 200px;border-right: 1px solid #ebeef5">
      #{{reply.floor}}
      <br>
      <br>
      {{reply.speaker_name}}
    </div>
    <div>
      <el-text style=" font-size: 24px;color: black">{{reply.text}}</el-text>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  reply:{
    type: Object,
    required: true,
    default:()=>({})
  }
})

</script>

<style scoped>

</style>