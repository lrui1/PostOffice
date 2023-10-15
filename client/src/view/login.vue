<script lang="ts" setup>
import { reactive } from "vue";
import axios from "../api/request.ts";
import { User, SpringString } from "../model/model8080.ts";
import { useRouter } from "vue-router";

// do not use same name with ref
// 表单中只允许
const router = useRouter()
const form: User = reactive({
  id: 1,
  username: "",
  password: "",
  salt: "",
  tel: "",
  address: "",
  postalCode: "",
  identity: 1
});

async function onSubmit() {
  console.log("submit!");
  const resp = await axios.post("/log/login", form);
  const result: SpringString = resp.data;
  console.log(result.code);
  if (result.code == 200) {
    sessionStorage.setItem('identity', JSON.stringify(result.data.identity));
    if (result.data.identity == 2) {
      router.push('/user')
    } else {
      router.push('/userMsc')
    }
  }
}

function register() {
  console.log("register!");
  router.push('/register');
}

</script>
<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">欢迎来到JMU-飞机邮局</h2>
      <el-form class="login-form" ref="loginForm" :model="form" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
          <el-button @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-card {
  width: 400px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  padding: 20px;
}
</style>
