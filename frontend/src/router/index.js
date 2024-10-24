import { createRouter, createWebHistory } from 'vue-router'
import {checkMe} from "@/net/auth/checkMe.js";
import {ElMessage} from "element-plus";
import {getPostHeadList} from "@/net/read/getPostHeadList.js";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path:'/',
            name:'main',
            component:()=>import('@/views/MainView.vue'),
        },
        {
            path:'/register',
            name:'register',
            component:()=>import('@/views/RegisterView.vue')
        },
        {
            path:'/login',
            name:'login',
            component:()=>import('@/views/LoginView.vue')
        },
        {
            path:'/post',
            name:'post',
            component:()=>import('@/views/PostView.vue')
        },
        {
            path:'/read',
            name:'read',
            component:()=>import('@/views/ReadView.vue'),
            beforeEnter: (to, from, next) => {
                if (!to.query.pid) {
                    next({ name: '/' });
                } else {
                    next();
                }
            }
        },
        {
            path:'/space',
            name:'space',
            component:()=>import('@/views/SpaceView.vue')
        },
        {
            path:'/test',
            name:'test',
            component:()=>import('@/views/TestView.vue')
        },
        {
            path: '/:pathMatch(.*)*',
            redirect: '/'
        }

    ]
})

export default router