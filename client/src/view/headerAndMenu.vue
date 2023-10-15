<!-- 头部和侧边栏菜单 -->

<template>
    <div class="my-header">JMU-飞机邮局</div>
    <div class="home-container">
        <el-container>
            <el-aside width="200px">
                <el-menu :default-active="defaultMenu" class="sidebar-menu" router>
                    <el-menu-item v-for="menu in menus" :key="menu.id" :index="menu.id" @select="handleMenuSelect">
                        <template #title>
                            <el-icon :class="menu.icon"></el-icon>
                            <span>{{ menu.title }}</span>
                        </template>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <RouterView></RouterView>
        </el-container>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {admin, user} from "../model/menu.ts"; // 引入用户管理员菜单

const defaultMenu = ref("user"); // 默认选中的菜单项
const identity = sessionStorage.getItem('identity')
let menus = ref([{}]);
if(identity == "1") {
  menus = user
} else {
    menus = admin
}


// 选中对应的菜单，执行的函数
const handleMenuSelect = (index: any) => {
    console.log("选中菜单项:", index);
};
</script>

<style scoped>
.home-container {
    height: 100vh;
    background-color: #f5f5f5;
}

.sidebar-menu {
    width: 100%;
}

.my-header {
    margin-left: 1%;
    font-size: xx-large;
}
</style>