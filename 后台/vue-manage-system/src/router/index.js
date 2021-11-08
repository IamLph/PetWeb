import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/UserInfoManage',
                    component: () => import(/* webpackChunkName: "table" */ '../components/pages/UserInfoManage.vue'),
                    meta: { title: '用户详情管理' }
                },
                {
                    path: '/UserBasicManage',
                    component: () => import(/* webpackChunkName: "table" */ '../components/pages/UserBasicManage.vue'),
                    meta: { title: '用户基本管理' }
                },
                {
                    path: '/icon',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/Icon.vue'),
                    meta: { title: '评论管理' }
                },
                {
                    path: '/TrendManage',
                    component: () => import(/* webpackChunkName: "tabs" */ '../components/pages/TrendManage.vue'),
                    meta: { title: '动态管理' }
                },
                {
                    path: '/ArticleManage',
                    component: () => import(/* webpackChunkName: "form" */ '../components/pages/ArticleManage.vue'),
                    meta: { title: '用品管理' }
                },
                {
                    path: '/TipManage',
                    component: () => import(/* webpackChunkName: "form" */ '../components/pages/TipManage.vue'),
                    meta: { title: '小知识管理' }
                },
                {
                    path: '/MedicineManage',
                    component: () => import(/* webpackChunkName: "form" */ '../components/pages/MedicineManage.vue'),
                    meta: { title: '医疗点管理' }
                },
                {
                    path: '/PetManage',
                    component: () => import(/* webpackChunkName: "form" */ '../components/pages/PetManage.vue'),
                    meta: { title: '宠物管理' }
                },
                {
                    path: '/SharePetManage',
                    component: () => import(/* webpackChunkName: "form" */ '../components/pages/SharePetManage.vue'),
                    meta: { title: '共享宠物管理' }
                },
                {
                    path: '/form',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/BaseForm.vue'),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: () => import(/* webpackChunkName: "editor" */ '../components/page/VueEditor.vue'),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: () => import(/* webpackChunkName: "markdown" */ '../components/page/Markdown.vue'),
                    meta: { title: 'markdown编辑器' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: () => import(/* webpackChunkName: "upload" */ '../components/page/Upload.vue'),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/BaseCharts.vue'),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: () => import(/* webpackChunkName: "drag" */ '../components/page/DragList.vue'),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 拖拽Dialog组件
                    path: '/dialog',
                    component: () => import(/* webpackChunkName: "dragdialog" */ '../components/page/DragDialog.vue'),
                    meta: { title: '拖拽弹框' }
                },
                {
                    // 国际化组件
                    path: '/i18n',
                    component: () => import(/* webpackChunkName: "i18n" */ '../components/page/I18n.vue'),
                    meta: { title: '国际化' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                },
                {
                    path: '/donate',
                    component: () => import(/* webpackChunkName: "donate" */ '../components/page/Donate.vue'),
                    meta: { title: '支持作者' }
                }
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },

        {
            path: '*',
            redirect: '/404'
        }
    ]
});
