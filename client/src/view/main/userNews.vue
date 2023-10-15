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
            <el-pagination background layout="prev, pager, next" :current-page="page.currentPage" :page-size="page.pageSize"
                :total="page.total" @current-change="handlePageChange"></el-pagination>
            <el-table :data="page.data" style="width: 100%">
                <el-table-column prop="newspaperId" label="刊号"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="price" label="单价"></el-table-column>
                <el-table-column prop="pageSize" label="版面规格"></el-table-column>
                <el-table-column prop="pressId" label="出版社编号"></el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>
<script setup lang="ts">
import { onMounted, ref } from "vue";
import axios from "../../api/request.ts";
import { ElMessage } from 'element-plus';
import { useRouter } from "vue-router";
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



// 分页插件数据 // 表格数据
let page = ref({
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 10,
    "total": 50,
    "data": [{
        newspaperId: 1,
        name: "",
        price: "",
        pageSize: "",
        pressId: ""
    }]
});

// 分页插件 必须的事件，当前页更新后，向后台请求对应的数据
const handlePageChange = (page1: any) => {
    console.log("当前页:", page1);
    // 发送请求并更新表格数据
    page.value.currentPage = page1;
    loadData();
};

// 加载表格数据
const loadData = async () => {
    const res = (await axios.post('/newspaper/page', page.value)).data;
    console.log(res);
    if (res.code == 200) {
        page.value.currentPage = res.data.currentPage
        page.value.pageSize = res.data.pageSize
        page.value.totalPages = res.data.totalPages
        page.value.total = res.data.total
        page.value.data = res.data.data
    }
}

onMounted(() => {
    getCurrentUser();
    loadData();
})
</script>
<style>
.pagination {
    text-align: right;
    padding-right: 10px;
}

.dialog-footer {
    text-align: right;
}

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