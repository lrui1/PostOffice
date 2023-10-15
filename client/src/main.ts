import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './style.css'
import router from './router/globalRouter'

import App from './App.vue'
// 导入单文件 (.vue)

const app = createApp(App);
app.use(ElementPlus).use(router)
app.mount('#app')