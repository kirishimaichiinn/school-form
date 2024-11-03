<template>
  <div ref="dropdownMenu" style="position: relative; display: inline-block;">
    <el-button @click="toggleMenu" @mousedown="handleMousedown">...</el-button>
    <div v-if="menuVisible" class="custom-dropdown-menu" @mousedown="handleMousedown">
      <slot></slot>
    </div>
  </div>
</template>

<script setup>

let menuVisible = ref(false)
const toggleMenu = function () {
  menuVisible.value = !menuVisible.value
}
const dropdownMenu = ref(null);
const handleMousedown = (event) => {
  event.stopPropagation();
};
const handleClickOutside = (event) => {
  if (dropdownMenu.value && !dropdownMenu.value.contains(event.target)) {
    menuVisible.value = false;
  }
};
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});
onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside);
});
</script>

<style scoped>
.custom-dropdown-menu {
  position: absolute;
  top: 0;
  left: 0;
  background-color: white;
  border: 1px solid #ebeef5;
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  min-width: 160px;
}
</style>
