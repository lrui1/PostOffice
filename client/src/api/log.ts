import { ref } from "vue";
import axios from "./request.ts";
import { ElMessage } from 'element-plus';
import router from "../router/globalRouter.ts";

// -- 这些是从子路由文件的js中提出的公共部分 以下


// 定义当前用户
export const currentUser = ref(""); // 假设currentUser是一个ref
// 定义注销的逻辑
export const logout = async () => {
  // 执行注销操作，例如清除登录状态、重定向到登录页等
  // ...
  const res = (await axios.get('/log/logout')).data
  if(res.code == 200) {
    ElMessage.success("注销成功");
    router.push('/login');
  } else {
    ElMessage.error("注销失败");
  }
};
// 获取当前用户
export const getCurrentUser = async () => {
    const res = (await axios.get('/log/user')).data
    if(res.code == 200) {
        currentUser.value = res.data.username
        console.log(res)
    } else {
        console.log("获取当前用户失败");
    }
}
// 公共部分 以上