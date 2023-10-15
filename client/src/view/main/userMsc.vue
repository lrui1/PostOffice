<template>
    <el-container>
        <el-header>
            <div class="header-content mt">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item v-for="breadcrumb in breadcrumbs" :key="breadcrumb.id">
                        <a :href="breadcrumb.url">{{ breadcrumb.title }}</a>
                    </el-breadcrumb-item>
                </el-breadcrumb>
                <el-dropdown class="dropdown-menu">
                    <span class="el-dropdown-link">
                        当前用户：{{ currentUser.username }}
                        <el-icon class="el-icon--right">
                            <arrow-down />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="logout">注销</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </el-header>
        <el-main>
            <el-card>
                <p>用户名: {{ currentUser.username }}</p>
                <p>电话: {{ currentUser.tel }} </p>
                <p>地址: {{ currentUser.address }} </p>
                <p>邮政编码: {{currentUser.postalCode  }}</p>
                <p>身份: {{ currentUser.identity }}</p> 
            </el-card>
        </el-main>
    </el-container>
</template>
<script setup lang="ts">
import { onMounted, ref } from "vue";
import axios from "../../api/request.ts";
import { ElMessage } from 'element-plus';
import {useRouter} from "vue-router";
const router = useRouter();
// 面包屑
const breadcrumbs = ref([
    { id: "login", title: "登录", url: '/#/login' },
    { id: "userMsc", title: "用户信息", url: "/#/userMsc" },
]);

// 定义当前用户 假设currentUser是一个ref
let currentUser = ref({
    userId: "",
    username: "",
    password: "",
    salt: "",
    tel: "",
    address: "",
    postalCode: "",
    identity: ""
}); 
// 定义注销的逻辑
const logout = async () => {
    // 执行注销操作，例如清除登录状态、重定向到登录页等
    // ...
    const res = (await axios.get('/log/logout')).data
    if (res.code == 200) {
        ElMessage.success("注销成功");
        sessionStorage.removeItem('identity');
        router.push('/login');
    } else {
        ElMessage.error("注销失败");
    }
};
// 获取当前用户
const getCurrentUser = async () => {
    const res = (await axios.get('/log/user')).data
    if (res.code == 200) {
        currentUser.value.username = res.data.username
        currentUser.value.tel = res.data.tel
        currentUser.value.address = res.data.address
        currentUser.value.postalCode = res.data.postalCode
        currentUser.value.identity = res.data.identity
    } else {
        console.log("获取当前用户失败");
    }
}

onMounted(() => {
    getCurrentUser();
})
</script>
<style>
.mt {
    margin-top: 1%;
}

.header-content {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.dropdown-menu {
    margin-left: auto;
}
</style>