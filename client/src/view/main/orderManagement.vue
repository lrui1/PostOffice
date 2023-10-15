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
                        当前用户：{{ currentUser }}
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
            <el-row class="toolbar">
                <el-col span="12">
                    <el-button type="primary" @click="showAddDialog">新增</el-button>
                    <el-button type="danger" @click="handleDelete">删除</el-button>
                </el-col>
                <el-col span="12" class="pagination">
                    <el-pagination background layout="prev, pager, next" :current-page="page.currentPage"
                        :page-size="page.pageSize" :total="page.total" @current-change="handlePageChange"></el-pagination>
                </el-col>
            </el-row>

            <el-table class="mt" :data="page.data" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="orderId" label="订单编号"></el-table-column>
                <el-table-column prop="userId" label="用户id"></el-table-column>
                <el-table-column prop="newspaperId" label="刊号"></el-table-column>
                <el-table-column prop="count" label="数量"></el-table-column>
                <el-table-column prop="time" label="时间"></el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="showUpdateDialog(scope.row)">编辑</el-button>
                    </template>
                </el-table-column>
                <el-table-column label="状态">
                    <template #default="scope">
                        <span :class="getStatusClass(scope.row.status)">{{ scope.row.status=="1"?"完成":"未完成" }}</span>
                    </template>
                </el-table-column>

            </el-table>
        </el-main>

        <el-dialog title="新增数据" v-model="addDialogVisible" @close="clean">
            <el-form ref="addData" :model="addForm" label-width="80px">
                <el-form-item label="用户id" prop="userId">
                    <el-input v-model="addForm.userId" placeholder="请输入用户id"></el-input>
                </el-form-item>
                <el-form-item label="刊号" prop="newspaperId">
                    <el-input v-model="addForm.newspaperId" placeholder="请输入刊号"></el-input>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-input v-model="addForm.count" placeholder="请输入购买数量"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="() => {
                    addDialogVisible = false;
                }
                    ">取消</el-button>
                <el-button type="primary" @click="handleAdd">确定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="修改数据" v-model="updateDialogVisible" @close="updateFormClean">
            <el-form ref="updateData" :model="updateForm" label-width="80px">
                <el-form-item label="用户id" prop="userId">
                    <el-input v-model="updateForm.userId" placeholder="请输入用户id"></el-input>
                </el-form-item>
                <el-form-item label="刊号" prop="newspaperId">
                    <el-input v-model="updateForm.newspaperId" placeholder="请输入刊号"></el-input>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-input v-model="updateForm.count" placeholder="请输入购买数量"></el-input>
                </el-form-item>
                <el-form-item label="时间" prop="time">
                    <el-input v-model="updateForm.time" placeholder="请输入出版社编号"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-input v-model="updateForm.status" placeholder="请输入状态"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="() => {
                    updateDialogVisible = false;
                }
                    ">取消</el-button>
                <el-button type="primary" @click="handleEdit">确定</el-button>
            </div>
        </el-dialog>
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
    { id: "userOrder", title: "订报", url: "/#/userOrder" },
]);

// 定义当前用户
const currentUser = ref(""); // 假设currentUser是一个ref
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
        currentUser.value = res.data.username
        console.log(res)
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
        orderId: "",
        userId: "",
        newspaperId: "",
        count: "",
        time: "",
        status: ""
    }]
});

// 表格选中的数据
let selectData = ref([{
    orderId: "",
    userId: "",
    newspaperId: "",
    count: "",
    time: "",
    status: ""
}]);

//          增加的表单
let addDialogVisible = ref(false); // 增加表单是否可见的属性
const addForm = ref({
    orderId: "",
    userId: "",
    newspaperId: "",
    count: "",
    time: "",
    status: ""
});
// 展示增加表单的函数
const showAddDialog = () => {
    addDialogVisible.value = true;
    // console.log("dada");
};
//获取表单元素
const addData = ref()
//重置操作
const clean = () => {
    addData.value.resetFields()
}

//      修改的表单
const updateForm = ref({
    orderId: "",
    userId: "",
    newspaperId: "",
    count: "",
    time: "",
    status: ""
});
let updateDialogVisible = ref(false); // 增加表单是否可见的属性

// 展示修改表单的函数
const showUpdateDialog = (row: any) => {
    updateForm.value = row; // 将数据读到表单updateForm
    updateDialogVisible.value = true;
    console.log(updateForm.value)
};
//获取表单元素
const updateData = ref()
//重置操作
const updateFormClean = () => {
    updateData.value.resetFields()
}


// 新增功能
const handleAdd = async () => {
    console.log("新增数据:", addForm);
    const res = (await axios.post('/order', addForm.value)).data;
    if (res.code == 200) {
        // 关闭对话框并重置表单
        ElMessage.success("新增成功")
        loadData();
        addDialogVisible.value = false;
        clean();
    } else {
        ElMessage.error("您输入的数据有误")
    }
};

// 删除功能
const handleDelete = async () => {
    console.log("删除数据");
    // 发送请求并处理删除逻辑, 数据在selectData
    let ids = selectData.value.map((item) => {
        return item.orderId;
    })
    console.log(JSON.stringify(ids));
    const res = (await axios.delete('/order', {
        data: ids
    })).data;
    console.log(res)
    if (res.code == 200) {
        ElMessage.success("删除成功");
        loadData(); //重新加载数据
    } else {
        ElMessage.error('删除失败');
    }

};

// 修改功能
const handleEdit = async () => {
    console.log(updateForm.value)
    const res = (await axios.put('/order', JSON.stringify(updateForm.value), {
        headers: {
            'Content-Type': 'application/json',
        }
    })).data
    console.log(res)
    if (res.code == 200) {
        ElMessage.success("修改成功");
        loadData(); //重新加载数据
        updateDialogVisible.value = false;
    } else {
        ElMessage.error("修改失败");
    }
};


// 分页插件 必须的事件，当前页更新后，向后台请求对应的数据
const handlePageChange = (page1: any) => {
    console.log("当前页:", page1);
    // 发送请求并更新表格数据
    page.value.currentPage = page1;
    loadData();
};

// 将选中的数据绑定到定义的对应的变量
const handleSelectionChange = (selection: any) => {
    console.log("选中项:", selection);
    selectData.value = selection
};

// 加载表格数据
const loadData = async () => {
    const res = (await axios.post('/order/page', page.value)).data;
    console.log(res);
    if (res.code == 200) {
        page.value.currentPage = res.data.currentPage
        page.value.pageSize = res.data.pageSize
        page.value.totalPages = res.data.totalPages
        page.value.total = res.data.total
        page.value.data = res.data.data
    }
}

// 渲染数据
function getStatusClass(status) {
    return status == '1' ? 'status-complete' : 'status-incomplete';
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

.status-complete {
    color: green;
}

.status-incomplete {
    color: red;
}
</style>