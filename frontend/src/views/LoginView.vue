<template>
  <el-button @click="backToIndex">回到主页</el-button>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style=" width: 400px;background-color: white;margin: -100px auto auto;">
      <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 150px">
          <div style="font-size: 25px">论坛账号登录</div>
        </div>
        <div style="margin-top:50px">
          <el-input type="text" v-model="form.account" style="margin-top: 15px" placeholder="账号"/>
          <el-input type="password" v-model="form.password" style="margin-top: 15px" placeholder="密码"
                    @keyup.enter="login(form)"/>
        </div>

        <div style="margin-top: 30px">
          <el-button style="width: 200px;" type="success" plain @click="login(form)">登录</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import {login} from "@/net/auth/login.js";
import {backToIndex} from "@/js/backToIndex.js";

const form = reactive({
  account: '',
  password: ''
})
</script>

<style scoped>

</style>