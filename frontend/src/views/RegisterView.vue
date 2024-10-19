<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style=" width: 400px;background-color: white;margin: -100px auto auto;">
      <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 150px">
          <div style="font-size: 25px">论坛账号注册</div>
        </div>
        <div style="margin-top:50px">
          <el-input type="text" v-model="form.nickname" placeholder="昵称"/>
          <el-input type="text" v-model="form.account" style="margin-top: 15px" placeholder="账号"/>
          <el-input type="password" v-model="form.password" style="margin-top: 15px" placeholder="密码"/>
          <el-input type="password" v-model="form.reputPsw" style="margin-top: 15px" placeholder="确认密码"
                    @keyup.enter="register(form)"/>
        </div>

        <div style="margin-top: 30px">
          <el-button style="width: 200px;" type="success" plain @click="register(form)">注册</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
  import {register} from "@/net/auth/register.js";

  const form = reactive({
    nickname: '',
    account: '',
    password: '',
    reputPsw: ''
  })
</script>

<style scoped>

</style>