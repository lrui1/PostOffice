import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
    //路由匹配从上到下,匹配到一个不匹配
    {
        path: '/login',
        name: 'login',
        component: () => import('../view/login.vue')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../view/register.vue')
    },
    {
        path: '/sys',
        name: 'sys',
        component: () => import('../view/headerAndMenu.vue'),
        children: [
            {
                path: '/user',
                name: 'userManagement',
                component: () => import('../view/main/userManagement.vue')
            },
            {
                path: '/newspaper',
                name: 'newspaperManagement',
                component: () => import('../view/main/newspaperManagement.vue')
            }, 
            {
                path: '/press',
                name: 'pressManagement',
                component: () => import('../view/main/pressManagement.vue')
            }, 
            {
                path: '/order',
                name: 'orderManagement',
                component: () => import('../view/main/orderManagement.vue')
            },  
            {
                path: '/userMsc',
                name: 'userMsc',
                component: () => import('../view/main/userMsc.vue')
            },
            {
                path: '/userNews',
                name: 'userNews',
                component: () => import('../view/main/userNews.vue')
            },
            {
                path: '/userOrder',
                name: 'userOrder',
                component: () => import('../view/main/userOrder.vue')
            }, 
            {
                path: '/userPay',
                name: 'userPay',
                component: () => import('../view/main/userPay.vue')
            }, 
        ]
    },
    // 404 跳登录
    {
        path: '/:pathMatcher(.*)*', // 可以匹配剩余的路径
        redirect: '/login'
    },
]

const router = createRouter({
    history: createWebHashHistory(), //路径格式
    routes: routes //路由数组
})

export default router