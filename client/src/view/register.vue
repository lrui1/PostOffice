<template>
    <div class="login-container">
        <el-card class="login-card">
            <h2 class="login-title">注册</h2>
            <el-form class="login-form" ref="registerForm" :model="registerData" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="registerData.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="registerData.password" placeholder="请输入密码" type="password"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input v-model="registerData.tel" placeholder="请输入电话"></el-input>
                </el-form-item>   
                <el-form-item label="地址" prop="address">
                    <el-input v-model="registerData.address" placeholder="请输入地址"></el-input>
                </el-form-item>
                <el-form-item label="邮政编码" prop="postalCode">
                    <el-input v-model="registerData.postalCode" placeholder="请输入邮政编码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleRegister">注册</el-button>
                    <el-button @click="handleReset">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { ElMessage } from "element-plus";
import axios from "../api/request.ts";
import { useRouter } from "vue-router";
const router = useRouter();

const registerData = ref({
    username: "",
    password: "",
    tel: "",
    address: "",
    postalCode: ""
});

const handleRegister = async () => {
    try {
        // 发送注册请求
        const response = (await axios.post("/log/register", registerData.value)).data;
        if (response.code === 200) {
            ElMessage.success("注册成功");
            // 跳转登录界面
            router.push('/login');
        } else {
            ElMessage.error("注册失败");
        }
    } catch (error) {
        ElMessage.error("注册失败");
        console.error(error);
    }
};

const handleReset = () => {
    // 重置表单
    registerData.value.username = "";
    registerData.value.password = "";
    registerData.value.tel = "";
    registerData.value.address = "";
    registerData.value.postalCode = "";
    // 清空表单校验状态
    
};
</script>
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