import { ref } from 'vue'

export interface Menu {
    id: number;
    title: string;
    icon: string;
    url: string;
    // path: string;
    subMenus: Menu[];
}

export const admin = ref([
    {
        id: 'user',
        title: '用户管理',
        icon: 'el-icon-management',
        url: '/user'
        // subMenus: [
        //     { id: '1-1', title: '子菜单1-1' },
        //     { id: '1-2', title: '子菜单1-2' },
        // ],
    },
    {
        id: 'newspaper',
        title: '报纸管理',
        icon: 'el-icon-management',
        url: '/newspaper',
        // subMenus: [
        //     { id: '2-1', title: '子菜单2-1' },
        //     { id: '2-2', title: '子菜单2-2' },
        // ],
    },
    {
        id: 'press',
        title: '出版社管理',
        icon: 'el-icon-management',
        url: '/press',
        // subMenus: [
        //     { id: '2-1', title: '子菜单2-1' },
        //     { id: '2-2', title: '子菜单2-2' },
        // ],
    },
    {
        id: 'order',
        title: '订单管理',
        icon: 'el-icon-management',
        url: '/order',
        // subMenus: [
        //     { id: '2-1', title: '子菜单2-1' },
        //     { id: '2-2', title: '子菜单2-2' },
        // ],
    },
]);

export const user = ref([
    {
        id: 'userMsc',
        title: '用户信息',
        icon: 'el-icon-msc',
        url: '/userMsc'
    },
    {
        id: 'userNews',
        title: '报纸查询',
        icon: 'el-icon-msc',
        url: '/userNews'
    },
    {
        id: 'userOrder',
        title: '订报',
        icon: 'el-icon-management',
        url: '/order',
    },
    {
        id: 'userPay',
        title: '付款',
        icon: 'el-icon-pay',
        url: '/userPay',
    },
]);